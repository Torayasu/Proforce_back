package com.proforce.proforcecore.scheduler;

import com.proforce.proforcecore.client.TodoLyClient;
import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.ExpiryReminder;
import com.proforce.proforcecore.service.DocumentService;
import com.proforce.proforcecore.service.ExpiryReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoLyUpdateScheduler {

    @Autowired
    private TodoLyClient todoLyClient;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ExpiryReminderService expiryReminderService;

    @Scheduled(cron = "0 0 0 ? * SAT *")
    public void updateReminderList(){

        List<Document> allDocuments = documentService.getAllDocs();

        List<Document> soonTOExpireDocs = new ArrayList<>();

        soonTOExpireDocs = allDocuments.stream()
                .filter(document ->
                        (document.getExpiryDate().getYear() == LocalDate.now().getYear()
                && (document.getExpiryDate().getMonth().getValue() - LocalDate.now().getMonth().getValue() <= 1)))
               .collect(Collectors.toList());

        List<ExpiryReminder> listOfExpiryReminders = soonTOExpireDocs.stream()
                .map(document -> new ExpiryReminder(String.valueOf(document.getId()), String.valueOf(document.getExpiryDate())))
                .collect(Collectors.toList());

        listOfExpiryReminders.stream()
                .forEach(todoLyClient::addReminderToList);

        listOfExpiryReminders.stream()
                .forEach(expiryReminderService::save);
    }

}
