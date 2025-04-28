package com.school.library.Java.LibraryU.exception;

public class BooksNotFoundException extends RuntimeException{

    public BooksNotFoundException(){
        super("Books not found");
    }

    public BooksNotFoundException(Object value){
        super("Books with the value " + value + " not found");
    }
}
