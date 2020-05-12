package com.proforce.proforcecore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExpiryReminderDto {

    private long id;
    private String docId;
    private String message;

}
