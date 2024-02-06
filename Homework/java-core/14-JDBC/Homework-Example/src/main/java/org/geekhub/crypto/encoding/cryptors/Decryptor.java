package org.geekhub.crypto.encoding.cryptors;

import org.springframework.lang.NonNull;

public interface Decryptor {

    @NonNull
    String decrypt(@NonNull String inputMessage);
}
