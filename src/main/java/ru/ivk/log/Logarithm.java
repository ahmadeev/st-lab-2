package ru.ivk.log;

import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Logarithm extends AbstractFunction {
    private final NaturalLogarithm ln;
    private final int base;

    public Logarithm(int base) {
        this.ln = new NaturalLogarithm();
        this.base = base;
    }

    @Override
    protected void validate(BigDecimal x, BigDecimal precision) {
        super.validate(x, precision);

        if (x.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("x value must be greater than 0");
        }
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        validate(x, precision);

        // log_a(b) = ln(b) / ln(a)

        return ln.calculate(x, precision)
                .divide(
                        ln.calculate(BigDecimal.valueOf(base), precision),
                        MathContext.DECIMAL128.getPrecision(),
                        RoundingMode.HALF_EVEN
                )
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return String.format("%s (base %d)", super.toString(), this.base);
    }
}
