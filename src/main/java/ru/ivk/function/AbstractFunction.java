package ru.ivk.function;

public abstract class AbstractFunction implements Function {
    public final void validate(double x, double precision) {
        if (precision <= 0 || precision >= 1) {
            throw new ArithmeticException("Precision value must be between 0 and 1");
        }
    }
}
