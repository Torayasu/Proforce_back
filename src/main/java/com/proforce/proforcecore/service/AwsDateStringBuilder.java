package com.proforce.proforcecore.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AwsDateStringBuilder {

    public String getDateString() {

        LocalDateTime now = LocalDateTime.now().minusHours(2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String text = now.format(formatter);

        //20200506T121553Z
        return text;

    }

    public String getDateTimeString() {

        LocalDateTime now = LocalDateTime.now().minusHours(2);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);

        return date + "T" + time + "Z";
    }

}
