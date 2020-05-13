package com.proforce.proforcecore.controller;

import com.proforce.proforcecore.domain.EventLog;
import com.proforce.proforcecore.domain.EventLogDto;
import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.mapper.EventLogMapper;
import com.proforce.proforcecore.mapper.PartMapper;
import com.proforce.proforcecore.service.EventLogService;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventLogController.class)
public class EventLogControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventLogService eventLogService;

    @MockBean
    private EventLogMapper eventLogMapper;

    @Test
    public void shouldGetAllEventLogs() throws Exception {

        List<EventLog> eventLogList = new ArrayList<>();

        when(eventLogService.getAllEventLogs()).thenReturn(eventLogList);

        mockMvc.perform(get("/v1/log").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetEventLogById() throws Exception {

        EventLog eventLog = new EventLog("Test Log","Test Desc");
        EventLogDto eventLogDto = new EventLogDto(1L, "Test Log", "Test Desc");

        when(eventLogService.getEventLogById(1L)).thenReturn(eventLog);
        when(eventLogMapper.mapToEventLogDto(ArgumentMatchers.any(EventLog.class))).thenReturn(eventLogDto);

        mockMvc.perform(get("/v1/log/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("Test Log")))
                .andExpect(jsonPath("$.desc",is("Test Desc")));
    }

}