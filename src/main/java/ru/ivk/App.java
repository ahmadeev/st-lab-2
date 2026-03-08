package ru.ivk;

import ru.ivk.trig.Sine;

import java.math.BigDecimal;

public class App {
    public static void main( String[] args ) {
        Sine sine = new Sine();

        System.out.println(sine.calculate(BigDecimal.valueOf(Math.PI).divide(BigDecimal.valueOf(2)), BigDecimal.valueOf(1e-6)));
    }
}
