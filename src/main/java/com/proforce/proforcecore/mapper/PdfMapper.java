package com.proforce.proforcecore.mapper;

import com.proforce.proforcecore.domain.Pdf;
import com.proforce.proforcecore.domain.PdfDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PdfMapper {

    public Pdf mapToPdf(PdfDto pdfDto) {

        Pdf pdfToBeReturned = new Pdf(pdfDto.getUrl());
        pdfToBeReturned.setId(pdfDto.getId());
        return pdfToBeReturned;


    }

    public PdfDto mapToPdfDto(Pdf pdf) {

        PdfDto pdfDtoToBeReturned = new PdfDto(pdf.getUrl());
        pdfDtoToBeReturned.setId(pdf.getId());
        return pdfDtoToBeReturned;
    }

    public List<PdfDto> mapToPdfDtoList(List<Pdf> pdfList) {
        return pdfList.stream().map(pdf -> mapToPdfDto(pdf)).collect(Collectors.toList());
    }

}
