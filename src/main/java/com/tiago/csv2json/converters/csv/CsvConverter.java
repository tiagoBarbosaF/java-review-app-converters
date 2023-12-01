package com.tiago.csv2json.converters.csv;

import com.google.gson.Gson;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.tiago.csv2json.models.Products;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvConverter {

    private static final Gson GSON = new Gson();

    public String convertCsvToJsonFromString(String csvContent) {
        try (CSVReader csvReader = new CSVReaderBuilder(new StringReader(csvContent))
                .withSkipLines(1)
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {
            return converterCsvToJson(csvReader);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertCsvToJsonFromFile(Path filePath) {
        try (CSVReader csvReader = new CSVReaderBuilder(Files.newBufferedReader(filePath))
                .withSkipLines(1)
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {
            return converterCsvToJson(csvReader);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    private static String converterCsvToJson(CSVReader csvReader) throws IOException, CsvException {
        List<String[]> records = csvReader.readAll();
        List<Products> productsList = records.stream()
                .map(record -> new Products(Long.parseLong(record[0]), record[1], record[2], record[3], new BigDecimal(record[4])))
                .toList();
        ArrayList<Products> productsListJson = new ArrayList<>(productsList);
        return GSON.toJson(productsListJson);
    }
}
