package io.bitmax.api;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JavaAuthClient {

    private String baseUrl;
    private String apiKey;
    private String secretKey;
    private Mac hmac;
    private byte[] hmacKey;
    private SecretKeySpec keySpec;

    public JavaAuthClient(String baseUrl, String apiKey, String secretKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secretKey = secretKey;

        hmacKey = Base64.getDecoder().decode(secretKey);
        try {
            hmac = Mac.getInstance("HmacSHA256");
            keySpec = new SecretKeySpec(hmacKey, "HmacSHA256");
            hmac.init(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getHeaderMap(String url, long timestamp) {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-auth-key", apiKey);
        headers.put("x-auth-signature", generateSig(url, timestamp));
        headers.put("x-auth-timestamp", timestamp + "");
        return headers;
    }

    private String generateSig(String url, long timestamp) {
        String prehash = timestamp + "+" + url;
        byte[] encoded = Base64.getEncoder().encode(hmac.doFinal(prehash.getBytes(StandardCharsets.UTF_8)));
        return new String(encoded);
    }
}