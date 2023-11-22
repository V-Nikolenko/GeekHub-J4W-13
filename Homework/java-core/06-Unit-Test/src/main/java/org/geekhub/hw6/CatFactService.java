package org.geekhub.hw6;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class CatFactService {
    private static final String CAT_FACT_URL = "https://catfact.ninja/fact";

    private final HttpClient httpClient;
    private final Gson gson;

    public CatFactService(
        HttpClient httpClient,
        Gson gson
    ) {
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public String getRandomCatFact() throws CatFactException {
        try {
            var result = httpClient.execute(new HttpGet(CAT_FACT_URL));
            try (var inputStream = result.getEntity().getContent()) {
                var json = new String(inputStream.readAllBytes());
                return gson.fromJson(json, JsonObject.class).get("fact").getAsString();
            }
        } catch (Exception e) {
            throw new CatFactException("Fail to fetch new cat fact", e);
        }
    }
}
