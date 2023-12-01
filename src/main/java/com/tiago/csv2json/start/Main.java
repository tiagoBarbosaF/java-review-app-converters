package com.tiago.csv2json.start;

import com.google.gson.Gson;
import com.tiago.csv2json.converters.csv.CsvService;
import com.tiago.csv2json.converters.json.JsonConverter;
import com.tiago.csv2json.converters.utils.FileUtils;

import java.util.Scanner;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Gson gson = new Gson();
    private static String textWithoutArray = """
            {
                 "id": 1,
                 "name": "Laptop",
                 "shortDescription": "Powerful laptop",
                 "description": "High-performance laptop with SSD",
                 "value": 1200.00
               }
            """;

    private static String textWithArray = """
            [{"id":1,"name":"Laptop","shortDescription":"Powerful laptop","description":"High-performance laptop with SSD","value":1200.00},{"id":2,"name":"Smartphone","shortDescription":"Latest smartphone","description":"Feature-rich smartphone with dual cameras","value":699.99},{"id":3,"name":"Headphones","shortDescription":"Wireless headphones","description":"Over-ear headphones with noise cancellation","value":199.95}]
            """;

    public static void start() {

        JsonConverter jsonConverter = new JsonConverter();
        String test = jsonConverter.convertJsonToCsvFromString(textWithArray, ";");
        System.out.println(test);
        FileUtils.saveFile("test-csv-file.csv",test);

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

                    if (optionJson.equalsIgnoreCase("0")){
                        break;
                    }


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
