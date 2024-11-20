package com.example.controller;

import com.example.EncryptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EncryptionController {

    @PostMapping("/encrypt")
    public ResponseEntity<Map<String, String>> encrypt(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        String key = payload.get("key");

        try {
            String encryptedText = EncryptionUtils.encrypt(text, key);
            Map<String, String> response = new HashMap<>();
            response.put("encryptedText", encryptedText);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        String key = payload.get("key");

        try {
            String decryptedText = EncryptionUtils.decrypt(text, key);
            Map<String, String> response = new HashMap<>();
            response.put("decryptedText", decryptedText);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
