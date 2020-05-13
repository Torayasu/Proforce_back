package com.proforce.proforcecore.service;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.domain.Pdf;
import com.proforce.proforcecore.exception.DocumentNotFound;
import com.proforce.proforcecore.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public boolean checkIfDocExists(Long id) {
        Optional<Document> resultDoc = documentRepository.findById(id);
        return resultDoc.isPresent();
    }

    public Document createEmptyDoc() {
        Pdf tmpPdf = new Pdf();
        List<Part> partList = new ArrayList<>();
        Document newDoc = new Document("","","", LocalDate.now(),tmpPdf);
        return documentRepository.save(newDoc);

    }

    public Document createDocFromObject(Document document) {
        return documentRepository.save(document);
    }

    public Document updateDocument(Document document) {
        Optional<Document> docToBeUpdated = documentRepository.findById(document.getId());

        if (docToBeUpdated.isPresent()) {
            return documentRepository.save(document);
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

}
