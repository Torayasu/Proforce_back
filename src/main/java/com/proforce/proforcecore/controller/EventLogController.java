package com.proforce.proforcecore.controller;

import com.proforce.proforcecore.mapper.EventLogMapper;
import com.proforce.proforcecore.domain.EventLogDto;
import com.proforce.proforcecore.service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class EventLogController {

    private static final String APPLICATION_JSON_VALUE = "application/json";

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private EventLogMapper eventLogMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/log")
    public List<EventLogDto> getAllEventLogs() {
        return eventLogMapper.mapToEventLogDtoList(eventLogService.getAllEventLogs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/log/{id}")
    public EventLogDto getEventLogById(@PathVariable Long id) {
        return eventLogMapper.mapToEventLogDto(eventLogService.getEventLogById(id));
    }

}
