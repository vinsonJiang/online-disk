package io.vinson.file.converter.office;

import com.sun.star.frame.XDesktop;
import com.sun.star.lang.DisposedException;
import io.vinson.file.converter.exception.OfficeException;

import java.net.ConnectException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OfficeProcessManager {
    private static final Integer EXIT_CODE_NEW_INSTALLATION = Integer.valueOf(81);

    private final OfficeProcessSettings settings;

    private final OfficeProcess process;
    private final OfficeConnection connection;

    private ExecutorService executor = Executors.newSingleThreadExecutor(new NamedThreadFactory("OfficeProcessThread"));

    private final Logger logger = Logger.getLogger(getClass().getName());

    public OfficeProcessManager(OfficeProcessSettings settings) throws OfficeException {
        this.settings = settings;
        process = new OfficeProcess(settings.getOfficeHome(), settings.getUnoUrl(), settings.getRunAsArgs(), settings.getTemplateProfileDir(), settings.getWorkDir(), settings
                .getProcessManager());
        connection = new OfficeConnection(settings.getUnoUrl());
    }

    public OfficeConnection getConnection() {
        return connection;
    }

    public void startAndWait() throws OfficeException {
        Future<?> future = executor.submit(new Runnable() {
            public void run() {

            }
        });
        try {
            future.get();
        } catch (Exception exception) {
            throw new OfficeException("failed to start and connect", exception);
        }
    }

    public void stopAndWait() throws OfficeException {
        Future<?> future = executor.submit(new Runnable() {
            public void run() {

            }
        });
        try {
            future.get();
        } catch (Exception exception) {
            throw new OfficeException("failed to start and connect", exception);
        }
    }

    boolean isConnected() {
        return connection.isConnected();
    }

}
