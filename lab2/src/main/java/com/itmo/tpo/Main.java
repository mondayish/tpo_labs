package com.itmo.tpo;

import com.itmo.tpo.function.trigonometry.CosFunction;

import static java.lang.Math.PI;

public class Main {

    public static void main(String[] args) {
        System.out.println(new CosFunction(0.0001).calculate(-0.5 * PI));
    }
}
