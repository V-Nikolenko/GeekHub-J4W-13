package org.geekhub.crypto.encoding.cryptors.algorithms;

import org.geekhub.crypto.encoding.cryptors.Decryptor;
import org.geekhub.crypto.encoding.cryptors.Encryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.function.UnaryOperator;

@Component
public class CaesarAlgorithm implements Encryptor, Decryptor {

    private final int shift;

    public CaesarAlgorithm(@Value("${codec.caesar.shift}") int shift) {
        this.shift = shift;
    }

    @NonNull
    @Override
    public String encrypt(@NonNull String inputMessage) {
        UnaryOperator<Character> encodeShift = (Character character) -> (char) (character + shift);
        return shiftInput(inputMessage, encodeShift);
    }

    @NonNull
    @Override
    public String decrypt(@NonNull String inputMessage) {
        UnaryOperator<Character> decodeShift = (Character character) -> (char) (character - shift);
        return shiftInput(inputMessage, decodeShift);
    }

    private String shiftInput(String inputMessage, UnaryOperator<Character> shiftFunction) {
        if (inputMessage.isBlank()) {
            return "";
        }

        return inputMessage.chars()
            .mapToObj(character -> (char) character)
            .map(shiftFunction)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }
}
