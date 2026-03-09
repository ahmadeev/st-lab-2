package ru.ivk.trig.module;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ivk.trig.Sine;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
@DisplayName("[unit] Cosine")
public class CosineTest {
    private static final BigDecimal PRECISION = BigDecimal.valueOf(1e-6);

    @Mock
    private Sine mockSin;

    @Test
    @DisplayName("Cosine (passed argument)")
    void testValues() {

    }
}
