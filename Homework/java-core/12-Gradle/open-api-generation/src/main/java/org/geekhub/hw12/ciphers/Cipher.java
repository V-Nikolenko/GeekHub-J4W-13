package org.geekhub.hw12.ciphers;

public interface Cipher<DTO> {

    String encode(DTO encodeData);

    String decode(DTO decodeData);

}
