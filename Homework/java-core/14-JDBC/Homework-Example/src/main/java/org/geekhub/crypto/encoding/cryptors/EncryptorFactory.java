package org.geekhub.crypto.encoding.cryptors;

import org.geekhub.crypto.encoding.EncodingAlgorithm;
import org.geekhub.crypto.encoding.cryptors.algorithms.CaesarAlgorithm;
import org.geekhub.crypto.encoding.cryptors.algorithms.OneWayEncodingAlgorithm;
import org.geekhub.crypto.encoding.cryptors.algorithms.VigenereAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class EncryptorFactory {

    private final CaesarAlgorithm caesarEncryption;
    private final VigenereAlgorithm vigenereEncryption;
    private final OneWayEncodingAlgorithm oneWayEncodingAlgorithm;

    public EncryptorFactory(CaesarAlgorithm caesarEncryption,
                            VigenereAlgorithm vigenereEncryption,
                            OneWayEncodingAlgorithm oneWayEncodingAlgorithm) {
        this.caesarEncryption = caesarEncryption;
        this.vigenereEncryption = vigenereEncryption;
        this.oneWayEncodingAlgorithm = oneWayEncodingAlgorithm;
    }

    public Encryptor getEncryptor(EncodingAlgorithm type) {
        return switch (type) {
            case CAESAR -> caesarEncryption;
            case VIGENERE -> vigenereEncryption;
            case SHA256 -> oneWayEncodingAlgorithm;
        };
    }
}
