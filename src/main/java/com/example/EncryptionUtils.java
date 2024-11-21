package com.example;

import javax.crypto.Cipher;  // For encryption and decryption operations
import javax.crypto.spec.SecretKeySpec;  // For creating a key specification based on the encryption algorithm
import java.util.Base64;  // For encoding and decoding data into Base64 format (to make encrypted data readable)

public class EncryptionUtils {
    // Define the encryption algorithm constant which is Advanced Encryption Standard
    private static final String ALGORITHM = "AES";

    // Method to encrypt data using AES encryption
    public static String encrypt(String data, String key) throws Exception {
        // AES requires this key length
        if (key.length() != 16) {
            throw new IllegalArgumentException("Key must be 16 bytes long");
        }

        // Create a SecretKeySpec using the key bytes and the AES algorithm
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

        // Get an instance of the AES cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Initialize the cipher in ENCRYPT_MODE, using the secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Perform the encryption on the input data
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        // Encode the encrypted bytes to Base64 for easy representation
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt data using AES decryption
    public static String decrypt(String encryptedData, String key) throws Exception {
        // AES requires this key length
        if (key.length() != 16) {
            throw new IllegalArgumentException("Key must be 16 bytes long");
        }

        // Create a SecretKeySpec using the key bytes and the AES algorithm
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

        // Get an instance of the AES cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Initialize the cipher in DECRYPT_MODE, using the secret key
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decode the encrypted data from Base64 to bytes
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        // Convert the decrypted bytes back to a string and return it
        return new String(decryptedBytes);
    }
}
