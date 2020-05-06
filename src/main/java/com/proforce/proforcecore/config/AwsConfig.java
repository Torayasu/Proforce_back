package com.proforce.proforcecore.config;

import com.google.api.client.util.Value;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class AwsConfig {

    @Value("${aws.accessKeyId}")
    private String awsAccessKeyId;

    @Value("${aws.secretAccessKey}")
    private String awsSecretAccessKey;

}