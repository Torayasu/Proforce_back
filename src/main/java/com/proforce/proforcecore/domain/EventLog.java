package com.proforce.proforcecore.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
public class EventLog {

    @Id
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
