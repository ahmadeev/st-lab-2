package ru.ivk.trig;

import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Cosecant extends AbstractFunction {
    private final Sine sin;

    public Cosecant() {
        this.sin = new Sine();
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        validate(x, precision);

        return BigDecimal.ONE
                .divide(
                        sin.calculate(x, precision),
                        MathContext.DECIMAL128.getPrecision(),
                        RoundingMode.HALF_EVEN
                )
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
