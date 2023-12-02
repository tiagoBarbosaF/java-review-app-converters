package com.tiago.csv2json.converters.json;

import com.tiago.csv2json.converters.utils.FileUtils;

import java.nio.file.Path;
import java.util.Scanner;

public class JsonService {
    private static final JsonConverter jsonConverter = new JsonConverter();

    public static void jsonOptions(String optionJson, Scanner scanner) {
        switch (optionJson) {
            case "1":
                System.out.println("Enter the content (after input content press Enter two times):");
                StringBuilder jsonStringBuilder = new StringBuilder();

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        break;
                    }

                    jsonStringBuilder.append(line).append("\n");
                }

                System.out.print("\nEnter the type of delimiter for your csv file: ");
                String delimiterCsv = scanner.nextLine();

                String resultJsonConverter = jsonConverter.convertJsonToCsvFromString(jsonStringBuilder.toString(), delimiterCsv);
                System.out.println(resultJsonConverter);

                saveJsonFileConverted(resultJsonConverter, scanner);
                break;
            case "2":
                System.out.print("\nEnter the path of the file: ");
                String jsonPathFile = scanner.nextLine();

                System.out.print("\nEnter the type of delimiter for your csv file: ");
                String csvDelimiter = scanner.nextLine();
                String resultJsonConverterFromFile = jsonConverter.convertJsonToCsvFromFile(Path.of(jsonPathFile), csvDelimiter);
                System.out.println(resultJsonConverterFromFile);

                saveJsonFileConverted(resultJsonConverterFromFile, scanner);
                break;
            default:
                System.out.println("Invalid option...");
                break;
        }
    }

    private static void saveJsonFileConverted(String resultJsonConverter, Scanner scanner) {
        System.out.print("\nWould you like to save the file (y/n): ");
        String saveJsonOptions = scanner.nextLine();

        if (saveJsonOptions.equalsIgnoreCase("y")) {
            System.out.print("\nEnter the file name: ");
            String fileName = scanner.nextLine();

            FileUtils.saveFile(fileName, resultJsonConverter);
        }
    }
}
