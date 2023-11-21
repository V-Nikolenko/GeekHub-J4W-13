package org.geekhub.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.geekhub.example.exception.FileContentException;

import java.net.URI;

public class FileContentService {
    private final HttpClient httpClient;

    public FileContentService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public byte[] getFileContent(URI uri) {
        HttpResponse fileContent;
        try {
            fileContent = httpClient.execute(new HttpGet(uri));
        } catch (Exception e) {
            throw new FileContentException("Failed to get file content: " + e.getMessage());
        }

        var statusCode = fileContent.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new FileContentException(String.format("Invalid status Code: " + statusCode));
        }

        try (var inputStream = fileContent.getEntity().getContent()) {
            return inputStream.readAllBytes();
        } catch (Exception e) {
            throw new FileContentException("Failed to read content bytes: " + e.getMessage());
        }
    }
}
