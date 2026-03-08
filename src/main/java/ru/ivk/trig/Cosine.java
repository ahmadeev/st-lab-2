package ru.ivk.trig;

import lombok.RequiredArgsConstructor;
import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class Cosine extends AbstractFunction {
    private final Sine sin;

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        // sin^2(x) + cos^2(x) = 1
        // |cos(x)| = sqrt(1 - sin^2(x))

        return BigDecimal.ONE
                .subtract(sin.calculate(x, precision).pow(2))
                .sqrt(MathContext.DECIMAL128)
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
