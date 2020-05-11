package com.proforce.proforcecore.scheduler;

import com.proforce.proforcecore.client.TodoLyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

public class TodoLyUpdateScheduler {

    @Autowired
    private TodoLyClient todoLyClient;

    @Scheduled(cron = "0 0 0 ? * SAT *")
    public void updatePACList(){

        ResponseEntity<String> projects = todoLyClient.getProjects();
        ResponseEntity<String> items = todoLyClient.getItems();

    }

}
