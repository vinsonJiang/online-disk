package io.vinson.file.converter.util;

import com.sun.star.lang.XComponent;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.uno.UnoRuntime;

import java.io.File;

public class OfficeUtils {
    public static final String DESKTOP_SERVICE = "com.sun.star.frame.Desktop";

    public static <T> T cast(Class<T> type, Object object) {
        return (T) UnoRuntime.queryInterface(type, object);
    }

    public static File getOfficeExecutable(File officeHome) {
        if (PlatformUtils.isMac()) {
            return new File(officeHome, "MacOS/soffice.bin");
        } else {
            return new File(officeHome, "program/soffice.bin");
        }
    }

    public static File findOfficeHome(String... knownPaths) {
        for (String path : knownPaths) {
            File home = new File(path);
            if (getOfficeExecutable(home).isFile()) {
                return home;
            }
        }
        return null;
    }

    public static DocumentFamily getDocumentFamily(XComponent document) throws RuntimeException {
        XServiceInfo serviceInfo = cast(XServiceInfo.class, document);
        if (serviceInfo.supportsService("com.sun.star.text.GenericTextDocument")) {
            return DocumentFamily.TEXT;
        } else if (serviceInfo.supportsService("com.sun.star.sheet.SpreadsheetDocument")) {
            return DocumentFamily.SPREADSHEET;
        } else if (serviceInfo.supportsService("com.sun.star.presentation.PresentationDocument")) {
            return DocumentFamily.PRESENTATION;
        } else if (serviceInfo.supportsService("com.sun.star.drawing.DrawingDocument")) {
            return DocumentFamily.DRAWING;
        } else {
            throw new RuntimeException("document of unknown family: " + serviceInfo.getImplementationName());
        }
    }

    /**
     * 根据操作系统找到默认的office运行目录 home path
     * @return
     */
    public static File getDefaultOfficeHome() {
        // 如果配置了office.home属性，直接取值
        if (System.getProperty("office.home") != null) {
            return new File(System.getProperty("office.home"));
        }

        if (PlatformUtils.isWindows()) {
            // windows system
            String programFiles = System.getenv("ProgramFiles(x86)");
            if (programFiles == null) {
                programFiles = System.getenv("ProgramFiles");
            }
            return findOfficeHome(programFiles + File.separator + "OpenOffice 4",
                    programFiles + File.separator + "LibreOffice 4"
            );
        } else if (PlatformUtils.isMac()) {
            // mac system
            return findOfficeHome("/Applications/OpenOffice.org.app/Contents",
                    "/Applications/LibreOffice.app/Contents"
            );
        } else {
            // Linux or other *nix variants system
            return findOfficeHome("/opt/openoffice.org3",
                    "/opt/libreoffice",
                    "/usr/lib/openoffice",
                    "/usr/lib/libreoffice"
            );
        }
    }

}
