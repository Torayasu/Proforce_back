package com.proforce.proforcecore.client;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HTMLPDFAPIClientTestSuite {

    @Autowired
    private HTMLAPIClient pdfOtterClient;

    @Test
    public void testGetAllTemplates() {

        ResponseEntity<String> result = pdfOtterClient.getCredits();

        System.out.println(result);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testGetPdfFromHtml() throws IOException {

        ResponseEntity<String> result = pdfOtterClient.getPdfFromHtml("ATEX", "ABB", "CoC", "2022-11-21");

        byte[] binaryPdf= result.getBody().getBytes();

        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("filledIn.pdf"));

        outputStream.write(binaryPdf);

        outputStream.close();

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
