package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.Function;

public class CosecFunction extends Function {

    public CosecFunction(double accuracy) {
        super(accuracy);
    }

    public CosecFunction() {
        super();
    }

    @Override
    public double calculate(double x) {
        x = handleInterval(x);
        if (Math.abs(x % Math.PI) < this.accuracy) return Double.POSITIVE_INFINITY;

        double cosec = 1 / new SinFunction(this.accuracy).calculate(x);
        return Double.isFinite(cosec) ? cosec : Double.POSITIVE_INFINITY;
    }
}
