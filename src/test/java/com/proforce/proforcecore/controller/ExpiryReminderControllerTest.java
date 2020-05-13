package com.proforce.proforcecore.controller;

import com.proforce.proforcecore.domain.ExpiryReminder;
import com.proforce.proforcecore.mapper.ExpiryReminderMapper;
import com.proforce.proforcecore.service.ExpiryReminderService;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExpiryReminderController.class)
public class ExpiryReminderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpiryReminderService expiryReminderService;

    @MockBean
    private ExpiryReminderMapper expiryReminderMapper;

    @Test
    public void shouldGetAllExpiryReminder() throws Exception {

        List<ExpiryReminder> expiryReminderList = new ArrayList<>();

        when(expiryReminderService.getAllReminders()).thenReturn(expiryReminderList);

        mockMvc.perform(get("/v1/reminder").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldCountExpiryReminders() throws Exception {

        when(expiryReminderService.getNoOfReminders()).thenReturn(10L);

        mockMvc.perform(get("/v1/reminder/count").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(10)));

    }

}