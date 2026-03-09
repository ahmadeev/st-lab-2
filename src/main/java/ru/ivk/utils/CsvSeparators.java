package ru.ivk.utils;

import lombok.Getter;

public enum CsvSeparators {
    COMMA(",");

    @Getter
    private final String separator;

    CsvSeparators(String separator) {
        this.separator = separator;
    }
}
