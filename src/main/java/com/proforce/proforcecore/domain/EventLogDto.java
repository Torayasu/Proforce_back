package com.proforce.proforcecore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventLogDto {

    private long id;
    private String name;
    private String desc;

}
