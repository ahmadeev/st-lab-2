package ru.ivk.trig.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ivk.trig.Sine;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("[unit] Sine")
public class SineTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);

    private Sine sin;

    @BeforeEach
    void setUp() {
        this.sin = new Sine();
    }

    @ParameterizedTest(name = "sin({0}) = {1}")
    @CsvFileSource(resources = "/sin.csv", numLinesToSkip = 1, delimiter = ',')
    @DisplayName("Sine (CSV values)")
    void testValues(BigDecimal x, BigDecimal y) {
        BigDecimal actual = sin.calculate(x, PRECISION);

        assertTrue(
                actual.subtract(y).abs().compareTo(PRECISION) < 0
        );
    }
}
