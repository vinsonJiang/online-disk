package io.vinson.file.service.util;

import io.vinson.file.service.model.FileAttribute;
import io.vinson.file.service.model.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FileUtil {

    public static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static FileAttribute getFileAttribute(String url) {
        String decodedUrl=null;
        try {
            decodedUrl = URLDecoder.decode(url, "utf-8");
        }catch (UnsupportedEncodingException e){
            logger.debug("url decode error");
        }
        FileType type = FileType.text;
        String suffix = "";
        String fileName = "";
        return new FileAttribute(type, suffix, fileName, url, decodedUrl);
    }
}
