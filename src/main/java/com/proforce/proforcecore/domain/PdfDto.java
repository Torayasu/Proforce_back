package com.proforce.proforcecore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PdfDto {

    private long id;
    private String url;

    public PdfDto(String url) {
        this.url = url;
    }
}
