package com.proforce.proforcecore.service;

import com.proforce.proforcecore.domain.ExpiryReminder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExpiryReminderServiceTest {

    @Autowired
    private ExpiryReminderService expiryReminderService;

    @Test
    public void testGetNoOfReminders() {

        Long noOfRemindersBefore = expiryReminderService.getNoOfReminders();

        ExpiryReminder testReminder = new ExpiryReminder("1", "Expires");

        expiryReminderService.save(testReminder);

        Long noOfRemindersAfter = expiryReminderService.getNoOfReminders();

        assertEquals(1, noOfRemindersAfter-noOfRemindersBefore);

    }

}