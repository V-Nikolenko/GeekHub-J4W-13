package org.geekhub.crypto.encoding.cryptors.algorithms;

import org.geekhub.crypto.encoding.cryptors.Decryptor;
import org.geekhub.crypto.encoding.cryptors.Encryptor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class VigenereAlgorithm implements Encryptor, Decryptor {

    @NonNull
    @Override
    public String encrypt(@NonNull String inputMessage) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NonNull
    @Override
    public String decrypt(@NonNull String inputMessage) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
