package com.tiago.csv2json.converters.csv;

import com.tiago.csv2json.converters.utils.FileUtils;

import java.nio.file.Path;
import java.util.Scanner;

public class CsvService {
    public static void csvOptions(String optionCsv, Scanner scanner) {
        CsvConverter csvConverter = new CsvConverter();
        switch (optionCsv) {
            case "1":

                System.out.println("Enter the file content (after input content press Enter two times):");
                StringBuilder csvStringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        break;
                    }
                    csvStringBuilder.append(line).append("\n");
                }

                String resultCsvConverter = csvConverter.convertCsvToJsonFromString(csvStringBuilder.toString());
                System.out.println(resultCsvConverter);

                saveCsvFileConverted(resultCsvConverter, scanner);
                break;
            case "2":
                System.out.print("\nEnter the path of the file: ");
                String pathFile = scanner.nextLine();

                String resultFileConverter = csvConverter.convertCsvToJsonFromFile(Path.of(pathFile));
                System.out.println(resultFileConverter);

                saveCsvFileConverted(resultFileConverter, scanner);
                break;
            default:
                System.out.println("Invalid option...");
                break;
        }
    }

    private static void saveCsvFileConverted(String resultCsvConverter, Scanner scanner) {
        System.out.print("\nWould you like to save the file (y/n): ");
        String saveCsvOption = scanner.nextLine();

        if (saveCsvOption.equalsIgnoreCase("y")) {
            System.out.println("\nEnter the file name:");
            String fileName = scanner.nextLine();
            FileUtils.saveFile(fileName, resultCsvConverter);
        }
    }
}
