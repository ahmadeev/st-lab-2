package ru.ivk.trig.module;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ivk.trig.Cosecant;
import ru.ivk.trig.Sine;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("[unit] Cosecant")
public class CosecantTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);

    @Mock
    private Sine mockSin;

    @Test
    @DisplayName("Cosecant (passed argument)")
    void testValues() {
        when(mockSin.calculate(any(), any())).thenReturn(BigDecimal.ONE);

        Cosecant csc = new Cosecant(mockSin);

        csc.calculate(BigDecimal.ONE, PRECISION);

        verify(mockSin, atLeastOnce()).calculate(any(), any());
    }
}
