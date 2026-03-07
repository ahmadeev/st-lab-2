package ru.ivk.function;

public abstract class BasicFunction extends AbstractFunction {
    protected abstract double getTerm(int n, double x);
    protected abstract double computeSeries(double x, double precision);
}
