package com.tiago.csv2json.converters;

import com.tiago.csv2json.converters.csv.CsvService;
import com.tiago.csv2json.converters.json.JsonService;

import java.util.Scanner;

public class ConvertersAppMenuControl {
    private static final Scanner scanner = new Scanner(System.in);

    public static void appMenuControl() {
        while (true) {
            menu();

            System.out.print("\nEnter an option: ");
            String option = scanner.nextLine();

            if (option.equals("0")) {
                break;
            }

            switch (option) {
                case "1":
                    menuTypeCsv();

                    System.out.print("\nEnter an option: ");
                    String optionCsv = scanner.nextLine();

                    if (optionCsv.equals("0")) {
                        break;
                    }

                    CsvService.csvOptions(optionCsv, scanner);
                    break;
                case "2":
                    menuTypeJson();

                    System.out.print("\nEnter an option: ");
                    String optionJson = scanner.nextLine();

                    if (optionJson.equalsIgnoreCase("0")) {
                        break;
                    }

                    JsonService.jsonOptions(optionJson, scanner);
                    break;
                default:
                    System.out.println("Invalid option...");
                    break;
            }
        }
    }

    private static void menu() {
        String menuBar = "*".repeat(40);

        System.out.println("\n" + menuBar);
        System.out.printf("== Choice the type of converter ==%n" +
                "   1 - Converter Csv to Json%n" +
                "   2 - Converter Json to Csv%n" +
                "   0 - Exit%n");
        System.out.println(menuBar);
    }

    private static void menuTypeCsv() {
        System.out.printf("%n   1 - Converter Csv to Json (using text)%n" +
                "   2 - Converter Csv to Json (using File)%n" +
                "   0 - Exit to Menu%n");
    }

    private static void menuTypeJson() {
        System.out.printf("%n   1 - Converter Json to Csv (using text)%n" +
                "   2 - Converter Json to Csv (using File)%n" +
                "   0 - Exit to Menu%n");
    }
}
