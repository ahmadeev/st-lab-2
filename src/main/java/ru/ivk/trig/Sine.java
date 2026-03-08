package ru.ivk.trig;

import ru.ivk.MathConstants;
import ru.ivk.function.SeriesFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.BiFunction;

public class Sine extends SeriesFunction {
    @Override
    public BigDecimal computeSeries(final BigDecimal x, final BigDecimal precision) {
        final MathContext mc = new MathContext(precision.scale() + 10, RoundingMode.HALF_EVEN);

        final BigDecimal doublePi = MathConstants.PI.multiply(BigDecimal.valueOf(2));
        final BigDecimal z = x.remainder(doublePi);

        BiFunction<BigDecimal, Integer, BigDecimal> nextTerm = (BigDecimal term, Integer n) -> BigDecimal.valueOf(-1)
                .multiply(term).multiply(z.pow(2))
                .divide(BigDecimal.valueOf(((long) 2 * n) * ((long) 2 * n + 1)), mc);

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal term = z;

        int count = 1;

        do {
            sum = sum.add(term);
            term = nextTerm.apply(term, count++);
        } while (term.abs().compareTo(precision) > 0);

        return sum.setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
