package ru.ivk.utils.file;

import lombok.Getter;

public enum FileExtensions {
    CSV("csv");

    @Getter
    private final String format;

    FileExtensions(String format) {
        this.format = format;
    }
}
