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

        return cos.calculate(x, precision)
                .divide(
                        sin.calculate(x, precision),
                        MathContext.DECIMAL128.getPrecision(),
                        RoundingMode.HALF_EVEN
                )
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
