package org.geekhub.hw5;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContentValidator {

    private static final int CONTENT_LENGTH_IS_NOT_KNOWN = -1;

    private final int maxFileSize;

    public ContentValidator(int maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public boolean isValid(URL url, String pathToFile, String filename)
        throws FileExistException, LimitSizeException, ContentLengthNotKnownException, IOException {
        URLConnection urlConnection = url.openConnection();
        int contentLength = urlConnection.getContentLength();

        hasContent(contentLength, url);
        validateContentLength(contentLength, url);
        isExistFile(pathToFile, filename, url);

        return true;
    }

    //TODO-10 ADD correct throws AND REMOVE THIS MESSAGE
    private void hasContent(int contentLength, URL url) {
        if (contentLength == CONTENT_LENGTH_IS_NOT_KNOWN) {
            //TODO-11 ADD correct throw and message AND REMOVE THIS MESSAGE
        }
    }

    //TODO-12 ADD correct throws AND REMOVE THIS MESSAGE
    private void validateContentLength(int contentLength, URL url) {
        if (contentLength > maxFileSize) {
            //TODO-13 ADD correct throw and message AND REMOVE THIS MESSAGE
        }
    }

    //TODO-14 ADD correct throws AND REMOVE THIS MESSAGE
    private void isExistFile(String pathToFile, String filename, URL url) {
        if (Files.exists(Path.of(pathToFile, filename))) {
            //TODO-15 ADD correct throw and message AND REMOVE THIS MESSAGE
        }
    }
}
