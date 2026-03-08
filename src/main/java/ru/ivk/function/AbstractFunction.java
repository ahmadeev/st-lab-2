package ru.ivk.function;

import java.math.BigDecimal;

public abstract class AbstractFunction implements Function {
    protected void validate(BigDecimal x, BigDecimal precision) {
        if (precision.abs().compareTo(BigDecimal.ZERO) <= 0 || precision.abs().compareTo(BigDecimal.ONE) >= 0) {
            throw new ArithmeticException("Precision value must be between 0 and 1");
        }
    }
}
