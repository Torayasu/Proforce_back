package com.proforce.proforcecore.scheduler;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.repository.DocumentRepository;
import com.proforce.proforcecore.repository.PartRepository;
import com.proforce.proforcecore.service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

public class DBStateUpdateScheduler {

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private PartRepository partRepository;

    @Scheduled(cron = "0 0 23 ? * * *")
    public void updateDBState() {

        String name = "Event Log: DB State Update dated " + LocalDate.now();
        String desc = "Document DB has " + documentRepository.count() + " records." + "\n" +
                "Part DB has " + partRepository.count() + " records." ;

        eventLogService.addEventLog(name, desc);

    }
}
