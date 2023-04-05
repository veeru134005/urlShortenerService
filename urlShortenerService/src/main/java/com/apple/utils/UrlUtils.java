package com.apple.utils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;

@Component
public class UrlUtils {
	public static final String ALPHABET = "Mheo9PI2qNs5Zpf80TBn7lmRbtQ4YKXHvwAEWxuzdra316OJigGLSVUCyFjkDc";
	public String generateShortUrl(String url)
    {
        String encodeShrtUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodeShrtUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()+ALPHABET), StandardCharsets.UTF_8)
                .toString();
        return  encodeShrtUrl;
    }
}
