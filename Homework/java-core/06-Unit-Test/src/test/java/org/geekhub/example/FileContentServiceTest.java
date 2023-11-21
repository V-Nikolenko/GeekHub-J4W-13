package org.geekhub.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.geekhub.example.exception.FileContentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileContentServiceTest {
    private static final URI TEST_URI = URI.create("test");

    @Mock
    private HttpClient httpClient;

    private FileContentService fileContentService;

    @BeforeEach
    void setUp() {
        fileContentService = new FileContentService(httpClient);
    }

    @Test
    void getFileContent() throws IOException {
        var httpResponse = mock(HttpResponse.class);
        var statusLine = mock(StatusLine.class);
        var entity = mock(HttpEntity.class);
        var bytes = new byte[]{1, 2, 3};

        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpClient.execute(any())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(bytes));

        assertThat(fileContentService.getFileContent(TEST_URI)).isEqualTo(bytes);
    }

    @Test
    void getFileContent_whenHttpClientThrowsException_shouldThrowFileContentException() throws IOException {
        when(httpClient.execute(any())).thenThrow(new RuntimeException("test"));

        assertThatCode(() -> fileContentService.getFileContent(TEST_URI))
            .isInstanceOf(FileContentException.class)
            .hasMessage("Failed to get file content: test");
    }

    @Test
    void getFileContent_whenNot200Status_shouldThrowFileContentException() throws IOException {
        var httpResponse = mock(HttpResponse.class);
        var statusLine = mock(StatusLine.class);

        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(404);
        when(httpClient.execute(any())).thenReturn(httpResponse);

        assertThatCode(() -> fileContentService.getFileContent(TEST_URI))
            .isInstanceOf(FileContentException.class)
            .hasMessage("Invalid status Code: 404");
    }

    @Test
    void getFileContent_whenStreamThrowsIoException_shouldThrowFileContentException() throws IOException {
        var httpResponse = mock(HttpResponse.class);
        var statusLine = mock(StatusLine.class);
        var entity = mock(HttpEntity.class);

        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpClient.execute(any())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenThrow(new RuntimeException("test"));

        assertThatCode(() -> fileContentService.getFileContent(TEST_URI))
            .isInstanceOf(FileContentException.class)
            .hasMessage("Failed to read content bytes: test");
    }
}
