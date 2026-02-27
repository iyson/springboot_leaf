package com.example.demo.password;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final String ALL = UPPER + LOWER + DIGITS + SPECIAL;


    
    public static String getSecureString(String password, String salt) {

        String generatedString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedString = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedString;
    }
    
    public static String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        String salt = new String(Base64.getEncoder().encode(bytes));
        return salt;
    }

    public static String generate(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // 최소한 1개씩은 각 종류가 포함되게(강도 향상)
        sb.append(UPPER.charAt(random.nextInt(UPPER.length())));
        sb.append(LOWER.charAt(random.nextInt(LOWER.length())));
        sb.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        sb.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        // 남은 길이만큼 ALL에서 무작위 선택
        for (int i = sb.length(); i < length; i++) {
            sb.append(ALL.charAt(random.nextInt(ALL.length())));
        }

        // 셔플로 순서 섞기(더 강력)
        char[] pwd = sb.toString().toCharArray();
        for (int i = 0; i < pwd.length; i++) {
            int j = random.nextInt(pwd.length);
            char tmp = pwd[i];
            pwd[i] = pwd[j];
            pwd[j] = tmp;
        }

        return new String(pwd);
    }

}
