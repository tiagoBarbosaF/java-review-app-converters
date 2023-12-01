package com.tiago.csv2json.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;

public record Products(
        @CsvBindByPosition(position = 0) @CsvBindByName(column = "id") Long id,
        @CsvBindByPosition(position = 1) @CsvBindByName(column = "name") String name,
        @CsvBindByPosition(position = 2) @CsvBindByName(column = "shortDescription") String shortDescription,
        @CsvBindByPosition(position = 3) @CsvBindByName(column = "description") String description,
        @CsvBindByPosition(position = 4) @CsvBindByName(column = "value") BigDecimal value
) {
}
