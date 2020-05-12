package com.proforce.proforcecore.service;

import com.proforce.proforcecore.domain.ExpiryReminder;
import com.proforce.proforcecore.repository.ExpiryReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpiryReminderService {

    @Autowired
    private ExpiryReminderRepository repository;

    public List<ExpiryReminder> getAllReminders(){

        return repository.findAll();

    }

    public Long getNoOfReminders() {
        return repository.count();
    }

    public void save(ExpiryReminder expiryReminder) {

        repository.save(expiryReminder);
    }

}
