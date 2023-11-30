package com.tiago.csv2json.converters.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileUtils {

    public static void saveFile(String path, String text) {
        try {
            Files.write(Path.of(path), text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            System.out.println("File created.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
