package com.proforce.proforcecore.mapper;

import com.proforce.proforcecore.domain.ExpiryReminder;
import com.proforce.proforcecore.domain.ExpiryReminderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpiryReminderMapper {

    public ExpiryReminderDto mapToExpiryReminderDto( ExpiryReminder expiryReminder) {
        return new ExpiryReminderDto(   expiryReminder.getId(),
                                        expiryReminder.getDocId(),
                                        expiryReminder.getMessage());
    }


    public ExpiryReminder mapToExpiryReminer(ExpiryReminderDto expiryReminderDto) {

        ExpiryReminder expReminderToBeReturned = new ExpiryReminder(expiryReminderDto.getDocId(), expiryReminderDto.getMessage());

        expReminderToBeReturned.setId(expiryReminderDto.getId());

        return expReminderToBeReturned;

    }

    public List<ExpiryReminderDto> mapToExpireReminderDtoList(List<ExpiryReminder> listOfReminders) {

        return listOfReminders.stream()
                .map(expiryReminder -> mapToExpiryReminderDto(expiryReminder))
                .collect(Collectors.toList());
    }

}
