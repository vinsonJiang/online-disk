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
            // NOTE: a GenericTextDocument is either a TextDocument, a WebDocument, or a GlobalDocument
            // but this further distinction doesn't seem to matter for conversions
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

}
