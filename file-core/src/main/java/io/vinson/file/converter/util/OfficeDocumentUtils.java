package io.vinson.file.converter.util;

import com.sun.star.lang.XComponent;
import com.sun.star.lang.XServiceInfo;
import io.vinson.file.converter.exception.OfficeException;

import static io.vinson.file.converter.util.OfficeUtils.cast;

class OfficeDocumentUtils {

    private OfficeDocumentUtils() {
        throw new AssertionError("utility class must not be instantiated");
    }

    public static DocumentFamily getDocumentFamily(XComponent document) throws OfficeException {
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
            throw new OfficeException("unknown document: " + serviceInfo.getImplementationName());
        }
    }

}
