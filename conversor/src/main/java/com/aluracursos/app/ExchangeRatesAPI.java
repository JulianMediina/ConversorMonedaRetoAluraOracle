package com.aluracursos.app;


import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRatesAPI {

    private static final String API_KEY = "9e224286d54fe9026baaa4f2";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final Gson gson = new Gson();
    private static final HttpClient client = HttpClient.newHttpClient();

    public static  double getExchangeRate(CurrencyPair currencyPair) {
        String fromCurrency = currencyPair.fromCurrency();
        String toCurrency = currencyPair.toCurrency();

        try {
            String url = BASE_URL + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ExchangeRate exchangeRate = gson.fromJson(response.body(), ExchangeRate.class);
                return exchangeRate.conversionRate();
            } else {
                System.out.println("Error en la respuesta: " + response.statusCode());
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
            return -1;
        }
    }
}
