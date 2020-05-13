package com.proforce.proforcecore.service;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.Pdf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @Test
    public void testCheckIfDocExists() {

        Document document = new Document("","","", LocalDate.now(),new Pdf());

        documentService.createDocFromObject(document);

        assertTrue(documentService.checkIfDocExists(document.getId()));

    }

    @Test
    public void testDocCreationMethods() {

        Document testDoc = new Document("Atex", "P+F", Document.TYPE_COC, LocalDate.now(), new Pdf());
        Document createdDoc = documentService.createDocFromObject(testDoc);
        Document emptyCreatedDoc = documentService.createEmptyDoc();

        assertEquals("Atex", createdDoc.getName());
        assertEquals("P+F", createdDoc.getManufacturer());
        assertEquals(Document.TYPE_COC, createdDoc.getType());
        assertEquals(LocalDate.now(), createdDoc.getExpiryDate());
        assertTrue(testDoc.getPdf().equals(createdDoc.getPdf()));

        assertEquals("", emptyCreatedDoc.getName());
        assertEquals("", emptyCreatedDoc.getManufacturer());
        assertEquals("", emptyCreatedDoc.getType());
        assertEquals(LocalDate.now(), emptyCreatedDoc.getExpiryDate());
        assertEquals("", emptyCreatedDoc.getPdf().getUrl());

    }

    @Test
    public void testUpdateAndDelete() {

        Document testDoc = new Document("Atex", "P+F", Document.TYPE_COC, LocalDate.now(), new Pdf());
        Document createdDoc = documentService.createDocFromObject(testDoc);

        Document docToBeUpdated = new Document("PED", "Sentry", Document.TYPE_DOC, LocalDate.now(), new Pdf());
        docToBeUpdated.setId(createdDoc.getId());

        createdDoc = documentService.updateDocument(docToBeUpdated);

        assertEquals("PED", createdDoc.getName());
        assertEquals("Sentry", createdDoc.getManufacturer());
        assertEquals(Document.TYPE_DOC, createdDoc.getType());
        assertEquals(LocalDate.now(), createdDoc.getExpiryDate());
        assertFalse(testDoc.getPdf().equals(createdDoc.getPdf()));

        documentService.deleteDoc(createdDoc.getId());

        assertFalse(documentService.checkIfDocExists(createdDoc.getId()));

    }

}