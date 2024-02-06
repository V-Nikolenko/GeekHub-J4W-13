package org.geekhub.crypto.encoding.cryptors.algorithms;

import org.geekhub.crypto.encoding.cryptors.Encryptor;
import org.geekhub.crypto.exceptions.EncodingOperationException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class OneWayEncodingAlgorithm implements Encryptor {

    @NonNull
    @Override
    public String encrypt(@NonNull String inputMessage) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] encodedBytes = digest.digest(inputMessage.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new EncodingOperationException("Error while encoding message", e);
        }
    }
}
