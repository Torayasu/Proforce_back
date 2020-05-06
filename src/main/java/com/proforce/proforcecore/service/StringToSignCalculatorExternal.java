package com.proforce.proforcecore.service;

import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.io.InputStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class StringToSignCalculatorExternal {

    public static byte[] HmacSHA256(byte[] key) throws NoSuchAlgorithmException {
        MessageDigest mac = MessageDigest.getInstance("SHA-256");
        byte[] signatureBytes = mac.digest(key);
        return signatureBytes;
    }
    public static String convertbyte(byte[] bytes) {
        StringBuffer hexString = new StringBuffer();
        for (int j=0; j<bytes.length; j++) {
            String hex=Integer.toHexString(0xff & bytes[j]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
/*
for( int i = 0; i < dataContext.getDataCount(); i++ ) {
        InputStream is = dataContext.getStream(i);
        Properties props = dataContext.getProperties(i);
// Acquire applicable Properties
        Day = ExecutionUtil.getDynamicProcessProperty("Date");
        Input = ExecutionUtil.getDynamicProcessProperty("Payload");
        Region = ExecutionUtil.getDynamicProcessProperty("Region");
        Service = ExecutionUtil.getDynamicProcessProperty("Service");
        AccessKey = ExecutionUtil.getDynamicProcessProperty("Access_Key_ID");
        version = version number;
        Date now= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        TimeZone utc = TimeZone.getTimeZone("UTC");
        sdf.setTimeZone(utc);
        CDT = (sdf.format(now)).toString();
// Build CanonicalHeaders inputs for CanonicalRequest
        CanonicalHeaders_line1 = "content-type:application/x-www-form-urlencoded";
        CanonicalHeaders_line2 = "host:ec2.amazonaws.com";
        CanonicalHeaders_line3 = "x-amz-date:"+CDT;
// Build CanonicalRequest
        Request_Method = "GET";
        CanonicalURI = "/";
        CanonicalQueryString = "Action=DescribeRegions&Version=version";
        CanonicalHeaders = CanonicalHeaders_line1 + "\n" + CanonicalHeaders_line2 + "\n" + CanonicalHeaders_line3 + "\n";
        SignedHeaders_line = "content-type;host;x-amz-date";
        byte[] HashedPayload_bytes = new SignString().HmacSHA256(Input.getBytes("UTF-8"));
        HashedPayload = new SignString().convertbyte(HashedPayload_bytes);
        CanonicalRequest = Request_Method + "\n" + CanonicalURI + "\n" + CanonicalQueryString + "\n" + CanonicalHeaders + "\n" + SignedHeaders_line + "\n" + HashedPayload;
// Calculate String to sign
        Signing_algorithm = "AWS4-HMAC-SHA256";
        RequestDate = CDT;
        CredentialScope = Day+"/"+Region+"/"+Service+"/aws4_request";
        byte[] HashedCanonicalRequest_bytes = new SignString().HmacSHA256(CanonicalRequest.getBytes("UTF-8"));
        HashedCanonicalRequest = new SignString().convertbyte(HashedCanonicalRequest_bytes);
        string_to_sign = Signing_algorithm+"\n"+RequestDate+"\n"+CredentialScope+"\n"+HashedCanonicalRequest;
        ExecutionUtil.setDynamicProcessProperty("String_to_sign",string_to_sign, false);
        dataContext.storeStream(is, props);
        }‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍*/
