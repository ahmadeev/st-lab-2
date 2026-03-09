package ru.ivk.trig.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ivk.trig.Cotangent;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CotangentTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);
    private Cotangent cot;

    @BeforeEach
    void setUp() {
        this.cot = new Cotangent();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cot.csv", numLinesToSkip = 1, delimiter = ',')
    void testValues(BigDecimal x, BigDecimal y) {
        BigDecimal actual = cot.calculate(x, PRECISION);

        assertTrue(
                actual.subtract(y).abs().compareTo(PRECISION) < 0
        );
    }
}
