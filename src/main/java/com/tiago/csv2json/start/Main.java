package com.tiago.csv2json.start;

import com.tiago.csv2json.converters.csv.CsvConverter;
import com.tiago.csv2json.converters.utils.FileUtils;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {

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
                    System.out.print("\nEnter and option: ");
                    String optionCsv = scanner.nextLine();

                    if (optionCsv.equals("0")) {
                        break;
                    }

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

                            String resultCsvConverter = csvConverter.convertCsv2jsonFromString(csvStringBuilder.toString());
                            System.out.println(resultCsvConverter);

                            saveCsvFileConverted(resultCsvConverter);
                            break;
                        case "2":
                            System.out.print("\nEnter the path of the file: ");
                            String pathFile = scanner.nextLine();

                            String resultFileConverter = csvConverter.convertCsv2JsonFromFile(Path.of(pathFile));
                            System.out.println(resultFileConverter);

                            saveCsvFileConverted(resultFileConverter);
                            break;
                        default:
                            System.out.println("Invalid option...");
                            break;
                    }

                    break;
                case "2":
                    break;
                default:
                    System.out.println("Invalid option...");
                    break;
            }
        }
    }

    private static void saveCsvFileConverted(String resultCsvConverter) {
        System.out.print("\nWould you like to save the file (y/n): ");
        String saveCsvOption = scanner.nextLine();

        if (saveCsvOption.equalsIgnoreCase("y")) {
            System.out.println("\nEnter the file name:");
            String fileName = scanner.nextLine();
            FileUtils.saveFile(fileName, resultCsvConverter);
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
}
