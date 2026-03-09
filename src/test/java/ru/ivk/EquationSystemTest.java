package ru.ivk;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquationSystemTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);

    @Test
    void testSystemWithNegativeX() {
        EquationSystem es = new EquationSystem();

        BigDecimal expected = BigDecimal.valueOf(-1.27985);
        BigDecimal actual = es.calculate(
                BigDecimal.valueOf(-2.24533),
                PRECISION
        );

        assertTrue(expected.subtract(actual).compareTo(PRECISION) < 0);
    }

    @Test
    void testSystemWithPositiveX() {
        EquationSystem es = new EquationSystem();

        BigDecimal expected = BigDecimal.valueOf(-7.93577);
        BigDecimal actual = es.calculate(
                BigDecimal.valueOf(3.91498),
                PRECISION
        );

        assertTrue(expected.subtract(actual).compareTo(PRECISION) < 0);
    }
}
