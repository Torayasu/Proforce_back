package com.proforce.proforcecore.controller;

import com.google.gson.Gson;
import com.proforce.proforcecore.domain.Pdf;
import com.proforce.proforcecore.domain.PdfDto;
import com.proforce.proforcecore.mapper.PdfMapper;
import com.proforce.proforcecore.service.PdfService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PdfController.class)
public class PdfControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PdfService PdfService;

    @MockBean
    private PdfMapper PdfMapper;

    private Gson gson = new Gson();

    @Test
    public void shouldGetAllPdfs() throws Exception {

        List<Pdf> pdfList = new ArrayList<>();

        when(PdfService.getAllPdfs()).thenReturn(pdfList);

        mockMvc.perform(get("/v1/pdf").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetPdf() throws Exception {

        Pdf Pdf = new Pdf("http://test.com");

        PdfDto PdfDto = new PdfDto("http://test.com");

        String jsonContent = gson.toJson(PdfDto);

        when(PdfService.getPdfById(1L)).thenReturn(Pdf);
        when(PdfMapper.mapToPdfDto(ArgumentMatchers.any(Pdf.class))).thenReturn(PdfDto);

        mockMvc.perform(get("/v1/pdf/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url", is("http://test.com")));
    }

    @Test
    public void shouldAddPdf() throws Exception {

        Pdf Pdf = new Pdf("http://test.com");

        PdfDto PdfDto = new PdfDto("http://test.com");

        String jsonContent = gson.toJson(PdfDto);

        when(PdfMapper.mapToPdf(ArgumentMatchers.any(PdfDto.class))).thenReturn(Pdf);
        when(PdfService.addPdf(ArgumentMatchers.any(Pdf.class))).thenReturn(Pdf);
        when(PdfMapper.mapToPdfDto(ArgumentMatchers.any(Pdf.class))).thenReturn(PdfDto);

        mockMvc.perform(post("/v1/pdf")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url", is("http://test.com")));
    }


    @Test
    public void shouldDeletePdf() throws Exception {

        mockMvc.perform(delete("/v1/pdf/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdatePdf() throws Exception {
        Pdf Pdf = new Pdf("http://test.com");

        PdfDto PdfDto = new PdfDto("http://test.com");

        String jsonContent = gson.toJson(PdfDto);

        when(PdfMapper.mapToPdf(ArgumentMatchers.any(PdfDto.class))).thenReturn(Pdf);
        when(PdfService.updatePdf(ArgumentMatchers.any(Pdf.class))).thenReturn(Pdf);
        when(PdfMapper.mapToPdfDto(ArgumentMatchers.any(Pdf.class))).thenReturn(PdfDto);

        mockMvc.perform(put("/v1/pdf")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url", is("http://test.com")))
;
    }


}