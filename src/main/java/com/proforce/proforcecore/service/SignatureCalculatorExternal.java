package com.proforce.proforcecore.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.io.InputStream;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;


/*String String_to_sign= ExecutionUtil.getDynamicProcessProperty("String_to_sign");
        Day = ExecutionUtil.getDynamicProcessProperty("Date");
        Region = ExecutionUtil.getDynamicProcessProperty("Region");
        Service = ExecutionUtil.getDynamicProcessProperty("Service");
        String secret_key = ExecutionUtil.getDynamicProcessProperty("SecretKey");
// Create a signing key.
        byte[] signing_key = new CalculateSignature().getSignatureKey(secret_key, Day, Region, Service);
// Use the signing key to sign the StringToSign using HMAC-SHA256 signing algorithm.
        byte[] signature_bytes = new CalculateSignature().HmacSHA256(String_to_sign, signing_key);
        String signature = new CalculateSignature().convertbyte(signature_bytes);
        String signature1 = new CalculateSignature().convertbyte(signing_key);*/

public class SignatureCalculatorExternal {
    public static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        byte[] kSecret = ("AWS4" + key).getBytes("utf-8");
        byte[] kDate = HmacSHA256(dateStamp, kSecret);
        byte[] kRegion = HmacSHA256(regionName, kDate);
        byte[] kService = HmacSHA256(serviceName, kRegion);
        byte[] kSigning = HmacSHA256("aws4_request", kService);
        return kSigning;
    }
    public static byte[] HmacSHA256(String data, byte[] key) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(data.getBytes("utf-8"));
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
/*ExecutionUtil.setDynamicProcessProperty("Signature", signature, false);
        for( int i = 0; i < dataContext.getDataCount(); i++ ) {
        InputStream is = dataContext.getStream(i);
        Properties props = dataContext.getProperties(i);
        dataContext.storeStream(is, props);
        }‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍‍*/

