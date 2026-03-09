package ru.ivk.trig;

import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Tangent extends AbstractFunction {
    private final Sine sin;
    private final Cosine cos;

    public Tangent() {
        this.sin = new Sine();
        this.cos = new Cosine();
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        validate(x, precision);

        precision = precision.divide(
                BigDecimal.TEN.pow(GUARD_DIGITS),
                MathContext.DECIMAL128.getPrecision(),
                RoundingMode.HALF_EVEN
        );

        return sin.calculate(x, precision)
                .divide(
                        cos.calculate(x, precision),
                        MathContext.DECIMAL128.getPrecision(),
                        RoundingMode.HALF_EVEN
                )
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
