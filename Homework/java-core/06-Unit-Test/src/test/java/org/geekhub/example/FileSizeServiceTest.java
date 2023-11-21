package org.geekhub.example;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.geekhub.example.exception.FileSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileSizeServiceTest {
    private static final URI TEST_URI = URI.create("test");

    @Mock
    private HttpClient httpClient;

    private FileSizeService fileSizeService;

    @BeforeEach
    void setUp() {
        fileSizeService = new FileSizeService(httpClient);
    }

    @Test
    void getExpectedBase64Size() throws IOException {
        var httpResponse = mock(HttpResponse.class);
        var header = mock(Header.class);

        when(httpResponse.getFirstHeader(HttpHeaders.CONTENT_LENGTH)).thenReturn(header);
        when(header.getValue()).thenReturn("1000");
        when(httpClient.execute(any())).thenReturn(httpResponse);

        assertThat(fileSizeService.getExpectedBase64Size(TEST_URI)).isEqualTo(1000 / 3 * 4);
    }

    @Test
    void getExpectedBase64Size_whenClientThrowException_shouldThrowFileSizeException() throws IOException {
        when(httpClient.execute(any())).thenThrow(new RuntimeException("testmessage"));

        assertThatCode(() -> fileSizeService.getExpectedBase64Size(TEST_URI))
            .isInstanceOf(FileSizeException.class)
            .hasMessage("Fail to get expected file size: testmessage");
    }

    @Test
    void getExpectedBase64Size_whenContentLengthIsNull_shouldThrowFileSizeException() throws IOException {
        var httpResponse = mock(HttpResponse.class);

        when(httpResponse.getFirstHeader(HttpHeaders.CONTENT_LENGTH)).thenReturn(null);
        when(httpClient.execute(any())).thenReturn(httpResponse);

        assertThatCode(() -> fileSizeService.getExpectedBase64Size(TEST_URI))
            .isInstanceOf(FileSizeException.class)
            .hasMessage("Content Length Header is null");
    }
}
