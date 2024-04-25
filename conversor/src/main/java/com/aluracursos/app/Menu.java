package com.aluracursos.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {
    private final static Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println("=== Menú de Conversión de Moneda ===");
        System.out.println("1. Convertir USD a COP");
        System.out.println("2. Convertir COP a USD");
        System.out.println("3. Convertir USD a BRL");
        System.out.println("4. Convertir BRL a USD");
        System.out.println("5. Convertir USD a ARS");
        System.out.println("6. Convertir ARS a USD");
        System.out.println("7. Convertir otras monedas");
        System.out.println("8. Guardar historial de operaciones");
        System.out.println("9. Salir");
    }

    public static int getUserChoice() {
        System.out.println("Ingrese una opción:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        return choice;
    }

    public double getUserAmount() {
        System.out.println("Ingrese la cantidad a convertir:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer del scanner
        return amount;
    }

    public String getFromCurrency() {
        System.out.println("Ingrese la moneda de origen:");
        return scanner.nextLine().toUpperCase(); // Convertir la entrada a mayúsculas
    }

    public String getToCurrency() {
        System.out.println("Ingrese la moneda de destino:");
        return scanner.nextLine().toUpperCase(); // Convertir la entrada a mayúsculas
    }

    public CurrencyPair processUserInput(int option) {
        return switch (option) {
            case 1 -> new CurrencyPair("USD", "COP");
            case 2 -> new CurrencyPair("COP", "USD");
            case 3 -> new CurrencyPair("USD", "BRL");
            case 4 -> new CurrencyPair("BRL", "USD");
            case 5 -> new CurrencyPair("USD", "ARS");
            case 6 -> new CurrencyPair("ARS", "USD");
            case 7 -> new CurrencyPair(getFromCurrency(), getToCurrency());

            default -> null;
        };
    }

    public void closeScanner() {
        scanner.close();
    }

    public void run() {
        while (true) {
            displayMenu();

            int option = getUserChoice();

            if (option == 8) {
                displayHistory();
                continue;
            } else if (option == 9) {
                return;
            }

            CurrencyPair currencyPair = processUserInput(option);

            while (currencyPair != null) {
                displayOpMenu(currencyPair);
                double fromAmount = getUserAmount();

                if (fromAmount == 0) {
                    break;
                }

                try {
                    CurrencyOperation currencyOp = new CurrencyOperation(currencyPair, fromAmount);
                    System.out.println(currencyOp);
                    currencyOp.writeToFile();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    waitForEnter();
                }
            }
        }
    }

    public static void displayHistory() {
        System.out.println("\nHistorial de operaciones: \n");
        try {
            File myHistory = new File("operations_history.txt");
            Scanner myReader = new Scanner(myHistory);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
        } finally {
            waitForEnter();
        }
    }

    public static void waitForEnter() {
        System.out.println("\nPresione Enter para continuar");
        try {
            System.in.read();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void displayOpMenu(CurrencyPair currencyPair) {
        System.out.println("\nConversion: " + currencyPair.fromCurrency() + " a " + currencyPair.toCurrency() + "\n");
    }
}
