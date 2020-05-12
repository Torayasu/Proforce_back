package com.proforce.proforcecore.client;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.proforce.proforcecore.domain.ExpiryReminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class TodoLyClient {

    private String email = "torayasuamari@gmail.com";

    private String password = "Darketernal1";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> getItems() {
        URI url = UriComponentsBuilder.fromHttpUrl("https://todo.ly/api/items.json").encode().build().toUri();

        HttpEntity<String> entity = new HttpEntity<>(getAuthorizationHeader());

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return result;
    }

    public ResponseEntity<String> getProjects() {
        URI url = UriComponentsBuilder.fromHttpUrl("https://todo.ly/api/projects.json").encode().build().toUri();

        HttpEntity<String> entity = new HttpEntity<>(getAuthorizationHeader());

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return result;
    }

    public ResponseEntity<String> addReminderToList(ExpiryReminder expiryReminder) {

        URI url = UriComponentsBuilder.fromHttpUrl("https://todo.ly/api/item.json").encode().build().toUri();

        HttpHeaders headers = getAuthorizationHeader();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, String> body = new HashMap<>();
        body.put("Reminder", "Doc with Id " + expiryReminder.getDocId() + " will expire on " + expiryReminder.getMessage());

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(url, entity, String.class );

        return result;

    }

    public HttpHeaders getAuthorizationHeader() {

        HttpHeaders headers = new HttpHeaders();

        String auth = email + ":" + password;

        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")) );

        headers.add("Authorization", "Basic " + new String(encodedAuth));

        return headers;

    }
}
