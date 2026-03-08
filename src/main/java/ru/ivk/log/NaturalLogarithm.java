package ru.ivk.log;

import ru.ivk.function.SeriesFunction;

import java.math.BigDecimal;

public class NaturalLogarithm extends SeriesFunction {
    @Override
    protected BigDecimal computeSeries(BigDecimal x, BigDecimal precision) {
        return BigDecimal.ZERO;
    }
}
