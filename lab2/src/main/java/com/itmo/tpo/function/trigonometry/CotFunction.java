package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.Function;

public class CotFunction extends Function {

    public CotFunction(double accuracy) {
        super(accuracy);
    }

    public CotFunction() {
        super();
    }

    @Override
    public double calculate(double x) {
        if (Math.abs(x % Math.PI) < this.accuracy) return Double.POSITIVE_INFINITY;

        double cot = new CosFunction(this.accuracy).calculate(x) / new SinFunction(this.accuracy).calculate(x);
        return Double.isFinite(cot) ? cot : Double.POSITIVE_INFINITY;
    }
}
