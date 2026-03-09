package ru.ivk.trig;

import lombok.RequiredArgsConstructor;
import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
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

        BigDecimal extraPrecision = precision.divide(
                BigDecimal.TEN.pow(GUARD_DIGITS),
                MathContext.DECIMAL128.getPrecision(),
                RoundingMode.HALF_EVEN
        );

        BigDecimal cosValue = cos.calculate(x, extraPrecision);

        if (cosValue.abs().compareTo(precision) < 0) {
            throw new ArithmeticException("tan value -> inf");
        }

        return sin.calculate(x, extraPrecision)
                .divide(
                        cosValue,
                        MathContext.DECIMAL128.getPrecision(),
                        RoundingMode.HALF_EVEN
                )
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
