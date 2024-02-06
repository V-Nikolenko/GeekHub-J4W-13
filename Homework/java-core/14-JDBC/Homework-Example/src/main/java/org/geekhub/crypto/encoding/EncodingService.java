package org.geekhub.crypto.encoding;

import org.geekhub.crypto.encoding.cryptors.DecryptorFactory;
import org.geekhub.crypto.encoding.cryptors.EncryptorFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class EncodingService {

    private final EncryptorFactory encryptorFactory;
    private final DecryptorFactory decryptorFactory;

    public EncodingService(EncryptorFactory encryptorFactory, DecryptorFactory decryptorFactory) {
        this.encryptorFactory = encryptorFactory;
        this.decryptorFactory = decryptorFactory;
    }

    @NonNull
    public String encode(@NonNull EncodingAlgorithm algorithm,
                         @NonNull EncodingOperation operation,
                         @NonNull String message) {
        return switch (operation) {
            case ENCRYPT -> encryptorFactory.getEncryptor(algorithm).encrypt(message);
            case DECRYPT -> decryptorFactory.getDecryptor(algorithm).decrypt(message);
        };
    }
}
