package com.aluracursos;

public record CurrencyPair(String fromCurrency, String toCurrency) {


    /**
     * Devuelve una representación en formato legible del par de divisas (por
     * ejemplo, "EUR to USD").
     */
    @Override
    public String toString() {
        return String.format("%s to %s", fromCurrency, toCurrency);
    }
}
