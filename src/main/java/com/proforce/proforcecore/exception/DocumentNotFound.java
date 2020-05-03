package com.proforce.proforcecore.exception;

public class DocumentNotFound extends RuntimeException {
    public DocumentNotFound() {
        super("Document with given ID does not exist");
    }
}
