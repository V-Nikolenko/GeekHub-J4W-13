package org.geekhub.hw5;

import org.geekhub.hw5.exception.ContentLengthNotKnownException;
import org.geekhub.hw5.exception.FileExistException;
import org.geekhub.hw5.exception.LimitSizeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContentValidatorTest {

    private static final int MAX_FILE_SIZE = 10485760;
    private static final String PATH_TO_FILE = "file:./src/test/resources/Браття українці.mp3";
    private static final String FILE_NAME = "Браття українці.mp3";

    @Test
    void isValidWhenLinkWithContentShouldReturnTrue()
        throws LimitSizeException, ContentLengthNotKnownException, FileExistException, IOException {
        ContentValidator validator = new ContentValidator(MAX_FILE_SIZE);
        URL url = new URL(PATH_TO_FILE);

        boolean actualResult = validator.isValid(url, "", FILE_NAME);

        assertTrue(actualResult);
    }

    @Test
    void isValidWhenLinkWithoutContentShouldThrowException() throws MalformedURLException {
        ContentValidator validator = new ContentValidator(MAX_FILE_SIZE);
        URL url = new URL("http://test");

        ContentLengthNotKnownException exception = assertThrows(ContentLengthNotKnownException.class, () ->
            validator.isValid(url, null, FILE_NAME));

        String expectedMessage = "Cannot download file from url: http://test\n";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void isValidWhenFileSizeMoreThanLimitShouldThrowException() throws MalformedURLException {
        ContentValidator validator = new ContentValidator(10);
        URL url = new URL(PATH_TO_FILE);

        LimitSizeException exception = assertThrows(LimitSizeException.class, () ->
            validator.isValid(url, null, FILE_NAME));

        String expectedMessage = "Failed to download from url: file:./src/test/resources/Браття українці.mp3 over 10\n";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
