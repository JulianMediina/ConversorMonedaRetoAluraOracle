package com.aluracursos.app;

import com.aluracursos.CurrencyOperation;
import com.aluracursos.CurrencyPair;

import java.util.Scanner;

public class CurrencyConverterApp {

    /**
     * Método principal que ejecuta la aplicación de conversión de divisas.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        

        while (running) {
            displayMenu();
            int option = getUserChoice(scanner);

            if (option == 9) {
                running = false;
                continue;
            }

            CurrencyPair currencyPair = processUserInput(option);

            if (currencyPair == null) {
                System.out.println("Opción inválida");
                continue;
            }

            double fromAmount = getUserAmount(scanner);

            try {
                CurrencyOperation currencyOp = new CurrencyOperation(currencyPair, fromAmount);
                System.out.println(currencyOp);
            } catch (IllegalArgumentException e) {
                System.out.println("Error al realizar la conversión: " + e.getMessage());
            }

            System.out.println("\nPresione Enter para continuar...");
            waitForEnter(scanner);
        }

        scanner.close();
    }

    /**
     * Muestra el menú de opciones disponibles.
     */
    private static void displayMenu() {
        System.out.println("=== Conversor de Divisas ===");
        System.out.println("1. USD -> COP");
        System.out.println("2. COP -> USD");
        System.out.println("3. USD -> BRL");
        System.out.println("4. BRL -> USD");
        System.out.println("5. USD -> ARS");
        System.out.println("6. ARS -> USD");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Obtiene la opción seleccionada por el usuario.
     */
    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Opción inválida. Por favor, ingrese un número válido.");
            scanner.next(); // Limpiar el buffer
        }
        return scanner.nextInt();
    }

    /**
     * Procesa la entrada del usuario y devuelve el par de divisas correspondiente.
     */
    private static CurrencyPair processUserInput(int option) {
        switch (option) {
            case 1:
                return new CurrencyPair("USD", "COP");
            case 2:
                return new CurrencyPair("COP", "COP");
            case 3:
                return new CurrencyPair("USD", "BRL");
            case 4:
                return new CurrencyPair("BRL", "USD");
            case 5:
                return new CurrencyPair("USD", "ARS");
            case 6:
                return new CurrencyPair("ARS", "USD");
            default:
                return null;
        }
    }

    /**
     * Obtiene la cantidad de dinero a convertir ingresada por el usuario.
     */
    private static double getUserAmount(Scanner scanner) {
        System.out.print("Ingrese la cantidad a convertir: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Cantidad inválida. Por favor, ingrese un número válido.");
            scanner.next(); // Limpiar el buffer
        }
        return scanner.nextDouble();
    }

    /**
     * Espera a que el usuario presione Enter antes de continuar.
     */
    private static void waitForEnter(Scanner scanner) {
        try {
            scanner.nextLine(); // Consumir la nueva línea pendiente
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine(); // Esperar a que se presione Enter
        } catch (Exception e) {
            System.out.println("Error al esperar la entrada del usuario.");
        }
    }
}
