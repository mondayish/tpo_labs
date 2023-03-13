package com.itmo.tpo.function;

import lombok.SneakyThrows;

import java.io.FileWriter;
import java.math.BigDecimal;

import static java.lang.Math.*;

public abstract class AbstractFunction {

    private static final double MIN_ACCURACY = pow(10, -10);
    private static final double MAX_ACCURACY = pow(10, -1);

    protected double accuracy;

    public AbstractFunction(double accuracy) {
        if (accuracy < MIN_ACCURACY || accuracy > MAX_ACCURACY) {
            throw new IllegalArgumentException("Accuracy must be in [" + MIN_ACCURACY + ", " + MAX_ACCURACY + "]");
        }
        this.accuracy = accuracy;
    }

    public AbstractFunction() {
        this(MIN_ACCURACY);
    }

    public abstract BigDecimal calculate(double x);

    // todo вынести эту кучу параметров в какой-нибудь объект контекста
    @SneakyThrows
    public void saveToCsv(String filePath, double x, double limit, double step, String delimiter) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(toCsv(x, limit, step, delimiter));
            writer.flush();
        }
    }

    public String toCsv(double x, double limit, double step, String delimiter) {
        StringBuilder result = new StringBuilder();
        while (x <= limit) {
            result.append(x).append(delimiter).append(calculate(x)).append("\n");
            x += step;
        }
        return result.toString();
    }
}
