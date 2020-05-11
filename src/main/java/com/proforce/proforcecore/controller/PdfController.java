package com.proforce.proforcecore.controller;

import com.proforce.proforcecore.domain.PdfDto;
import com.proforce.proforcecore.mapper.PdfMapper;
import com.proforce.proforcecore.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PdfController {

    private static final String APPLICATION_JSON_VALUE = "application/json";

    @Autowired
    private PdfService pdfService;

    @Autowired
    private PdfMapper pdfMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/pdf")
    private List<PdfDto> getAllPDFs() {
        return pdfMapper.mapToPdfDtoList(pdfService.getAllPdfs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pdf/{id}")
    private PdfDto getPDFById(@PathVariable Long id) {
        return pdfMapper.mapToPdfDto(pdfService.getPdfById(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pdf", consumes = APPLICATION_JSON_VALUE)
    private void addPdf(@RequestBody PdfDto pdfDto) {
        pdfService.addPdf(pdfMapper.mapToPdf(pdfDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pdf/{id}")
    private void deletePdfById(@PathVariable Long id) {
        pdfService.deletePdfById(id);
    }


    /*

    @RequestMapping(method = RequestMethod.DELETE, value = "/part/{partId}")
    private void deletePart(@PathVariable Long partId) throws PartNotFound {
        partService.deletePart(partId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/part")
    private PartDto updatePart(@RequestBody PartDto partDto) throws PartNotFound {
        return partMapper.mapToPartDto(partService.updatePart(partMapper.mapToPart(partDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/part/{type}")
    private List<PartDto> getPartsByType(@PathVariable String type) {
        return partMapper.mapToPartDtoList(partService.getAllPartsByType(type));
    }*/

}
