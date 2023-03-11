package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.Function;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class SinFunction extends Function {

    public SinFunction(double accuracy) {
        super(accuracy);
    }

    public SinFunction() {
        super();
    }

    @Override
    public double calculate(double x) {
        double result = 0, prev;
        int i = 0;
        x = handleInterval(x);

        do {
            prev = result;
            result = result + pow(-1, i) * pow(x, 2 * i + 1) / factorial(2 * i + 1);
            i++;
        } while (this.accuracy <= abs(result - prev));

        return result;
    }

    private static double factorial(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
