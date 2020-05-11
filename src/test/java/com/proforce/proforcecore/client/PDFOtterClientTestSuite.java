package com.proforce.proforcecore.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PDFOtterClientTestSuite {

    @Autowired
    private PDFOtterClient pdfOtterClient;

    @Test
    public void testGetAllTemplates() {

        ResponseEntity<String> result = pdfOtterClient.getAllTemplates();

        System.out.println(result);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testFillInATemplate() {

        ResponseEntity<String> result = pdfOtterClient.fillInATemplate();

        System.out.println(result);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
