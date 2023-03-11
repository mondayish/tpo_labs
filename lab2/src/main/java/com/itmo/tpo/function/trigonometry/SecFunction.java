package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.Function;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class SecFunction extends Function {

    public SecFunction(double accuracy) {
        super(accuracy);
    }

    public SecFunction() {
        super();
    }

    @Override
    public double calculate(double x) {
        x = handleInterval(x);
        if (abs(x % PI - PI / 2) <= this.accuracy) return Double.POSITIVE_INFINITY;
        if (abs(x % PI + PI / 2) <= this.accuracy) return Double.POSITIVE_INFINITY;

        double sec = 1 / new CosFunction(this.accuracy).calculate(x);
        return Double.isFinite(sec) ? sec : Double.POSITIVE_INFINITY;
    }
}
