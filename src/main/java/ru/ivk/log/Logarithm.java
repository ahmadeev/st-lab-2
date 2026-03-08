package ru.ivk.log;

import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;

public class Logarithm extends AbstractFunction {
    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        return BigDecimal.ZERO;
    }
}
