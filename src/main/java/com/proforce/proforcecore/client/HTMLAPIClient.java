package com.proforce.proforcecore.client;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.proforce.proforcecore.config.CoreConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
public class HTMLAPIClient {

    private String token = "eKIq-kCarhnW_Tp98SQ8WQTcn06H55we";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> getCredits() {

        URI url = UriComponentsBuilder.fromHttpUrl("https://htmlpdfapi.com/api/v1/credits").encode().build().toUri();

        HttpEntity<String> entity = new HttpEntity<>(getAuthorizationHeader());

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return result;

    }

    public ResponseEntity<String> getPdfFromHtml(String name, String manufacturer, String type, String expiryDate) throws UnsupportedEncodingException {

        String documentTemplate = "<h1> Document name: " + name + " </h1>"
                + "<h1> Issued by " + manufacturer + "</h1>"
                + "<h1> Document type " + type + "</h1>"
                + "<h1> Expires " + expiryDate + "</h1>";

        String encodedDocumentTemplate = URLEncoder.encode(documentTemplate, StandardCharsets.UTF_8.toString());

        URI url = UriComponentsBuilder.fromHttpUrl("https://htmlpdfapi.com/api/v1/pdf")
                .encode().build().toUri();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("html", encodedDocumentTemplate);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, getAuthorizationHeader());

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return result;

    }

    public HttpHeaders getAuthorizationHeader() {

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authentication", "Token " + token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return headers;

    }
}
