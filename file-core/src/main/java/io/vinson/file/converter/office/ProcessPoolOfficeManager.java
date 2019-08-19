package io.vinson.file.converter.office;

import io.vinson.file.converter.exception.OfficeException;
import io.vinson.file.converter.process.ProcessManager;
import io.vinson.file.converter.task.OfficeTask;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

class ProcessPoolOfficeManager implements OfficeManager {

    private final BlockingQueue<PooledOfficeManager> pool;
    private final PooledOfficeManager[] pooledManagers;
    private final long taskQueueTimeout;

    private volatile boolean running = false;

    private final Logger logger = Logger.getLogger(ProcessPoolOfficeManager.class.getName());

    public ProcessPoolOfficeManager(File officeHome, UnoUrl[] unoUrls, String[] runAsArgs, File templateProfileDir, File workDir,
                                    long retryTimeout, long taskQueueTimeout, long taskExecutionTimeout, int maxTasksPerProcess,
                                    ProcessManager processManager) {
		this.taskQueueTimeout = taskQueueTimeout;
        pool = new ArrayBlockingQueue<PooledOfficeManager>(unoUrls.length);
        pooledManagers = new PooledOfficeManager[unoUrls.length];
        for (int i = 0; i < unoUrls.length; i++) {
            OfficeProcessSettings settings = new OfficeProcessSettings(unoUrls[i]);
            settings.setRunAsArgs(runAsArgs);
            settings.setTemplateProfileDir(templateProfileDir);
            settings.setWorkDir(workDir);
            settings.setOfficeHome(officeHome);
            settings.setRetryTimeout(retryTimeout);
            settings.setTaskExecutionTimeout(taskExecutionTimeout);
            settings.setMaxTasksPerProcess(maxTasksPerProcess);
            settings.setProcessManager(processManager);
            pooledManagers[i] = new PooledOfficeManager(settings);
        }
        logger.info("ProcessManager implementation is " + processManager.getClass().getSimpleName());
    }

    public synchronized void start() throws OfficeException {
        for (int i = 0; i < pooledManagers.length; i++) {
            pooledManagers[i].start();
            releaseManager(pooledManagers[i]);
        }
        running = true;
    }

    public void execute(OfficeTask task) throws IllegalStateException, OfficeException {
        if (!running) {
            throw new IllegalStateException("this OfficeManager is currently stopped");
        }
        PooledOfficeManager manager = null;
        try {
            manager = acquireManager();
            if (manager == null) {
                throw new OfficeException("no office manager available");
            }
            manager.execute(task);
        } finally {
            if (manager != null) {
                releaseManager(manager);
            }
        }
    }

    public synchronized void stop() throws OfficeException {
        running = false;
        logger.info("stopping");
        pool.clear();
        for (int i = 0; i < pooledManagers.length; i++) {
            pooledManagers[i].stop();
        }
        logger.info("stopped");
    }

    private PooledOfficeManager acquireManager() {
        try {
            return pool.poll(taskQueueTimeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException interruptedException) {
            throw new OfficeException("interrupted", interruptedException);
        }
    }

    private void releaseManager(PooledOfficeManager manager) {
        try {
            pool.put(manager);
        } catch (InterruptedException interruptedException) {
            throw new OfficeException("interrupted", interruptedException);
        }
    }

	public boolean isRunning() {
		return running;
	}

}