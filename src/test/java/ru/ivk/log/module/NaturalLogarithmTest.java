package ru.ivk.log.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ivk.log.NaturalLogarithm;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NaturalLogarithmTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);
    private NaturalLogarithm ln;

    @BeforeEach
    void setUp() {
        this.ln = new NaturalLogarithm();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ln.csv", numLinesToSkip = 1, delimiter = ';')
    void testValues(BigDecimal x, BigDecimal y) {
        BigDecimal actual = ln.calculate(x, PRECISION);

        assertTrue(
                actual.subtract(y).abs().compareTo(PRECISION) < 0
        );
    }
}
