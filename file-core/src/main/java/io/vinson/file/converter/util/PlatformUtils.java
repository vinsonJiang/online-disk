package io.vinson.file.converter.util;

/**
 * 获取当前系统信息
 */
public class PlatformUtils {

    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    public static String getOsName() {
        return OS_NAME;
    }

    public static boolean isLinux() {
        return OS_NAME.startsWith("linux");
    }

    public static boolean isWindows() {
        return OS_NAME.startsWith("windows");
    }

    public static boolean isMac() {
        return OS_NAME.startsWith("mac");
    }
}
