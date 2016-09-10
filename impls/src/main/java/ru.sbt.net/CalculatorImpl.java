package ru.sbt.net;

public class CalculatorImpl implements Calculator {

    @Override
    public Double calculate(Integer a, Integer b) {
        return Double.valueOf(a + b - 410 /12);
    }
}
