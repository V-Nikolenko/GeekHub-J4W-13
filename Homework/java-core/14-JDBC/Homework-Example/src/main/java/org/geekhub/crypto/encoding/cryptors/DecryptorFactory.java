package org.geekhub.crypto.encoding.cryptors;

import org.geekhub.crypto.encoding.EncodingAlgorithm;
import org.geekhub.crypto.encoding.cryptors.algorithms.CaesarAlgorithm;
import org.geekhub.crypto.encoding.cryptors.algorithms.VigenereAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class DecryptorFactory {

    private final CaesarAlgorithm caesarEncryption;
    private final VigenereAlgorithm vigenereEncryption;

    public DecryptorFactory(CaesarAlgorithm caesarEncryption,
                            VigenereAlgorithm vigenereEncryption) {
        this.caesarEncryption = caesarEncryption;
        this.vigenereEncryption = vigenereEncryption;
    }

    public Decryptor getDecryptor(EncodingAlgorithm type) {
        return switch (type) {
            case CAESAR -> caesarEncryption;
            case VIGENERE -> vigenereEncryption;
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + type);
        };
    }
}
