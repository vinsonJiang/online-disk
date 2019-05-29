package io.vinson.file.converter.process;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinuxProcessManager implements ProcessManager {
    private static final Pattern PS_OUTPUT_LINE = Pattern.compile("^\\s*(\\d+)\\s+(.*)$");

    private String[] runAsArgs;

    public void setRunAsArgs(String... runAsArgs) {
        this.runAsArgs = runAsArgs;
    }

    protected String[] psCommand() {
        return new String[] { "/bin/ps", "-e", "-o", "pid,args" };
    }

    public long findPid(ProcessQuery query) throws IOException {
        String regex = Pattern.quote(query.getCommand()) + ".*" + Pattern.quote(query.getArgument());
        Pattern commandPattern = Pattern.compile(regex);
        for (String line : execute(psCommand())) {
            Matcher lineMatcher = PS_OUTPUT_LINE.matcher(line);
            if (lineMatcher.matches()) {
                String command = lineMatcher.group(2);
                Matcher commandMatcher = commandPattern.matcher(command);
                if (commandMatcher.find()) {
                    return Long.parseLong(lineMatcher.group(1));
                }
            }
        }
        return PID_NOT_FOUND;
    }

    public void kill(Process process, long pid) throws IOException {
        if (pid <= 0) {
            throw new IllegalArgumentException("invalid pid: " + pid);
        }
        execute("/bin/kill", "-KILL", Long.toString(pid));
    }

    private List<String> execute(String... args) throws IOException {
        String[] command;
        if (runAsArgs != null) {
            command = new String[runAsArgs.length + args.length];
            System.arraycopy(runAsArgs, 0, command, 0, runAsArgs.length);
            System.arraycopy(args, 0, command, runAsArgs.length, args.length);
        } else {
            command = args;
        }
        Process process = new ProcessBuilder(command).start();
        @SuppressWarnings("unchecked")
        List<String> lines = IOUtils.readLines(process.getInputStream());
        return lines;
    }

}
