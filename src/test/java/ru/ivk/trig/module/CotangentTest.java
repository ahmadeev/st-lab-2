package ru.ivk.trig.module;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ivk.trig.Cosine;
import ru.ivk.trig.Cotangent;
import ru.ivk.trig.Sine;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("[unit] Cotangent")
public class CotangentTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);

    @Mock
    private Sine mockSin;
    @Mock
    private Cosine mockCos;

    @ParameterizedTest(name = "cos(x)={1} sin(x)={0} -> cot(x)={2}")
    @CsvSource({
            "1.0, 0.5, 0.5",
            "2.0, 1.0, 0.5",
            "1.5, 3.0, 2.0"
    })
    @DisplayName("Cotangent (hardcoded values)")
    void testValues(BigDecimal sinValue, BigDecimal cosValue, BigDecimal expected, TestReporter reporter) {
        when(mockSin.calculate(any(), any())).thenReturn(sinValue);
        when(mockCos.calculate(any(), any())).thenReturn(cosValue);

        Cotangent cot = new Cotangent(mockSin, mockCos);

        BigDecimal actual = cot.calculate(BigDecimal.ZERO, PRECISION);

        assertEquals(expected, actual.setScale(expected.scale(), RoundingMode.HALF_EVEN));
    }
}
