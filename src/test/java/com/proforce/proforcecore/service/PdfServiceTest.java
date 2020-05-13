package com.proforce.proforcecore.service;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.Pdf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PdfServiceTest {

    @Autowired
    private PdfService pdfService;


    @Test
    public void testPdfCreationMethods() {

        Pdf testPdf = new Pdf("http://test.com");
        Pdf createdPdf = pdfService.addPdf(testPdf);

        assertEquals("http://test.com", createdPdf.getUrl());

    }

    @Test
    public void testUpdateAndDelete() {


        Pdf testPdf = new Pdf("http://test.com");
        Pdf createdPdf = pdfService.addPdf(testPdf);

        Pdf PdfToBeUpdated = new Pdf("http://test2.com");
        PdfToBeUpdated.setId(createdPdf.getId());

        createdPdf = pdfService.updatePdf(PdfToBeUpdated);

        assertEquals("http://test2.com", createdPdf.getUrl());

        pdfService.deletePdfById(createdPdf.getId());

        assertFalse(pdfService.checkIfPdfExists(createdPdf.getId()));

    }

}