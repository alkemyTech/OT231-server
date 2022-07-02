package com.alkemy.ong.application.exception;

public class CategoryAlreadyExistsException extends RuntimeException{

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
