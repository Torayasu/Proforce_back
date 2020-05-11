package com.proforce.proforcecore.exception;

public class PDFNotFound extends RuntimeException{

    public PDFNotFound() {
        super("Pdf with given ID does not exist");
    }
}
