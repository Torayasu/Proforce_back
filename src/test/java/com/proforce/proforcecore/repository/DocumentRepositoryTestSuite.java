package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.Pdf;
import com.proforce.proforcecore.domain.Part;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocumentRepositoryTestSuite {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private PartRepository partRepository;

    @Test
    public void testDocumentPartPDFIntegration() {

        Pdf pdfHandler = new Pdf("");



        List<Part> partList = new ArrayList<>();
        partList.add(new Part.PartBuilder()
                .model("SV06")
                .manufacturer("PARKER")
                .type(Part.TYPE_PROCESS)
                .build());
        partList.add(new Part.PartBuilder()
                .model("MHAC")
                .manufacturer("FRIGOTERMICA")
                .type(Part.TYPE_COMPLEX)
                .build());
        partList.add(new Part.PartBuilder()
                .model("GHG411")
                .manufacturer("EATON")
                .type(Part.TYPE_ELECTRICAL)
                .build());

        Document testDoc = new Document("CoC ATEX", "P+F", Document.TYPE_COC,partList, pdfHandler);
        List<Document> docList = new ArrayList<>();
        docList.add(testDoc);

        pdfHandler.setDoc(testDoc);

        for (Part p : partList) {
            p.setDocs(docList);
        }


        documentRepository.save(testDoc);

        Optional<Document> retrievedDoc = documentRepository.findById(testDoc.getId());

        assertNotNull(retrievedDoc);
        assertTrue(retrievedDoc.isPresent());

        assertEquals("CoC ATEX", retrievedDoc.get().getName());
        assertEquals("P+F", retrievedDoc.get().getManufacturer());
        assertEquals(Document.TYPE_COC, retrievedDoc.get().getType());
        assertEquals("SV06", retrievedDoc.get().getParts().get(0).getModel());
        assertEquals("PARKER", retrievedDoc.get().getParts().get(0).getManufacturer());
        assertEquals(Part.TYPE_PROCESS, retrievedDoc.get().getParts().get(0).getType());
        assertEquals("MHAC", retrievedDoc.get().getParts().get(1).getModel());
        assertEquals("FRIGOTERMICA", retrievedDoc.get().getParts().get(1).getManufacturer());
        assertEquals(Part.TYPE_COMPLEX, retrievedDoc.get().getParts().get(1).getType());
        assertEquals("GHG411", retrievedDoc.get().getParts().get(2).getModel());
        assertEquals("EATON", retrievedDoc.get().getParts().get(2).getManufacturer());
        assertEquals(Part.TYPE_ELECTRICAL, retrievedDoc.get().getParts().get(2).getType());

        pdfRepository.delete(pdfHandler);
        partRepository.deleteAll(partList);
        documentRepository.delete(testDoc);

        retrievedDoc = documentRepository.findById(testDoc.getId());

        assertFalse(retrievedDoc.isPresent());


    }

}
