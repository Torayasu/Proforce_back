package com.proforce.proforcecore.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Getter
public class EventLog {

    @Id
    @NotNull
    @GeneratedValue
    private long id;
    private String name;
    private String desc;

    public EventLog(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public void setId(long id) {
        this.id = id;
    }
}
