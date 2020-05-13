package com.proforce.proforcecore.client;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3CustomClient {

    private S3Client s3Client = null;


    public S3CustomClient() {
        s3Client = S3Client.builder()
                .region(Region.EU_CENTRAL_1)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }

    private S3CustomClient(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(String docBody) {

        s3Client.putObject(PutObjectRequest.builder().bucket("proforce").key("Test File").build(),
                RequestBody.fromString(docBody));

    }

}
