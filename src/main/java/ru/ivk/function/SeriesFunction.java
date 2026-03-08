package ru.ivk.function;

import java.math.BigDecimal;

public abstract class SeriesFunction extends AbstractFunction {
    protected final static int GUARD_DIGITS = 10;

    protected abstract BigDecimal computeSeries(BigDecimal x, BigDecimal precision);

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        validate(x, precision);

        return computeSeries(x, precision);
    }
}
