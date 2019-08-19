package io.vinson.file.service.service;

import io.vinson.file.service.model.FileAttribute;
import io.vinson.file.service.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class OfficeFilePreview implements FilePreview {
    private final Logger logger = LoggerFactory.getLogger(OfficeFilePreview.class);

    public String filePreviewHandle(String url) {
        FileAttribute fileAttribute = FileUtil.getFileAttribute(url);
        String suffix = fileAttribute.getSuffix();
        String fileName = fileAttribute.getName();
        String decodedUrl = fileAttribute.getDecodedUrl();
        boolean isHtml = suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("xlsx");
        String pdfName = fileName.substring(0, fileName.lastIndexOf(".") + 1) + (isHtml ? "html" : "pdf");
        return isHtml ? "html" : "pdf";
    }
}
