package io.vinson.file.converter.util;

import java.io.File;

public class FileUtils extends org.apache.commons.io.FileUtils {

    public static String toUrl(File file) {
        String path = file.toURI().getRawPath();
        String url = path.startsWith("//") ? "file:" + path : "file://" + path;
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }

}
