package com.aluracursos;

public record ExchangeRate(String baseCode, String targetCode, double conversionRate) {

    /**
     * Crea una instancia de ExchangeRate con el código base, el código objetivo y
     * la tasa de conversión.
     * 
     * @param baseCode       El código de la moneda base.
     * @param targetCode     El código de la moneda objetivo.
     * @param conversionRate La tasa de conversión entre la moneda base y la moneda
     *                       objetivo.
     */
    public ExchangeRate {
        // El cuerpo del constructor puede estar vacío porque record genera
        // automáticamente el constructor.
    }

    /**
     * Devuelve una representación en formato legible de la tasa de cambio (por
     * ejemplo, "USD to EUR: 1.23").
     */
    @Override
    public String toString() {
        return String.format("%s to %s: %.2f", baseCode, targetCode, conversionRate);
    }
}
