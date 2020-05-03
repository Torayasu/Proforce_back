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
        Document documentToBeReturned = new Document(documentDto.getName()
                                                    ,documentDto.getManufacturer()
                                                    ,documentDto.getType()
                                                    ,new ArrayList<>()
                                                    ,new Pdf());

        documentDto.getParts().stream().map(partDto -> partMapper.mapToPart(partDto)).forEach(documentToBeReturned::addPart);

        return documentToBeReturned;

    }

    public DocumentDto mapToDocumentDto(Document document) {
        DocumentDto documentDtoToBeReturned = new DocumentDto(  document.getName()
                                                                ,document.getManufacturer()
                                                                ,document.getType());

        documentDtoToBeReturned.setPdf(pdfMapper.mapToPdfDto(document.getPdf()));

        document.getParts().stream().map(part -> partMapper.mapToPartDto(part)).forEach(documentDtoToBeReturned::addPart);

        return documentDtoToBeReturned;
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
