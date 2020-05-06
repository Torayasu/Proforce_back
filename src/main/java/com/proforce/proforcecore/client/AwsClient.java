package com.proforce.proforcecore.client;

import com.proforce.proforcecore.config.AwsConfig;
import com.proforce.proforcecore.service.AwsDateStringBuilder;
import com.proforce.proforcecore.service.SignatureCalculator;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.signer.Aws4Signer;
import software.amazon.awssdk.auth.signer.params.Aws4SignerParams;
import software.amazon.awssdk.http.SdkHttpFullRequest;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AwsClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AwsConfig awsConfig;

    @Autowired
    private SignatureCalculator signatureCalculator;

    @Autowired
    private AwsDateStringBuilder dateStringBuilder;

    public List<Bucket> getBuckets() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "AWS4-HMAC-SHA256" +
                " Credential=AKIAQ2CROYAYWWTMX5AS/20200506/us-east-1/s3/aws4_request, " +
                "SignedHeaders=host;x-amz-content-sha256;x-amz-date, " +
                "Signature=" +
                signatureCalculator.getSignature(
                        signatureCalculator.getSigningKey("fyEyO/1wbzzW4Ln0lAQvuUuE9ZMfWaikCUaofwGc"),
                        signatureCalculator.getStringToSign()));
        headers.add("x-amz-content-sha256", "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
        headers.add("X-Amz-Date",dateStringBuilder.getDateTimeString());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        URI url = UriComponentsBuilder.fromHttpUrl("https://s3.amazonaws.com").build().encode().toUri();

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println(result.getHeaders() + "\n" +  result.getBody());

        return new ArrayList<>();
    }

}v
