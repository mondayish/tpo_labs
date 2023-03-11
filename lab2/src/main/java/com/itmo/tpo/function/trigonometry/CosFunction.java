package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.Function;

import static java.lang.Math.*;

public class CosFunction extends Function {

    public CosFunction(double accuracy) {
        super(accuracy);
    }

    public CosFunction() {
        super();
    }

    @Override
    public double calculate(double x) {
        x = handleInterval(x);
        return (((x > Math.PI / 2) & (x < 3 * Math.PI / 2)) || ((x < -Math.PI / 2) & (x > -3 * Math.PI / 2)) ? -1 : 1)
                * checkNaN(sqrt(1 - pow(new SinFunction(this.accuracy).calculate(x), 2)));
    }

    private static double checkNaN(double x) {
        return Double.isNaN(x) ? (double) 0 : x;
    }
}
