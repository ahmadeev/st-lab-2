package ru.ivk.log;

import ru.ivk.utils.MathConstants;
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
        final MathContext mc = new MathContext(precision.scale() + GUARD_DIGITS, RoundingMode.HALF_EVEN);

        /* нормализация x */

        BigDecimal m = x;
        int k = 0;

        final BigDecimal two = BigDecimal.valueOf(2);

        while (m.compareTo(two) > 0) {
            m = m.divide(two, mc);
            k++;
        }

        while (m.compareTo(BigDecimal.ONE.divide(two, mc)) < 0) {
            m = m.multiply(two, mc);
            k--;
        }

        /* замена для сходимости на x > 0 */

        // ln(x) = 2 * atanh( ( x - 1 ) / ( x + 1 ) )
        // atanh(z) = z + z^3 / 3 + z^5 / 5 + z^7 / 7 + ...
        // z = ( x - 1 ) / ( x + 1 );
        // |z| < 1, при x > 0
        final BigDecimal z = m.subtract(BigDecimal.ONE, mc)
                .divide(m.add(BigDecimal.ONE, mc), mc);
        final BigDecimal z2 = z.pow(2, mc);

        BiFunction<BigDecimal, Integer, BigDecimal> nextTerm = (BigDecimal term, Integer n) -> term.multiply(z2, mc)
                .multiply(BigDecimal.valueOf(n - 2), mc)
                .divide(BigDecimal.valueOf(n), mc);

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal term = z;

        int count = 3;

        do {
            sum = sum.add(term, mc);
            term = nextTerm.apply(term, count);
            count += 2;
        } while (term.abs(mc).compareTo(precision) > 0);

        // знакопостоянный ряд (прибавляем последний член)
        return sum.add(term, mc).multiply(BigDecimal.valueOf(2), mc).add(BigDecimal.valueOf(k).multiply(MathConstants.LN2), mc).setScale(precision.scale(), RoundingMode.HALF_EVEN);
    }
}
