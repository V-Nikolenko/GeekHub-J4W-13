package org.geekhub.hw6;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatFactServiceTest {
    private static final String CAT_FACT_JSON = "{\"fact\": \"test fact\"}";

    @Mock
    private CloseableHttpClient httpClient;

    private CatFactService catFactService;

    @BeforeEach
    void setUp() {
        catFactService = new CatFactService(
            httpClient, new Gson()
        );
    }

    @Test
    void getRandomCatFact() throws CatFactException, IOException {
        var response = mock(CloseableHttpResponse.class);
        when(httpClient.execute(any())).thenReturn(response);
        when(response.getEntity()).thenReturn(new ByteArrayEntity(CAT_FACT_JSON.getBytes()));

        assertThat(catFactService.getRandomCatFact()).isEqualTo("test fact");
    }

    @Test
    void getRandomCatFact_whenHttpClientThrowsException() throws IOException {
        var exception = new RuntimeException("test");
        when(httpClient.execute(any())).thenThrow(exception);

        assertThatCode(() -> catFactService.getRandomCatFact())
            .isInstanceOf(CatFactException.class)
            .hasMessage("Fail to fetch new cat fact", exception);
    }
}
