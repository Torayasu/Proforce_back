package com.proforce.proforcecore.mapper;

import com.proforce.proforcecore.domain.EventLog;
import com.proforce.proforcecore.domain.EventLogDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventLogMapper {

    public EventLogDto mapToEventLogDto(EventLog eventLog) {
        return new EventLogDto(eventLog.getId(),eventLog.getName(),eventLog.getDesc());
    }

    public EventLog mapToEventLog(EventLogDto eventLogDto) {

        EventLog result = new EventLog(eventLogDto.getName(),eventLogDto.getDesc());

        result.setId(eventLogDto.getId());

        return result;
    }

    public List<EventLogDto> mapToEventLogDtoList(List<EventLog> listOfLogs) {

        return listOfLogs.stream().map(eventLog -> mapToEventLogDto(eventLog)).collect(Collectors.toList());
    }

}
