package com.proforce.proforcecore.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AwsClientTestSuite {

    @Autowired
    private AwsClient awsClient;

    @Test
    public void testGetBuckets() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

        awsClient.getBuckets();

    }
}
