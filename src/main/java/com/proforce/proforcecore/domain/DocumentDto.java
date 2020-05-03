package com.proforce.proforcecore.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class DocumentDto {

    private long id;
    private String name;
    private String manufacturer;
    private String type;

    private PdfDto pdf;

    public DocumentDto() {
    }

    public DocumentDto(String name, String manufacturer, String type, PdfDto pdfDto) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.pdf = pdfDto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPdf(PdfDto pdfDto) {
        this.pdf = pdfDto;
    }

}
