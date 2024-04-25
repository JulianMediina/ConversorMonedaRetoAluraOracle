package com.aluracursos.app;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class CurrencyOperation {
    private LocalDateTime datetime;
    private CurrencyPair currencyPair;
    private double exchangeRate;
    private double fromAmount;
    private double toAmount;

    public CurrencyOperation(CurrencyPair currencyPair, double fromAmount) {
        this.datetime = LocalDateTime.now();
        this.currencyPair = currencyPair;
        this.exchangeRate = getExchangeRate(currencyPair);

        if (exchangeRate == -1) {
            throw new IllegalArgumentException("No se pudo obtener el tipo de cambio para " + currencyPair);
        }

        this.fromAmount = fromAmount;
        this.toAmount = fromAmount * exchangeRate;
    }

    private double getExchangeRate(CurrencyPair currencyPair) {
        double rate = ExchangeRatesAPI.getExchangeRate(currencyPair);
        if (rate == -1) {
            throw new IllegalArgumentException("No se pudo obtener el tipo de cambio para " + currencyPair);
        }
        return rate;
    }

    @Override
    public String toString() {
        String formattedDateTime = datetime.toString();
        String fromCurrency = currencyPair.fromCurrency();
        String toCurrency = currencyPair.toCurrency();
        return String.format("%s | %.2f %s | %.2f %s | %s: %.2f", formattedDateTime, fromAmount, fromCurrency, toAmount,
                toCurrency, currencyPair, exchangeRate);
    }

    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter("operations_history.txt", true);
            myWriter.write(this.toString() + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo");
        }
    }
}
