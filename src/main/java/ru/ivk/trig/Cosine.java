package ru.ivk.trig;

import lombok.RequiredArgsConstructor;
import ru.ivk.MathConstants;
import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class Cosine extends AbstractFunction {
    private final Sine sin;

    public Cosine() {
        this.sin = new Sine();
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        validate(x, precision);

        // sin(pi / 2 - x) = cos(x)

        return sin.calculate(MathConstants.PI.divide(
                BigDecimal.valueOf(2),
                MathContext.DECIMAL128.getPrecision(),
                RoundingMode.HALF_EVEN
        ).subtract(x), precision).setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
