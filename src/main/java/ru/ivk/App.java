package ru.ivk;

import lombok.SneakyThrows;
import ru.ivk.function.AbstractFunction;
import ru.ivk.log.Logarithm;
import ru.ivk.log.NaturalLogarithm;
import ru.ivk.trig.*;
import ru.ivk.utils.file.CsvFileWriter;
import ru.ivk.utils.file.CsvGraphDrawer;
import ru.ivk.utils.file.CsvSeparators;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_EVEN;

public class App {
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "output";

    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);
    private static final BigDecimal POSITIVE_END = BigDecimal.TEN.setScale(10, HALF_EVEN);
    private static final BigDecimal NEGATIVE_END = POSITIVE_END.negate();
    private static final BigDecimal STEP = BigDecimal.valueOf(1e-2);

    private static final List<AbstractFunction> FUNCTIONS = new ArrayList<>(
            List.of(
                    new Sine(),
                    new Cosine(),
                    new Tangent(),
                    new Cotangent(),
                    new Cosecant(),
                    new NaturalLogarithm(),
                    new Logarithm(2),
                    new Logarithm(3),
                    new Logarithm(5),
                    new Logarithm(10)
            )
    );

    @SneakyThrows
    public static void main( String[] args ) {
        /* writes */

        CsvFileWriter writer = new CsvFileWriter(ROOT_PATH);

        FUNCTIONS.forEach((fun) -> {
            writer.process(
                    fun,
                    NEGATIVE_END,
                    POSITIVE_END,
                    STEP,
                    PRECISION
            );
        });

        /* plots */

        CsvGraphDrawer gd = new CsvGraphDrawer(ROOT_PATH, CsvSeparators.COMMA);

        FUNCTIONS.forEach(gd::process);
    }
}
