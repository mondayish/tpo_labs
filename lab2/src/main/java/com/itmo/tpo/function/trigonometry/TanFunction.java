package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.Function;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class TanFunction extends Function {

    public TanFunction(double accuracy) {
        super(accuracy);
    }

    public TanFunction() {
        super();
    }

    @Override
    public double calculate(double x) {
        x = handleInterval(x);
        if (abs(x % PI - PI / 2) <= this.accuracy) return Double.POSITIVE_INFINITY;
        if (abs(x % PI + PI / 2) <= this.accuracy) return Double.POSITIVE_INFINITY;

        double tan = new SinFunction(this.accuracy).calculate(x) / new CosFunction(this.accuracy).calculate(x);
        return Double.isFinite(tan) ? tan : Double.POSITIVE_INFINITY;
    }
}
