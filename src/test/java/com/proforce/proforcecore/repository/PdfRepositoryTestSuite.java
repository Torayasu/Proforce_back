package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.domain.Pdf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PdfRepositoryTestSuite {

    @Autowired
    private PdfRepository pdfRepository;

    @Test
    public void testSavingAndDeletingPdfs() {


        List<Pdf> pdfList = new ArrayList<>();
        pdfList.add(new Pdf("http://test.com"));
        pdfList.add(new Pdf("http://test1.com"));
        pdfList.add(new Pdf("http://test2.com"));

        pdfRepository.saveAll(pdfList);

        assertEquals("http://test.com", pdfList.get(0).getUrl());
        assertEquals("http://test1.com", pdfList.get(1).getUrl());
        assertEquals("http://test2.com", pdfList.get(2).getUrl());


        pdfRepository.deleteAll(pdfList);

        assertFalse(pdfRepository.existsById(pdfList.get(0).getId()));
        assertFalse(pdfRepository.existsById(pdfList.get(1).getId()));
        assertFalse(pdfRepository.existsById(pdfList.get(2).getId()));

    }


}
