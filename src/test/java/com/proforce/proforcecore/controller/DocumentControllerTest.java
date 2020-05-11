package com.proforce.proforcecore.controller;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.mapper.DocumentMapper;
import com.proforce.proforcecore.service.DocumentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


    @Test
    public void shouldGetAllDocuments() throws Exception {

        List<Document> documentList = new ArrayList<>();
        when(documentService.getAllDocs()).thenReturn(documentList);

        mockMvc.perform(get("/v1/doc").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

}