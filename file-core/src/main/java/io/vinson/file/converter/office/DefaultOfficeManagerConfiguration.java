package io.vinson.file.converter.office;

import io.vinson.file.converter.process.LinuxProcessManager;
import io.vinson.file.converter.process.ProcessManager;
import io.vinson.file.converter.util.OfficeUtils;
import io.vinson.file.converter.util.PlatformUtils;

import java.io.File;

public class DefaultOfficeManagerConfiguration {

    public enum OfficeConnectionProtocol { PIPE, SOCKET }

    public static final long DEFAULT_RETRY_TIMEOUT = 120000L;

    private File officeHome = OfficeUtils.getDefaultOfficeHome();
    private OfficeConnectionProtocol connectionProtocol = OfficeConnectionProtocol.SOCKET;
    private int[] portNumbers = new int[] { 2002 };
    private String[] pipeNames = new String[] { "office" };
    private String[] runAsArgs = null;
    private File templateProfileDir = null;
    private File workDir = new File(System.getProperty("java.io.tmpdir"));
    private long taskQueueTimeout = 30000L;  // 30 seconds
    private long taskExecutionTimeout = 120000L;  // 2 minutes
    private int maxTasksPerProcess = 200;
    private long retryTimeout = DEFAULT_RETRY_TIMEOUT;

    private ProcessManager processManager = null;

    public DefaultOfficeManagerConfiguration setOfficeHome(String officeHome) throws NullPointerException, IllegalArgumentException {
        checkArgumentNotNull("officeHome", officeHome);
        return setOfficeHome(new File(officeHome));
    }

    public DefaultOfficeManagerConfiguration setOfficeHome(File officeHome) throws NullPointerException, IllegalArgumentException  {
        checkArgumentNotNull("officeHome", officeHome);
        checkArgument("officeHome", officeHome.isDirectory(), "must exist and be a directory");
        this.officeHome = officeHome;
        return this;
    }

    public DefaultOfficeManagerConfiguration setConnectionProtocol(OfficeConnectionProtocol connectionProtocol) throws NullPointerException {
        checkArgumentNotNull("connectionProtocol", connectionProtocol);
        this.connectionProtocol = connectionProtocol;
        return this;
    }

    public DefaultOfficeManagerConfiguration setPortNumber(int portNumber) {
        this.portNumbers = new int[] { portNumber };
        return this;
    }

    public DefaultOfficeManagerConfiguration setPortNumbers(int... portNumbers) throws NullPointerException, IllegalArgumentException {
        checkArgumentNotNull("portNumbers", portNumbers);
        checkArgument("portNumbers", portNumbers.length > 0, "must not be empty");
        this.portNumbers = portNumbers;
        return this;
    }

    public DefaultOfficeManagerConfiguration setPipeName(String pipeName) throws NullPointerException {
        checkArgumentNotNull("pipeName", pipeName);
        this.pipeNames = new String[] { pipeName };
        return this;
    }

    public DefaultOfficeManagerConfiguration setPipeNames(String... pipeNames) throws NullPointerException, IllegalArgumentException {
        checkArgumentNotNull("pipeNames", pipeNames);
        checkArgument("pipeNames", pipeNames.length > 0, "must not be empty");
        this.pipeNames = pipeNames;
        return this;
    }

    public DefaultOfficeManagerConfiguration setRunAsArgs(String... runAsArgs) {
		this.runAsArgs = runAsArgs;
		return this;
	}

    public DefaultOfficeManagerConfiguration setTemplateProfileDir(File templateProfileDir) throws IllegalArgumentException {
        if (templateProfileDir != null) {
            checkArgument("templateProfileDir", templateProfileDir.isDirectory(), "must exist and be a directory");
        }
        this.templateProfileDir = templateProfileDir;
        return this;
    }

    /**
     * @param workDir
     * @return
     */
    public DefaultOfficeManagerConfiguration setWorkDir(File workDir) {
        checkArgumentNotNull("workDir", workDir);
        this.workDir = workDir;
        return this;
    }

    public DefaultOfficeManagerConfiguration setTaskQueueTimeout(long taskQueueTimeout) {
        this.taskQueueTimeout = taskQueueTimeout;
        return this;
    }

    public DefaultOfficeManagerConfiguration setTaskExecutionTimeout(long taskExecutionTimeout) {
        this.taskExecutionTimeout = taskExecutionTimeout;
        return this;
    }

    public DefaultOfficeManagerConfiguration setMaxTasksPerProcess(int maxTasksPerProcess) {
        this.maxTasksPerProcess = maxTasksPerProcess;
        return this;
    }

    /**
     *
     * @param processManager
     * @return
     * @throws NullPointerException
     */
    public DefaultOfficeManagerConfiguration setProcessManager(ProcessManager processManager) throws NullPointerException {
        checkArgumentNotNull("processManager", processManager);
        this.processManager = processManager;
        return this;
    }

    /**
     *
     * @param retryTimeout in milliseconds
     * @return
     */
    public DefaultOfficeManagerConfiguration setRetryTimeout(long retryTimeout) {
        this.retryTimeout = retryTimeout;
        return this;
    }

    public OfficeManager buildOfficeManager() throws IllegalStateException {
        if (officeHome == null) {
            throw new IllegalStateException("officeHome not set and could not be auto-detected");
        } else if (!officeHome.isDirectory()) {
            throw new IllegalStateException("officeHome doesn't exist or is not a directory: " + officeHome);
        } else if (!OfficeUtils.getOfficeExecutable(officeHome).isFile()) {
            throw new IllegalStateException("invalid officeHome: it doesn't contain soffice.bin: " + officeHome);
        }
        if (templateProfileDir != null && !isValidProfileDir(templateProfileDir)) {
            throw new IllegalStateException("templateProfileDir doesn't appear to contain a user profile: " + templateProfileDir);
        }
        if (!workDir.isDirectory()) {
            throw new IllegalStateException("workDir doesn't exist or is not a directory: " + workDir);
        }

        if (processManager == null) {
            processManager = findBestProcessManager();
        }
        
        int numInstances = connectionProtocol == OfficeConnectionProtocol.PIPE ? pipeNames.length : portNumbers.length;
        UnoUrl[] unoUrls = new UnoUrl[numInstances];
        for (int i = 0; i < numInstances; i++) {
            unoUrls[i] = (connectionProtocol == OfficeConnectionProtocol.PIPE) ? UnoUrl.pipe(pipeNames[i]) : UnoUrl.socket(portNumbers[i]);
        }
        return new ProcessPoolOfficeManager(officeHome, unoUrls, runAsArgs, templateProfileDir, workDir, retryTimeout, taskQueueTimeout, taskExecutionTimeout, maxTasksPerProcess, processManager);
    }

    private ProcessManager findBestProcessManager() {
        if (isSigarAvailable()) {
            // TODO
            return null;
        } else if (PlatformUtils.isLinux()) {
        	LinuxProcessManager processManager = new LinuxProcessManager();
        	if (runAsArgs != null) {
        		processManager.setRunAsArgs(runAsArgs);
        	}
        	return processManager;
        } else {
            // TODO
            return null;
        }
    }

    private boolean isSigarAvailable() {
        try {
            Class.forName("org.hyperic.sigar.Sigar", false, getClass().getClassLoader());
            return true;
        } catch (ClassNotFoundException classNotFoundException) {
            return false;
        }
    }

    private void checkArgumentNotNull(String argName, Object argValue) throws NullPointerException {
        if (argValue == null) {
            throw new NullPointerException(argName + " must not be null");
        }
    }

    private void checkArgument(String argName, boolean condition, String message) throws IllegalArgumentException {
        if (!condition) {
            throw new IllegalArgumentException(argName + " " + message);
        }
    }

    private boolean isValidProfileDir(File profileDir) {
        return new File(profileDir, "user").isDirectory();
    }

}
