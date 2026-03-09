package ru.ivk;

import lombok.RequiredArgsConstructor;
import ru.ivk.function.AbstractFunction;
import ru.ivk.log.Logarithm;
import ru.ivk.log.NaturalLogarithm;
import ru.ivk.trig.Cosecant;
import ru.ivk.trig.Cosine;
import ru.ivk.trig.Cotangent;
import ru.ivk.trig.Tangent;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class EquationSystem extends AbstractFunction {
    private final Cosine cos;
    private final Tangent tan;
    private final Cotangent cot;
    private final Cosecant csc;
    private final NaturalLogarithm ln;
    private final Logarithm log2;
    private final Logarithm log3;
    private final Logarithm log5;
    private final Logarithm log10;

    public EquationSystem() {
        this.cos = new Cosine();
        this.tan = new Tangent();
        this.cot = new Cotangent();
        this.csc = new Cosecant();
        this.ln = new NaturalLogarithm();
        this.log2 = new Logarithm(2);
        this.log3 = new Logarithm(3);
        this.log5 = new Logarithm(5);
        this.log10 = new Logarithm(10);
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        // x <= 0 : ((((((((tan(x) * cos(x)) + cos(x)) / (tan(x) ^ 3)) ^ 3) / (cot(x) - tan(x))) - cot(x)) ^ 2) + csc(x))
        // x > 0 : (((((ln(x) ^ 3) + log_2(x)) / ((log_3(x) / log_3(x)) - log_3(x))) * log_10(x)) * (log_5(x) ^ 2))

        MathContext mc = new MathContext(MathContext.DECIMAL128.getPrecision(), RoundingMode.HALF_EVEN);

        BigDecimal extraPrecision = precision.divide(
                BigDecimal.TEN.pow(GUARD_DIGITS),
                MathContext.DECIMAL128.getPrecision(),
                RoundingMode.HALF_EVEN
        );

        if (x.compareTo(BigDecimal.ZERO) <= 0) {
            final BigDecimal cosx = cos.calculate(x, extraPrecision);
            final BigDecimal tanx = tan.calculate(x, extraPrecision);
            final BigDecimal cotx = cot.calculate(x, extraPrecision);
            final BigDecimal cscx = csc.calculate(x, extraPrecision);

            return (
                    tanx
                            .multiply(cosx, mc)
                            .add(cosx, mc)
                            .divide(
                                    tanx.pow(3, mc),
                                    mc
                            )
                            .pow(3, mc)
                            .divide(
                                    cotx.subtract(tanx, mc),
                                    mc
                            )
                            .subtract(cotx, mc)
                            .pow(2, mc)
                            .add(cscx, mc)
                            .setScale(precision.scale(), RoundingMode.HALF_EVEN)
            );
        }

        final BigDecimal lnx = ln.calculate(x, extraPrecision);
        final BigDecimal log2x = log2.calculate(x, extraPrecision);
        final BigDecimal log3x = log3.calculate(x, extraPrecision);
        final BigDecimal log5x = log5.calculate(x, extraPrecision);
        final BigDecimal log10x = log10.calculate(x, extraPrecision);

        return (
                lnx.pow(3, mc)
                        .add(log2x, mc)
                        .divide(
                                log3x.divide(log3x, mc).subtract(log3x, mc),
                                mc
                        )
                        .multiply(log10x, mc)
                        .multiply(log5x.pow(2, mc), mc)
                        .setScale(precision.scale(), RoundingMode.HALF_EVEN)
        );
    }
}
