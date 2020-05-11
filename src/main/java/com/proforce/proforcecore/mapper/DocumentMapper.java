package com.proforce.proforcecore.mapper;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.DocumentDto;
import com.proforce.proforcecore.domain.Pdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentMapper {

    @Autowired
    private PartMapper partMapper;

    @Autowired
    private PdfMapper pdfMapper;

    public Document mapToDocument(DocumentDto documentDto) {

        Document docToBeReturned =  new Document( documentDto.getName()
                            ,documentDto.getManufacturer()
                            ,documentDto.getType()
                            ,documentDto.getExpiryDate()
                            ,pdfMapper.mapToPdf(documentDto.getPdf()));

        docToBeReturned.setId(documentDto.getId());
        return docToBeReturned;
    }

    public DocumentDto mapToDocumentDto(Document document) {
        DocumentDto docDtoToBeReturned =  new DocumentDto(  document.getName()
                                ,document.getManufacturer()
                                ,document.getType()
                                ,document.getExpiryDate()
                                ,pdfMapper.mapToPdfDto(document.getPdf()));

        docDtoToBeReturned.setId(document.getId());
        return docDtoToBeReturned;
    }

    public List<DocumentDto> mapToDocumentDtoList(List<Document> documentList) {

        return documentList.stream()
                .map(document -> mapToDocumentDto(document))
                .collect(Collectors.toList());

    }

    public List<Document> mapToDocumentList(List<DocumentDto> documentDtoList) {
        return documentDtoList.stream()
                .map(documentDto -> mapToDocument(documentDto))
                .collect(Collectors.toList());
    }



}
