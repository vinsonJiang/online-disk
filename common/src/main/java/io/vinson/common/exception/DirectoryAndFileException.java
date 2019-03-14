package io.vinson.common.exception;

/**
 * @Description: 文件或目录异常
 * @author: jiangweixin
 * @date: 2019/3/7
 */
public class DirectoryAndFileException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    public DirectoryAndFileException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DirectoryAndFileException(String msg) {
        super(msg);
    }

}
