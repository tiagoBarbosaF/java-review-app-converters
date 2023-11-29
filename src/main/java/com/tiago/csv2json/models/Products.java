package com.tiago.csv2json.models;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;

public record Products(
        @CsvBindByName(column = "id") Long id,
        @CsvBindByName(column = "name") String name,
        @CsvBindByName(column = "shortDescription") String shortDescription,
        @CsvBindByName(column = "description") String description,
        @CsvBindByName(column = "value") BigDecimal value
) {
}
