package io.vinson.file.service.service;

import io.vinson.file.service.model.FileType;


public class FilePreviewFactory {

    public FilePreview getInstance(FileType type) {
        switch (type) {
            case text:
                return new TextFilePreview();
            case picture:
                return new PictureFilePreview();
            case office:
                return new OfficeFilePreview();
            case media:
                return new MediaFilePreview();
            default:
                return new TextFilePreview();
        }
    }
}
