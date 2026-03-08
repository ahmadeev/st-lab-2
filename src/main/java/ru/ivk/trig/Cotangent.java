package ru.ivk.trig;

import lombok.RequiredArgsConstructor;
import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class Cotangent extends AbstractFunction {
    private final Sine sin;
    private final Cosine cos;

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        return cos.calculate(x, precision)
                .divide(
                        sin.calculate(x, precision),
                        MathContext.DECIMAL128.getPrecision(),
                        RoundingMode.HALF_EVEN
                )
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
