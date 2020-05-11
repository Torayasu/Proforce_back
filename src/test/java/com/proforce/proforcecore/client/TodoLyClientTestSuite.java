package com.proforce.proforcecore.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TodoLyClientTestSuite {

    @Autowired
    private TodoLyClient todoLyClient;

    @Test
    public void testGetItems() {

        ResponseEntity<String> result = todoLyClient.getItems();

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }


    @Test
    public void testGetProjects() {

        ResponseEntity<String> result = todoLyClient.getProjects();

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    public void addItem() {

        ResponseEntity<String> result = todoLyClient.addItemToPAC();

        System.out.println(result.toString());
    }

}
