package org.geekhub.hw6;

import com.google.gson.Gson;
import org.apache.http.impl.client.HttpClientBuilder;

public class Main {
    public static void main(String[] args) throws CatFactException {
        var httpClient = HttpClientBuilder.create().build();
        var catFactService = new CatFactService(httpClient, new Gson());

        System.out.println(catFactService.getRandomCatFact());
    }
}
