package ru.ivk.log;

import ru.ivk.function.SeriesFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.BiFunction;

public class NaturalLogarithm extends SeriesFunction {
    @Override
    protected void validate(BigDecimal x, BigDecimal precision) {
        super.validate(x, precision);

        if (x.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("x value must be greater than 0");
        }
    }

    @Override
    protected BigDecimal computeSeries(final BigDecimal x, final BigDecimal precision) {
        final MathContext mc = new MathContext(precision.scale() + 10, RoundingMode.HALF_EVEN);

        // ln(x) = 2 * atanh( ( x - 1 ) / ( x + 1 ) )
        // atanh(z) = z + z^3 / 3 + z^5 / 5 + z^7 / 7 + ...
        // z = ( x - 1 ) / ( x + 1 );
        // |z| < 1, при x > 0
        final BigDecimal z = x.subtract(BigDecimal.ONE)
                .divide(x.add(BigDecimal.ONE), precision.scale() + 2, RoundingMode.HALF_EVEN);
        final BigDecimal z2 = z.pow(2);

        BiFunction<BigDecimal, Integer, BigDecimal> nextTerm = (BigDecimal term, Integer n) -> term.multiply(z2)
                .multiply(BigDecimal.valueOf(n - 2), mc)
                .divide(BigDecimal.valueOf(n), mc);

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal term = z;

        int count = 3;

        do {
            sum = sum.add(term);
            term = nextTerm.apply(term, count);
            count += 2;
        } while (term.abs().compareTo(precision) > 0);

        return sum.multiply(BigDecimal.valueOf(2));
    }
}
