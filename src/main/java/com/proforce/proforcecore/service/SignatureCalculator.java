package com.proforce.proforcecore.service;

import com.google.api.client.util.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import software.amazon.awssdk.auth.signer.params.AwsS3V4SignerParams;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class SignatureCalculator {

    @Autowired
    private AwsDateStringBuilder dateStringBuilder;

    public String getSignature(byte[] SigningKey, String StringToSign) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {


        return Hex.encodeHexString(HmacSHA256(StringToSign, SigningKey));
    }

    public byte[] getSigningKey(String secretAccessKey) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] kSecret = ("AWS4" + secretAccessKey).getBytes("utf-8");
        byte[] kDate = HmacSHA256(dateStringBuilder.getDateString(), kSecret);
        byte[] kRegion = HmacSHA256("us-east-1", kDate);
        byte[] kService = HmacSHA256("s3", kRegion);
        byte[] kSigning = HmacSHA256("aws4_request", kService);
        return kSigning;

    }

    public String getStringToSign() throws NoSuchAlgorithmException {

        String result =  "AWS4-HMAC-SHA256" + "\n"
                + dateStringBuilder.getDateTimeString() +"\n"
                + dateStringBuilder.getDateString() +"/us-east-1/s3/aws4_request" + "\n"
                + DigestUtils.sha256Hex(getCanonicalRequest());

        return result;
    }

    public String getCanonicalRequest() {



        String result =  "GET" + "\n"
                + "https://s3.amazonaws.com" + "\n"
                + "host:s3.amazonaws.com" + "\n"
                + "x-amz-content-sha256:" + DigestUtils.sha256Hex("") + "\n"
                + "x-amz-date:" + dateStringBuilder.getDateTimeString() + "\n"
                + "host;x-amz-content-sha256;x-amz.date" + "\n"
                + DigestUtils.sha256Hex("");

        return result;

    }

    public static byte[] HmacSHA256(String data, byte[] key) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(data.getBytes("utf-8"));
    }

    public static String UriEncode(CharSequence input, boolean encodeSlash) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-' || ch == '~' || ch == '.') {
                result.append(ch);
            } else if (ch == '/') {
                result.append(encodeSlash ? "%2F" : ch);
            } else {
                result.append(Hex.encodeHex(ByteBuffer.allocateDirect(ch)));
            }
        }
        return result.toString();
    }

}
