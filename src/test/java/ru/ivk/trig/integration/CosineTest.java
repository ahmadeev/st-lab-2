package ru.ivk.trig.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ivk.trig.Cosine;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CosineTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);

    private Cosine cos;

    @BeforeEach
    void setUp() {
        this.cos = new Cosine();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cos.csv", numLinesToSkip = 1, delimiter = ',')
    void testValues(BigDecimal x, BigDecimal y) {
        BigDecimal actual = cos.calculate(x, PRECISION);

        assertTrue(
                actual.subtract(y).abs().compareTo(PRECISION) < 0
        );
    }
}
