package com.esen.java_kanban_rework.exception_handlers;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException (String message) {
        super(message);
    }
}
