package com.proforce.proforcecore.service;

import com.proforce.proforcecore.exception.EventLogNotFound;
import com.proforce.proforcecore.repository.EventLogRepository;
import com.proforce.proforcecore.scheduler.EventLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventLogService {

    @Autowired
    private EventLogRepository repository;

    public List<EventLog> getAllEventLogs() {
        return repository.findAll();
    }

    public EventLog getEventLogById(Long id) {

        Optional<EventLog> result = repository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EventLogNotFound();
        }

    }

    public void addEventLog(String name, String desc) {

        EventLog newEventLog = new EventLog(name, desc);

        repository.save(newEventLog);

    }

}
