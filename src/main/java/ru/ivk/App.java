package ru.ivk;

import ru.ivk.log.NaturalLogarithm;
import ru.ivk.trig.Sine;

import java.math.BigDecimal;

public class App {
    public static void main( String[] args ) {
        Sine sine = new Sine();

        System.out.println(sine.calculate(BigDecimal.valueOf(Math.PI), BigDecimal.valueOf(1e-6)));

        NaturalLogarithm ln = new NaturalLogarithm();

        System.out.println(ln.calculate(BigDecimal.valueOf(1.5), BigDecimal.valueOf(1e-6)));
    }
}
