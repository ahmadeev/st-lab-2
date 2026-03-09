package ru.ivk.trig.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ivk.trig.Cosecant;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("[integration] Cosecant")
public class CosecantTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);

    private Cosecant csc;

    @BeforeEach
    void setUp() {
        this.csc = new Cosecant();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csc.csv", numLinesToSkip = 1, delimiter = ',')
    void testValues(BigDecimal x, BigDecimal y) {
        BigDecimal actual = csc.calculate(x, PRECISION);

        assertTrue(
                actual.subtract(y).abs().compareTo(PRECISION) < 0
        );
    }
}
