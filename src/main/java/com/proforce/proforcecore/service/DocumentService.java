package com.proforce.proforcecore.service;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.domain.Pdf;
import com.proforce.proforcecore.exception.DocumentNotFound;
import com.proforce.proforcecore.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document createEmptyDoc() {
        Pdf tmpPdf = new Pdf();
        List<Part> partList = new ArrayList<>();
        Document newDoc = new Document("","","",tmpPdf);
        return documentRepository.save(newDoc);

    }

    public Document createDocFromObject(Document document) {
        return documentRepository.save(document);
    }

    public void updateDocument(Document document) {
        Optional<Document> docToBeUpdated = documentRepository.findById(document.getId());

        if (docToBeUpdated.isPresent()) {
            documentRepository.save(document);
        } else {
            throw new DocumentNotFound();
        }
    }


    public void deleteDoc(Long docId) {
        Optional<Document> docToBeDeleted = documentRepository.findById(docId);

        if (docToBeDeleted.isPresent()) {
          documentRepository.deleteById(docId);
        } else {
            throw new DocumentNotFound();
        }
    }

    public List<Document> getAllDocs() {
        return documentRepository.findAll();
    }

    public List<Document> getAllDocsByType(String type) {
        return documentRepository.findAllByType(type);
    }

    public List<Document> getAllDocsByManufacturer(String manufacturer) {
        return documentRepository.findAllByManufacturer(manufacturer);
    }

    public List<Document> getAllDocsByName(String name) {
        return documentRepository.findAllByName(name);
    }

}
