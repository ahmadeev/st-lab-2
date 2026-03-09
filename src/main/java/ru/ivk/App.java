package ru.ivk;

import lombok.SneakyThrows;
import ru.ivk.function.AbstractFunction;
import ru.ivk.log.Logarithm;
import ru.ivk.log.NaturalLogarithm;
import ru.ivk.trig.*;
import ru.ivk.utils.CsvFileWriter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_EVEN;

public class App {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);
    private static final BigDecimal POSITIVE_END = BigDecimal.TEN.setScale(10, HALF_EVEN);
    private static final BigDecimal NEGATIVE_END = POSITIVE_END.negate();
    private static final BigDecimal STEP = BigDecimal.valueOf(1e-2);

    @SneakyThrows
    public static void main( String[] args ) {
/*        Sine sine = new Sine();

        System.out.println(sine.calculate(BigDecimal.valueOf(Math.PI), BigDecimal.valueOf(1e-6)));

        NaturalLogarithm ln = new NaturalLogarithm();

        System.out.println(ln.calculate(BigDecimal.valueOf(1.5), BigDecimal.valueOf(1e-6)));

        Cotangent cot = new Cotangent();

        System.out.println(cot.calculate(BigDecimal.ZERO, BigDecimal.valueOf(1e-6)));*/

        /* writes */

        CsvFileWriter writer = new CsvFileWriter();

        List<AbstractFunction> functions = new ArrayList<>(
                List.of(
                        new Sine(),
                        new Cosine(),
                        new Tangent(),
                        new Cotangent(),
                        new Cosecant(),
                        new NaturalLogarithm(),
                        new Logarithm(3)
                )
        );

        functions.forEach((fun) -> {
            writer.run(
                    fun,
                    NEGATIVE_END,
                    POSITIVE_END,
                    STEP,
                    PRECISION
            );
        });
    }
}
