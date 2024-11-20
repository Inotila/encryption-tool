package com.example;

import org.junit.jupiter.api.Test;  // JUnit annotation for defining test methods
import static org.junit.jupiter.api.Assertions.*;  // Assertion used to verify test results

public class EncryptionUtilsTest {

    // Test to ensure encryption and decryption work correctly
    @Test
    public void testEncryptionDecryption() throws Exception {
        // Sample text to test encryption and decryption
        String originalText = "Hello, World!";
        // 16-byte key (AES requires exactly 16 bytes for the key)
        String key = "1234567812345678";

        // Encrypt the original text using the provided key
        String encryptedText = EncryptionUtils.encrypt(originalText, key);

        // Decrypt the encrypted text back to the original text
        String decryptedText = EncryptionUtils.decrypt(encryptedText, key);

        // Assert that the decrypted text matches the original text
        assertEquals(originalText, decryptedText, "Decrypted text should match the original");
    }

    // Test to handle the edge case of an empty string
    @Test
    public void testEmptyString() throws Exception {
        // Test with an empty string
        String originalText = "";
        // 16-byte key
        String key = "1234567812345678";

        // Encrypt the empty string
        String encryptedText = EncryptionUtils.encrypt(originalText, key);

        // Decrypt the encrypted empty string back
        String decryptedText = EncryptionUtils.decrypt(encryptedText, key);

        // Assert that the decrypted text matches the original empty string
        assertEquals(originalText, decryptedText, "Decrypted text should match the original");
    }

    // Test to check if the program throws an exception for an invalid key length
    @Test
    public void testInvalidKeyLength() {
        // Using an invalid key (shorter than 16 bytes)
        String key = "short";

        // Check if an IllegalArgumentException is thrown when attempting to encrypt with this key
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EncryptionUtils.encrypt("text", key);
        });

        // Assert that the exception message is as expected
        assertEquals("Key must be 16 bytes long", exception.getMessage());
    }
}
