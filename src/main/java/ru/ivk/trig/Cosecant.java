package ru.ivk.trig;

import lombok.RequiredArgsConstructor;
import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class Cosecant extends AbstractFunction {
    private final Sine sin;

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        return BigDecimal.ONE
                .divide(
                        sin.calculate(x, precision),
                        MathContext.DECIMAL128.getPrecision(),
                        RoundingMode.HALF_EVEN
                )
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
