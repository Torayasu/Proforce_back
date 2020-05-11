package com.proforce.proforcecore.client;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.proforce.proforcecore.config.CoreConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Service
public class PDFOtterClient {

    private String APIKey = "test_7AE5HME1GFKdfrezb2tMEziCdJrfqnuh";

    private String password = "Darketernal1";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> getAllTemplates() {

        URI url = UriComponentsBuilder.fromHttpUrl("https://www.pdfotter.com/api/v1/pdf_templates").encode().build().toUri();

        HttpEntity<String> entity = new HttpEntity<>(getAuthorizationHeader());

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return result;

    }

    public ResponseEntity<String> fillInATemplate() {

        URI url = UriComponentsBuilder.fromHttpUrl("https://www.pdfotter.com/api/v1/pdf_templates/tem_wfHBSxpSELCfNG/fill").encode().build().toUri();

        HttpEntity<String> entity = new HttpEntity<>(getAuthorizationHeader());

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return result;

    }

    public HttpHeaders getAuthorizationHeader() {

        HttpHeaders headers = new HttpHeaders();

        String auth = APIKey+ ":" + password;

        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")) );

        headers.add("Authorization", "Basic " + new String(encodedAuth));

        return headers;

    }
}
