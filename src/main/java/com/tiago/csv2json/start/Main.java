package com.tiago.csv2json.start;

import com.tiago.csv2json.converters.csv.CsvConverter;

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
                    menuCsv();
                    System.out.print("\nEnter and option: ");
                    String optionCsv = scanner.nextLine();

                    if (optionCsv.equals("0")) {
                        break;
                    }

                    switch (optionCsv) {
                        case "1":
                            System.out.println("Enter the file name:");
                            String fileName = scanner.nextLine();
                            System.out.println("Enter the file content: (after input content press Enter, Ctrl+Z, Enter again)");
                            StringBuilder csvStringBuilder = new StringBuilder();
                            while (scanner.hasNextLine()) {
                                String line = scanner.nextLine();
                                if (line.isEmpty()) {
                                    break;
                                }
                                csvStringBuilder.append(line).append("\n");
                            }

                            CsvConverter.convertCsv2jsonFromString(fileName, csvStringBuilder.toString());
                            break;
                        case "2":
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

    private static void menu() {
        String menuBar = "*".repeat(40);

        System.out.println("\n" + menuBar);
        System.out.printf("== Choice the type of converter ==%n" +
                "   1 - Converter Csv to Json%n" +
                "   2 - Converter Json to Csv%n" +
                "   0 - Exit%n");
        System.out.println(menuBar);
    }

    private static void menuCsv() {
        System.out.printf("%n   1 - Converter Csv to Json (using text)%n" +
                "   2 - Converter Csv to Json (using File)%n" +
                "   0 - Exit to Menu%n");
    }
}
