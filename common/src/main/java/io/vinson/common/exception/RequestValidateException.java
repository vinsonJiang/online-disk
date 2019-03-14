package io.vinson.common.exception;

/**
 * @Description: 请求验证异常
 * @author: jiangweixin
 * @date: 2019/3/7
 */
public class RequestValidateException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    public RequestValidateException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RequestValidateException(String msg) {
        super(msg);
    }

}
