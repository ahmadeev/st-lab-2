package ru.ivk.trig.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ivk.trig.Tangent;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TangentTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);
    private Tangent tan;

    @BeforeEach
    void setUp() {
        this.tan = new Tangent();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tan.csv", numLinesToSkip = 1, delimiter = ',')
    void testValues(BigDecimal x, BigDecimal y) {
        BigDecimal actual = tan.calculate(x, PRECISION);

        assertTrue(
                actual.subtract(y).abs().compareTo(PRECISION) < 0
        );
    }
}
