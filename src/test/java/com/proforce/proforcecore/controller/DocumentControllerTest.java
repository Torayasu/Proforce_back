package com.proforce.proforcecore.controller;

import com.google.gson.Gson;
import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.DocumentDto;
import com.proforce.proforcecore.domain.Pdf;
import com.proforce.proforcecore.domain.PdfDto;
import com.proforce.proforcecore.facade.DocAndPartFacade;
import com.proforce.proforcecore.mapper.DocumentMapper;
import com.proforce.proforcecore.service.DocumentService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DocumentController.class)
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;

    @MockBean
    private DocumentMapper documentMapper;

    @MockBean
    private DocAndPartFacade docAndPartFacade;

    private Gson gson = new Gson();

    @Test
    public void shouldGetAllDocuments() throws Exception {

        List<Document> documentList = new ArrayList<>();

        when(documentService.getAllDocs()).thenReturn(documentList);

        mockMvc.perform(get("/v1/doc").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetAllDocumentsByType() throws Exception {

        List<Document> documentList = new ArrayList<>();

        documentList.add(new Document("Atex", "P+F", Document.TYPE_COC, LocalDate.now().plusYears(2), new Pdf()));
        documentList.add(new Document("PED", "ABB", Document.TYPE_COC, LocalDate.now().plusYears(2), new Pdf()));

        List<DocumentDto> documentDtoList = new ArrayList<>();
        documentDtoList.add(new DocumentDto("Atex", "P+F", Document.TYPE_COC, LocalDate.now().plusYears(2), new PdfDto()));
        documentDtoList.add(new DocumentDto("PED", "ABB", Document.TYPE_COC, LocalDate.now().plusYears(2), new PdfDto()));

        when(documentService.getAllDocsByType("Certificate of Conformity")).thenReturn(documentList);
        when(documentMapper.mapToDocumentDtoList(documentList)).thenReturn(documentDtoList);

        mockMvc.perform(get("/v1/doc/Certificate of Conformity").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Atex")));
    }

    @Test
    public void shouldCreateEmptyDocument() throws Exception {

        when(documentService.createEmptyDoc()).thenReturn(new Document("","","",LocalDate.now(), new Pdf()));
        when(documentMapper.mapToDocumentDto(ArgumentMatchers.any(Document.class))).thenReturn(new DocumentDto("","","",LocalDate.now(), new PdfDto()));

        mockMvc.perform(post("/v1/doc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("")));

    }


   @Test
     public void shouldCreateDocument() throws Exception {

        Document document = new Document("Atex", "P+F", "Certificate of Conformity", null, new Pdf("http://test.com"));

        DocumentDto documentDto = new DocumentDto("Atex", "P+F", "Certificate of Conformity", null, new PdfDto("http://test.com"));

        String jsonContent = gson.toJson(documentDto);

        when(documentMapper.mapToDocument(ArgumentMatchers.any(DocumentDto.class))).thenReturn(document);
        when(docAndPartFacade.addDocument(ArgumentMatchers.any(Document.class))).thenReturn(document);
        when(documentMapper.mapToDocumentDto(ArgumentMatchers.any(Document.class))).thenReturn(documentDto);

        mockMvc.perform(post("/v1/doc")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Atex")))
                .andExpect(jsonPath("$.manufacturer", is("P+F")))
                .andExpect(jsonPath("$.type", is("Certificate of Conformity")))
                .andExpect(jsonPath("$.expiryDate", Matchers.nullValue()))
                .andExpect(jsonPath("$.pdf.url", is("http://test.com")));
    }


    @Test
    public void shouldDeleteDocument() throws Exception {

        mockMvc.perform(delete("/v1/doc/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateDocument() throws Exception {

        Document document = new Document("Atex", "P+F", "Certificate of Conformity", null, new Pdf("http://test.com"));

        DocumentDto documentDto = new DocumentDto("Atex", "P+F", "Certificate of Conformity", null, new PdfDto("http://test.com"));

        String jsonContent = gson.toJson(documentDto);

        when(documentMapper.mapToDocument(ArgumentMatchers.any(DocumentDto.class))).thenReturn(document);
        when(documentService.updateDocument(ArgumentMatchers.any(Document.class))).thenReturn(document);
        when(documentMapper.mapToDocumentDto(ArgumentMatchers.any(Document.class))).thenReturn(documentDto);

        mockMvc.perform(put("/v1/doc")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Atex")))
                .andExpect(jsonPath("$.manufacturer", is("P+F")))
                .andExpect(jsonPath("$.type", is("Certificate of Conformity")))
                .andExpect(jsonPath("$.expiryDate", Matchers.nullValue()))
                .andExpect(jsonPath("$.pdf.url", is("http://test.com")));
    }


}