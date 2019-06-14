package io.vinson.file.service.model;

public enum FileType {
    picture("pictureFilePreview"),
    office("officeFilePreview"),
    text("textFilePreview"),
    media("mediaFilePreviewImpl");

    private String className;
    FileType(String className){
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
