package com.tiago.csv2json.converters.json;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvBindByName;
import com.tiago.csv2json.models.Products;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JsonConverter {

    private static final Gson GSON = new Gson();

    public String convertJsonToCsvFromString(String jsonContent, String delimiter) {
        StringWriter stringWriter = new StringWriter();

        if (jsonContent.startsWith("[")) {
            Products[] products = GSON.fromJson(jsonContent, Products[].class);
            List<Products> productsList = Arrays.asList(products);

            String[] header = getHeaders();

            getCsvWriter(delimiter, stringWriter, header, productsList);
        }

        return stringWriter.toString();
    }

    public String convertJsonToCsvFromFile(Path filePath, String delimiter) {
        StringWriter stringWriter = new StringWriter();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            Products[] products = GSON.fromJson(reader, Products[].class);
            List<Products> productsList = Arrays.asList(products);

            String[] headers = getHeaders();

            getCsvWriter(delimiter, stringWriter, headers, productsList);

            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getCsvWriter(String delimiter, StringWriter stringWriter, String[] header, List<Products> productsList) {
        try (CSVWriter csvWriter = new CSVWriter(stringWriter, delimiter.charAt(0),
                CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            csvWriter.writeNext(header);
            List<String[]> records = productsList.stream()
                    .map(product -> new String[]{
                            Objects.toString(product.id()),
                            product.name(),
                            product.shortDescription(),
                            product.description(),
                            Objects.toString(product.value())
                    })
                    .toList();
            csvWriter.writeAll(records);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] getHeaders() {
        Field[] fields = Products.class.getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(CsvBindByName.class))
                .map(field -> field.getAnnotation(CsvBindByName.class).column())
                .toArray(String[]::new);
    }
}
