package ru.ivk.log;

import lombok.RequiredArgsConstructor;
import ru.ivk.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class Logarithm extends AbstractFunction {
    private final NaturalLogarithm ln;
    private final int base;

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        // log_a(b) = ln(b) / ln(a)

        return ln.calculate(x, precision)
                .divide(
                        ln.calculate(BigDecimal.valueOf(base), precision),
                        MathContext.DECIMAL128.getPrecision(),
                        RoundingMode.HALF_EVEN
                )
                .setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
