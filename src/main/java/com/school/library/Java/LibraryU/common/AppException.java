package com.school.library.Java.LibraryU.common;

/**
 * General non-checked exceptions class
 * */
public class AppException extends RuntimeException{

    //Serial version UID
    private static final long serialVersionUID = 1L;

    //Error code
    private final int errorCode;

    // HTTP code
    private final int httpCode;

    /**
     * Builder to receive the checked exception
     * @param message General Message from developer
     * @param cause Info from the error
     * @param errorCode Code from the error
     * @param httpCode HTTP code from the error
     * */
    public AppException(String message, Throwable cause, int errorCode, int httpCode){
        super(message, cause);
        this.errorCode = errorCode;
        this.httpCode = httpCode;
    }
}
