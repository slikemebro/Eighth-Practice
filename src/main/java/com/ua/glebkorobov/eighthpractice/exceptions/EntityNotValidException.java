package com.ua.glebkorobov.eighthpractice.exceptions;

public class EntityNotValidException extends RuntimeException{
    public EntityNotValidException(String message) {
        super(message);
    }
}
