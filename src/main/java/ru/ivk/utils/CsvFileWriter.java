package ru.ivk.utils;

import ru.ivk.function.AbstractFunction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvFileWriter {
    private final String OUTPUT_DIR;

    public CsvFileWriter() throws IOException {
        OUTPUT_DIR = System.getProperty("user.dir") + File.separator + "output";

        Path dir = Paths.get(OUTPUT_DIR);

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
    }

    public void run(AbstractFunction fun, BigDecimal min, BigDecimal max, BigDecimal step, BigDecimal precision) {
        final String path = getFilePath(fun);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            writer.write("x,y");

            while (min.compareTo(max) <= 0) {
                writer.newLine();

                try {
                    writer.write(String.format("%s,%s", min, fun.calculate(min, precision)));
                } catch (Exception e) {
                    writer.write(String.format("%s,%s", min, null));
                }

                min = min.add(step);
            }

            writer.flush();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private String getFilePath(AbstractFunction function) {
        return OUTPUT_DIR + File.separator + function.getClass().getSimpleName() + ".csv";
    }
}
