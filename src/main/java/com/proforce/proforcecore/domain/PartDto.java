package com.proforce.proforcecore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PartDto {

    private long id;
    private String model;
    private String manufacturer;
    private String type;
    private List<DocumentDto> docs;

    public PartDto() {
        this.docs = new ArrayList<>();
    }

    public PartDto(String model, String manufacturer, String type) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.docs = new ArrayList<>();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDocs(List<DocumentDto> docs) {
        this.docs = docs;
    }
}
