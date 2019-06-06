package io.vinson.file.converter.office;

import io.vinson.file.converter.process.LinuxProcessManager;
import io.vinson.file.converter.process.ProcessManager;
import io.vinson.file.converter.util.OfficeUtils;

import java.io.File;

public class OfficeProcessSettings {

    public static final long DEFAULT_RETRY_INTERVAL = 250L;
    public static final long DEFAULT_RETRY_TIMEOUT = 120000L;


    public static final long DEFAULT_TASK_EXECUTION_TIMEOUT = 120000L;
    public static final int DEFAULT_MAX_TASKS_PER_PROCESS = 200;

    private long taskExecutionTimeout = DEFAULT_TASK_EXECUTION_TIMEOUT;
    private int maxTasksPerProcess = DEFAULT_MAX_TASKS_PER_PROCESS;

    private final UnoUrl unoUrl;
    private File officeHome = OfficeUtils.getDefaultOfficeHome();
    private String[] runAsArgs;
    private File templateProfileDir;
    private File workDir = new File(System.getProperty("java.io.tmpdir"));
    private ProcessManager processManager = new LinuxProcessManager();
    private long retryTimeout = DEFAULT_RETRY_TIMEOUT;
    private long retryInterval = DEFAULT_RETRY_INTERVAL;

    public OfficeProcessSettings(UnoUrl unoUrl) {
        this.unoUrl = unoUrl;
    }

    public UnoUrl getUnoUrl() {
        return unoUrl;
    }

    public File getOfficeHome() {
        return officeHome;
    }

    public void setOfficeHome(File officeHome) {
        this.officeHome = officeHome;
    }

    public String[] getRunAsArgs() {
		return runAsArgs;
	}

    public void setRunAsArgs(String[] runAsArgs) {
		this.runAsArgs = runAsArgs;
	}

    public File getTemplateProfileDir() {
        return templateProfileDir;
    }

    public void setTemplateProfileDir(File templateProfileDir) {
        this.templateProfileDir = templateProfileDir;
    }

    public File getWorkDir() {
        return workDir;
    }

    public void setWorkDir(File workDir) {
        this.workDir = workDir;
    }

    public ProcessManager getProcessManager() {
        return processManager;
    }

    public void setProcessManager(ProcessManager processManager) {
        this.processManager = processManager;
    }

    public long getRetryTimeout() {
        return retryTimeout;
    }

    public void setRetryTimeout(long retryTimeout) {
        this.retryTimeout = retryTimeout;
    }

    public long getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(long retryInterval) {
        this.retryInterval = retryInterval;
    }

    public long getTaskExecutionTimeout() {
        return taskExecutionTimeout;
    }

    public void setTaskExecutionTimeout(long taskExecutionTimeout) {
        this.taskExecutionTimeout = taskExecutionTimeout;
    }

    public int getMaxTasksPerProcess() {
        return maxTasksPerProcess;
    }

    public void setMaxTasksPerProcess(int maxTasksPerProcess) {
        this.maxTasksPerProcess = maxTasksPerProcess;
    }

}
