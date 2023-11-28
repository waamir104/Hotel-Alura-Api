package dev.waamir.hotelaluraapi.infrastructure.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiException;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    private SecureRandom random = new SecureRandom();
    private byte[] salt = new byte[16];
    private int iterations = 10000;
    private int keyLength = 200;
    private boolean isSalted = false;

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            verifySalted();
            String hashedPassword = encodePBKDF2(rawPassword.toString(), salt, iterations, keyLength);
            return hashedPassword;
        } catch (Exception e) {
            throw new ApiException("Error");
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            verifySalted();
            boolean isMatch = matchPBKDF2(rawPassword.toString(), encodedPassword, salt, iterations, keyLength);
            return isMatch;
        } catch (Exception e) {
            throw new ApiException("Error");
        }
    }

    private boolean matchPBKDF2(String rawPassword, String hashedPassword, byte[] salt, int iterations, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String newHashedPassword = encodePBKDF2(rawPassword, salt, iterations, keyLength);
        return hashedPassword.equals(newHashedPassword);
    }

    private String encodePBKDF2(String rawPassword, byte[] salt, int iterations, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hashedPassword = skf.generateSecret(spec).getEncoded();
        return bytesToHex(hashedPassword);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    private void verifySalted() {
        if (isSalted == false) {
            random.nextBytes(salt);
            isSalted = true;
        }
    }
}
