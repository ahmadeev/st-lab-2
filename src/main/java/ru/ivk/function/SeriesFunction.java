package ru.ivk.function;

import java.math.BigDecimal;

public abstract class SeriesFunction extends AbstractFunction {
    protected abstract BigDecimal computeSeries(BigDecimal x, BigDecimal precision);

    @Override
    public final BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        validate(x, precision);

        return computeSeries(x, precision);
    }
}
