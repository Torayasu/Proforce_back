package com.proforce.proforcecore.service;

import com.proforce.proforcecore.domain.Pdf;
import com.proforce.proforcecore.exception.PdfNotFound;
import com.proforce.proforcecore.repository.PdfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PdfService {

    @Autowired
    private PdfRepository pdfRepository;

    public List<Pdf> getAllPdfs() {

        return pdfRepository.findAll();

    }

    public Pdf getPdfById(Long id) {

        Optional<Pdf> result = pdfRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PdfNotFound();
        }

    }

    public void addPdf(Pdf pdf) {
        pdfRepository.save(pdf);
    }

    public void deletePdfById(Long id) {
        pdfRepository.deleteById(id);
    }










}
