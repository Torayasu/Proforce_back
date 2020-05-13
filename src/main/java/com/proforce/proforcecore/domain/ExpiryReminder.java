package com.proforce.proforcecore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
public class ExpiryReminder {

    @Id
    @NotNull
    @GeneratedValue
    private long id;
    private String docId;
    private String message;

    public ExpiryReminder(String docId, String message) {
        this.docId = docId;
        this.message = message;
    }

    public void setId(long id) {
        this.id = id;
    }
}
