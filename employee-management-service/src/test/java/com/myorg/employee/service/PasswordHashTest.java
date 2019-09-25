package com.myorg.employee.service;

import org.junit.Test;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class PasswordHashTest {
    @Test
    public void testMD5HashDelegated() {
        String encodingId = "MD5";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new MessageDigestPasswordEncoder(encodingId));

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(encodingId, encoders);
        for(int i = 0; i < 2; i++)
            out.println(passwordEncoder.encode("password"));
    }

    @Test
    public void testMD5Hash() {
        String encodingId = "MD5";
        PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder(encodingId);
        for(int i = 0; i < 2; i++)
            out.println(passwordEncoder.encode("password"));
    }
}
