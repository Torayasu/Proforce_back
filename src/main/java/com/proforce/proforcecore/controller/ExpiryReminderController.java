package com.proforce.proforcecore.controller;


import com.proforce.proforcecore.domain.ExpiryReminderDto;
import com.proforce.proforcecore.mapper.ExpiryReminderMapper;
import com.proforce.proforcecore.service.ExpiryReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class ExpiryReminderController {

    @Autowired
    private ExpiryReminderService expiryReminderService;

    @Autowired
    private ExpiryReminderMapper expiryReminderMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/reminder")
    public List<ExpiryReminderDto> getAllReminders() {
        return expiryReminderMapper.mapToExpireReminderDtoList(expiryReminderService.getAllReminders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reminder/count")
    public Long getReminderCount() {
        return expiryReminderService.getNoOfReminders();
    }

}
