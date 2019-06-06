package io.vinson.file.converter.office;

import io.vinson.file.converter.exception.OfficeException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

class PooledOfficeManager implements OfficeManager {

    private final OfficeProcessSettings settings;
    private final OfficeProcessManager officeProcessManager;
    private final OfficeThreadPoolExecutor taskExecutor;

    private volatile boolean stopping = false;
    private int taskCount;
    private Future<?> currentTask;

    private final Logger logger = Logger.getLogger(getClass().getName());

    private OfficeConnectionEventListener connectionEventListener = new OfficeConnectionEventListener() {

        public void connected(OfficeConnectionEvent event) {
            taskCount = 0;
            taskExecutor.setAvailable(true);
        }
        public void disconnected(OfficeConnectionEvent event) {
            taskExecutor.setAvailable(false);
            if (stopping) {
                // expected
                stopping = false;
            } else {
                logger.warning("connection lost unexpectedly; attempting restart");
                if (currentTask != null) {
                    currentTask.cancel(true);
                }
            }
        }
    };



    public PooledOfficeManager(UnoUrl unoUrl) {
        this(new OfficeProcessSettings(unoUrl));
    }

    public PooledOfficeManager(OfficeProcessSettings settings) {
        this.settings = settings;
        officeProcessManager = new OfficeProcessManager(settings);
        officeProcessManager.getConnection().addConnectionEventListener(connectionEventListener);
        taskExecutor = new OfficeThreadPoolExecutor(new NamedThreadFactory("OfficeTaskThread"));
    }

    public void execute(final OfficeTask task) throws OfficeException {
        Future<?> futureTask = taskExecutor.submit(new Runnable() {
            public void run() {
                if (settings.getMaxTasksPerProcess() > 0 && ++taskCount == settings.getMaxTasksPerProcess() + 1) {
                    logger.info(String.format("reached limit of %d maxTasksPerProcess: restarting", settings.getMaxTasksPerProcess()));
                    taskExecutor.setAvailable(false);
                    stopping = true;
                }
                task.execute(officeProcessManager.getConnection());
             }
         });
         currentTask = futureTask;
         try {
             futureTask.get(settings.getTaskExecutionTimeout(), TimeUnit.MILLISECONDS);
         } catch (TimeoutException timeoutException) {
             throw new OfficeException("task did not complete within timeout", timeoutException);
         } catch (ExecutionException executionException) {
             if (executionException.getCause() instanceof OfficeException) {
                 throw (OfficeException) executionException.getCause();
             } else {
                 throw new OfficeException("task failed", executionException.getCause());
             }
         } catch (Exception exception) {
             throw new OfficeException("task failed", exception);
         }
    }

    public void start() throws OfficeException {
        officeProcessManager.startAndWait();
    }

    public void stop() throws OfficeException {
        taskExecutor.setAvailable(false);
        stopping = true;
        taskExecutor.shutdownNow();
        officeProcessManager.stopAndWait();
    }

	public boolean isRunning() {
		return officeProcessManager.isConnected();
	}

}
