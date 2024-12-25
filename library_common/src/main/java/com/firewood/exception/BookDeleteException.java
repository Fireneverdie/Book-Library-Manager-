package com.firewood.exception;

public class BookDeleteException extends RuntimeException{
    public BookDeleteException(String msg){
        super(msg);
    }
}
