package com.school.library.Java.LibraryU.exception;

public class NullOrEmptyParametersException extends RuntimeException{

    public NullOrEmptyParametersException(){
        super("Value is null or empty");
    }
}
