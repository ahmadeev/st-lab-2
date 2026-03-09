package ru.ivk.utils;

import ru.ivk.function.AbstractFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvFileWriter {
    private final String OUTPUT_DIR;

    public CsvFileWriter(String outputDir) throws IOException {
        OUTPUT_DIR = outputDir;

        Path dir = Paths.get(OUTPUT_DIR);

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
    }

    public void process(AbstractFunction fun, BigDecimal min, BigDecimal max, BigDecimal step, BigDecimal precision) {
        final String filename = fun.toString();
        final String path = FileCommonUtilities.getFilePath(OUTPUT_DIR, filename, FileExtensions.CSV);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            writer.write("x,y");
            writer.newLine();

            while (min.compareTo(max) <= 0) {
                try {
                    writer.write(String.format("%s,%s", min, fun.calculate(min, precision)));
                    writer.newLine();
                } catch (Exception ignore) {}

                min = min.add(step);
            }

            writer.flush();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
