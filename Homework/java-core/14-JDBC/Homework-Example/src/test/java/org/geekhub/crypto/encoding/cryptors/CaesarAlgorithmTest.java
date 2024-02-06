package org.geekhub.crypto.encoding.cryptors;

import org.geekhub.crypto.encoding.cryptors.algorithms.CaesarAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaesarAlgorithmTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void encrypt_shouldReturnEmptyString_whenMessageIsEmpty(String message) {
        CaesarAlgorithm caesarEncryption = new CaesarAlgorithm(3);

        String encryptedMessage = caesarEncryption.encrypt(message);

        assertEquals("", encryptedMessage);
    }

    @Test
    void encrypt_shouldReturnSameMessage_whenShiftKeyIsZero() {
        String message = "Hello, World!";
        CaesarAlgorithm caesarEncryption = new CaesarAlgorithm(0);

        String encryptedMessage = caesarEncryption.encrypt(message);

        assertEquals(message, encryptedMessage);
    }

    @Test
    void encrypt_shouldReturnEncryptedMessage_whenShiftKeyIsNotZero() {
        String message = "Hello, World!";
        CaesarAlgorithm caesarEncryption = new CaesarAlgorithm(3);

        String encryptedMessage = caesarEncryption.encrypt(message);

        assertEquals("Khoor/#Zruog$", encryptedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void decrypt_shouldReturnEmptyString_whenMessageIsEmpty(String message) {
        CaesarAlgorithm caesarEncryption = new CaesarAlgorithm(3);

        String decryptedMessage = caesarEncryption.decrypt(message);

        assertEquals("", decryptedMessage);
    }

    @Test
    void decrypt_shouldReturnSameMessage_whenShiftKeyIsZero() {
        String message = "Hello, World!";
        CaesarAlgorithm caesarEncryption = new CaesarAlgorithm(0);

        String decryptedMessage = caesarEncryption.decrypt(message);

        assertEquals(message, decryptedMessage);
    }

    @Test
    void decrypt_shouldReturnDecryptedMessage_whenShiftKeyIsNotZero() {
        String message = "Khoor/#Zruog$";
        CaesarAlgorithm caesarEncryption = new CaesarAlgorithm(3);

        String decryptedMessage = caesarEncryption.decrypt(message);

        assertEquals("Hello, World!", decryptedMessage);
    }
}
