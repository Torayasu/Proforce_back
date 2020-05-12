package com.proforce.proforcecore.facade;

import com.proforce.proforcecore.client.HTMLAPIClient;
import com.proforce.proforcecore.client.S3CustomClient;
import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.service.DocumentService;
import com.proforce.proforcecore.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class DocAndPartFacade {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PartService partService;

    @Autowired
    private HTMLAPIClient htmlapiClient;

    @Autowired
    private S3CustomClient s3Client;

    public Document addDocument(Document doc) throws UnsupportedEncodingException {

        if (documentService.checkIfDocExists(doc.getId())) {
            documentService.updateDocument(doc);
        }
        else {
            documentService.createDocFromObject(doc);
            ResponseEntity<String> result = htmlapiClient.getPdfFromHtml(doc.getName(), doc.getManufacturer(), doc.getType(), String.valueOf(doc.getExpiryDate()));
            s3Client.uploadFile(result.getBody());
        }

        return doc;

    }

    public Part addPartWithDocument(Part part, Document doc) throws UnsupportedEncodingException {

        if (documentService.checkIfDocExists(doc.getId())) {
            documentService.updateDocument(doc);
        }
        else {
            documentService.createDocFromObject(doc);
            ResponseEntity<String> result = htmlapiClient.getPdfFromHtml(doc.getName(), doc.getManufacturer(), doc.getType(), String.valueOf(doc.getExpiryDate()));
            s3Client.uploadFile(result.getBody());
        }

        if (part.getDocs().isEmpty()) {
            part.addDoc(doc);
        }

        if (partService.checkIfPartExists(part.getId())) {
            partService.updatePart(part);
        }
        else {
            partService.createPartFromObject(part);
        }

        return part;
    }

    public Part addPartWithoutDocument(Part part) {

        if (partService.checkIfPartExists(part.getId())) {
            partService.updatePart(part);
        }
        else {
            partService.createPartFromObject(part);
        }

        return part;
    }

}
