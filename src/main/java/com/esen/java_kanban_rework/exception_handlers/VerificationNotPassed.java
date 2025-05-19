package com.esen.java_kanban_rework.exception_handlers;

public class VerificationNotPassed extends RuntimeException{
    public VerificationNotPassed (String message) {
        super(message);
    }
}
