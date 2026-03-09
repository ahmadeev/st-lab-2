package ru.ivk.trig;

import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Cotangent extends AbstractFunction {
    private final Sine sin;
    private final Cosine cos;

    public Cotangent() {
        this.sin = new Sine();
        this.cos = new Cosine();
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        validate(x, precision);

        BigDecimal extraPrecision = precision.divide(
                BigDecimal.TEN.pow(GUARD_DIGITS),
                MathContext.DECIMAL128.getPrecision(),
                RoundingMode.HALF_EVEN
        );

        BigDecimal sinValue = sin.calculate(x, extraPrecision);

        if (sinValue.abs().compareTo(precision) < 0) {
            throw new ArithmeticException("cot value -> inf");
        }

        return cos.calculate(x, extraPrecision)
                .divide(
                        sinValue,
                        MathContext.DECIMAL128.getPrecision(),
                        RoundingMode.HALF_EVEN
                )
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
