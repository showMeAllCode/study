package com.study.sys.utils;

/**
 * @author wxl
 * @date 2020/4/20 09:45:54
 */
public class LogicException extends RuntimeException {
    static final long serialVersionUID = 1L;
    
    public LogicException() {
        super();
    }
    
    public LogicException(String message) {
        super(message);
    }
    
    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public LogicException(Throwable cause) {
        super(cause);
    }

    protected LogicException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
