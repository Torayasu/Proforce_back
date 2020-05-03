package com.proforce.proforcecore.mapper;

import com.proforce.proforcecore.domain.Pdf;
import com.proforce.proforcecore.domain.PdfDto;
import org.springframework.stereotype.Service;

@Service
public class PdfMapper {

    public Pdf mapToPdf(PdfDto pdfDto) {

        Pdf pdfToBeReturned = new Pdf(pdfDto.getUrl());

        return pdfToBeReturned;


    }

    public PdfDto mapToPdfDto(Pdf pdf) {
        return new PdfDto(pdf.getUrl());
    }

}
