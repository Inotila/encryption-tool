package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionUtilsTest {
    @Test
    public void testEncryptionDecryption() throws Exception {
        String originalText = "Hello, World!";
        String key = "1234567812345678"; // 16-byte key

        String encryptedText = EncryptionUtils.encrypt(originalText, key);
        String decryptedText = EncryptionUtils.decrypt(encryptedText, key);

        assertEquals(originalText, decryptedText, "Decrypted text should match the original");
    }

    @Test
    public void testEmptyString() throws Exception {
        String originalText = "";
        String key = "1234567812345678"; // 16-byte key

        String encryptedText = EncryptionUtils.encrypt(originalText, key);
        String decryptedText = EncryptionUtils.decrypt(encryptedText, key);

        assertEquals(originalText, decryptedText, "Decrypted text should match the original");
    }

    @Test
    public void testInvalidKeyLength() {
        String key = "short";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EncryptionUtils.encrypt("text", key);
        });

        assertEquals("Key must be 16 bytes long", exception.getMessage());
    }
}