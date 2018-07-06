package com.example.demo.exception;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/6 11:43
 **/
public class UserServiceException extends RuntimeException {
    public UserServiceException() {
        super();
    }

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceException(Throwable cause) {
        super(cause);
    }

    protected UserServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
