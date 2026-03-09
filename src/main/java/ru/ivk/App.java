package ru.ivk;

import lombok.SneakyThrows;
import ru.ivk.log.NaturalLogarithm;
import ru.ivk.trig.Cotangent;
import ru.ivk.trig.Sine;
import ru.ivk.utils.CsvFileWriter;

import java.math.BigDecimal;

public class App {
    @SneakyThrows
    public static void main( String[] args ) {
        Sine sine = new Sine();

        System.out.println(sine.calculate(BigDecimal.valueOf(Math.PI), BigDecimal.valueOf(1e-6)));

        NaturalLogarithm ln = new NaturalLogarithm();

        System.out.println(ln.calculate(BigDecimal.valueOf(1.5), BigDecimal.valueOf(1e-6)));

        Cotangent cot = new Cotangent();

        System.out.println(cot.calculate(BigDecimal.ZERO, BigDecimal.valueOf(1e-6)));

        /* writes */

        CsvFileWriter writer = new CsvFileWriter();

        writer.run(
                new Cotangent(),
                BigDecimal.valueOf(-50),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(1e-6)
        );
    }
}
