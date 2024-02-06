package org.geekhub.crypto.encoding.cryptors;

import org.springframework.lang.NonNull;

public interface Encryptor {

    @NonNull
    String encrypt(@NonNull String inputMessage);
}
