package com.proforce.proforcecore.controller;

import com.google.gson.Gson;
import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.domain.PartDto;
import com.proforce.proforcecore.facade.DocAndPartFacade;
import com.proforce.proforcecore.mapper.PartMapper;
import com.proforce.proforcecore.service.PartService;
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
@WebMvcTest(PartController.class)
public class PartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartService PartService;

    @MockBean
    private PartMapper PartMapper;

    @MockBean
    private DocAndPartFacade docAndPartFacade;

    private Gson gson = new Gson();

    @Test
    public void shouldGetAllParts() throws Exception {

        List<Part> PartList = new ArrayList<>();

        when(PartService.getAllParts()).thenReturn(PartList);

        mockMvc.perform(get("/v1/part").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetAllPartsByType() throws Exception {

        List<Part> PartList = new ArrayList<>();

        PartList.add(new Part.PartBuilder()
                .model("SV06")
                .manufacturer("PARKER")
                .type(Part.TYPE_PROCESS)
                .build());
        PartList.add(new Part.PartBuilder()
                .model("MHAC")
                .manufacturer("FRIGOTERMICA")
                .type(Part.TYPE_PROCESS)
                .build());

        List<PartDto> PartDtoList = new ArrayList<>();
        PartDtoList.add(new PartDto("SV06", "PARKER", Part.TYPE_PROCESS));
        PartDtoList.add(new PartDto("MHAC", "FRIGOTERMICA", Part.TYPE_PROCESS));

        when(PartService.getAllPartsByType("Process")).thenReturn(PartList);
        when(PartMapper.mapToPartDtoList(ArgumentMatchers.anyList())).thenReturn(PartDtoList);

        mockMvc.perform(get("/v1/part/process").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model", is("SV06")));
    }

    @Test
    public void shouldCreateEmptyPart() throws Exception {

        when(PartService.createEmptyPart()).thenReturn(new Part.PartBuilder()
                .model("")
                .manufacturer("")
                .type("")
                .build());
        when(PartMapper.mapToPartDto(ArgumentMatchers.any(Part.class))).thenReturn(new PartDto("","",""));

        mockMvc.perform(post("/v1/part"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("")));

    }


    @Test
    public void shouldCreatePart() throws Exception {

        Part part = new Part.PartBuilder()
                .model("SV06")
                .manufacturer("PARKER")
                .type(Part.TYPE_PROCESS)
                .build();

        PartDto partDto = new PartDto("SV06", "PARKER", Part.TYPE_PROCESS);

        String jsonContent = gson.toJson(partDto);

        when(PartMapper.mapToPart(ArgumentMatchers.any(PartDto.class))).thenReturn(part);
        when(docAndPartFacade.addPartWithoutDocument(ArgumentMatchers.any(Part.class))).thenReturn(part);
        when(PartMapper.mapToPartDto(ArgumentMatchers.any(Part.class))).thenReturn(partDto);

        mockMvc.perform(post("/v1/part")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("SV06")))
                .andExpect(jsonPath("$.manufacturer", is("PARKER")))
                .andExpect(jsonPath("$.type", is("Process")));
    }


    @Test
    public void shouldDeletePart() throws Exception {

        mockMvc.perform(delete("/v1/part/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdatePart() throws Exception {

        Part part = new Part.PartBuilder()
                .model("SV06")
                .manufacturer("PARKER")
                .type(Part.TYPE_PROCESS)
                .build();

        PartDto partDto = new PartDto("SV06", "PARKER", Part.TYPE_PROCESS);

        String jsonContent = gson.toJson(partDto);

        when(PartMapper.mapToPart(ArgumentMatchers.any(PartDto.class))).thenReturn(part);
        when(PartService.updatePart(ArgumentMatchers.any(Part.class))).thenReturn(part);
        when(PartMapper.mapToPartDto(ArgumentMatchers.any(Part.class))).thenReturn(partDto);

        mockMvc.perform(put("/v1/part")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("SV06")))
                .andExpect(jsonPath("$.manufacturer", is("PARKER")))
                .andExpect(jsonPath("$.type", is("Process")));
    }


}