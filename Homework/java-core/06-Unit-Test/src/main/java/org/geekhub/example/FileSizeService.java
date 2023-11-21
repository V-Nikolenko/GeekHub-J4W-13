package org.geekhub.example;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.geekhub.example.exception.FileSizeException;

import java.net.URI;

public class FileSizeService {
    private final HttpClient httpClient;

    public FileSizeService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public long getExpectedBase64Size(URI uri) {
        HttpResponse response;
        try {
            response = httpClient.execute(new HttpHead(uri));
        } catch (Exception e) {
            throw new FileSizeException("Fail to get expected file size: " + e.getMessage());
        }

        var contentLengthHeader = response.getFirstHeader(HttpHeaders.CONTENT_LENGTH);
        if (contentLengthHeader == null) throw new FileSizeException("Content Length Header is null");

        return Long.parseLong(contentLengthHeader.getValue()) / 3 * 4;
    }
}
