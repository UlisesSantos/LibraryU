package com.school.library.Java.LibraryU.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Object value){
        super("Book with value: " + value.toString() + " not found.");
    }
}
