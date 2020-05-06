package com.proforce.proforcecore.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AwsDateStringBuilderTestSuite {


    @Autowired
    private AwsDateStringBuilder stringBuilder;

    @Test
    public void testGetDateString() {
        System.out.println(stringBuilder.getDateString());
    }

    @Test
    public void testGetTimeDateString() {
        System.out.println(stringBuilder.getDateTimeString());
    }


}
