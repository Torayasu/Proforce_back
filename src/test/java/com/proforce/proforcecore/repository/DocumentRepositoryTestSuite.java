package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.Pdf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocumentRepositoryTestSuite {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private PDFRepository pdfRepository;

    @Test
    public void testDocumentPartPDFIntegration() {

        Pdf pdfHandler = new Pdf("http://www.test.com");

        Document testDoc = new Document("CoC ATEX", "P+F", Document.TYPE_COC, pdfHandler);

        documentRepository.save(testDoc);

        Optional<Document> retrievedDoc = documentRepository.findById(testDoc.getId());

        assertNotNull(retrievedDoc);
        assertTrue(retrievedDoc.isPresent());

        assertEquals("CoC ATEX", retrievedDoc.get().getName());
        assertEquals("P+F", retrievedDoc.get().getManufacturer());
        assertEquals(Document.TYPE_COC, retrievedDoc.get().getType());

        pdfRepository.delete(pdfHandler);
        documentRepository.delete(testDoc);

        retrievedDoc = documentRepository.findById(testDoc.getId());

        assertFalse(retrievedDoc.isPresent());

    }

}
