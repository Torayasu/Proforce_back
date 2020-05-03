package com.proforce.proforcecore.controller;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.DocumentDto;
import com.proforce.proforcecore.mapper.DocumentMapper;
import com.proforce.proforcecore.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class DocumentController {

    private static final String APPLICATION_JSON_VALUE = "application/json";

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentMapper documentMapper;

    @RequestMapping(method = RequestMethod.POST, value="/doc")
    private void createEmptyDocument () {
        documentService.createEmptyDoc();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doc", consumes = APPLICATION_JSON_VALUE)
    private void createDocument (@RequestBody DocumentDto documentDto) {
        documentService.createDocFromObject(documentMapper.mapToDocument(documentDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/doc")
    public List<DocumentDto> getAllDocuments() {
        return documentMapper.mapToDocumentDtoList(documentService.getAllDocs());
    }

}
