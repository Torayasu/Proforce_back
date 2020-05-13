package com.proforce.proforcecore.controller;

import com.proforce.proforcecore.domain.DocumentDto;
import com.proforce.proforcecore.exception.DocumentNotFound;
import com.proforce.proforcecore.facade.DocAndPartFacade;
import com.proforce.proforcecore.mapper.DocumentMapper;
import com.proforce.proforcecore.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class DocumentController {

    private static final String APPLICATION_JSON_VALUE = "application/json";

    @Autowired
    private DocAndPartFacade docAndPartFacade;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentMapper documentMapper;

    @RequestMapping(method = RequestMethod.POST, value="/doc")
    public DocumentDto createEmptyDocument () {
        return documentMapper.mapToDocumentDto(documentService.createEmptyDoc());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doc", consumes = APPLICATION_JSON_VALUE)
    public DocumentDto createDocument (@RequestBody DocumentDto documentDto) throws UnsupportedEncodingException {
        return documentMapper.mapToDocumentDto(docAndPartFacade.addDocument(documentMapper.mapToDocument(documentDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/doc", consumes = APPLICATION_JSON_VALUE)
    public DocumentDto updateDocument (@RequestBody DocumentDto documentDto) {
        return documentMapper.mapToDocumentDto(documentService.updateDocument(documentMapper.mapToDocument(documentDto)));
    }


    @RequestMapping(method = RequestMethod.GET, value = "/doc")
    public List<DocumentDto> getAllDocuments() {
        return documentMapper.mapToDocumentDtoList(documentService.getAllDocs());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/doc/{docId}")
    public void deleteDocument(@PathVariable Long docId) throws DocumentNotFound {
        documentService.deleteDoc(docId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/doc/{type}")
    public List<DocumentDto> getAllDocumentsByType(@PathVariable String type) {
        return documentMapper.mapToDocumentDtoList(documentService.getAllDocsByType(type));
    }

}
