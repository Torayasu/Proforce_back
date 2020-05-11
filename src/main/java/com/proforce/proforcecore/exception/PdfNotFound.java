package com.proforce.proforcecore.exception;

public class PdfNotFound extends RuntimeException{

    public PdfNotFound() {
        super("Pdf with given ID does not exist");
    }
}
