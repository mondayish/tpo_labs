package com.itmo.tpo.task1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CosFunctionTest {

    @ParameterizedTest(name = "cos({0})")
    @DisplayName("Check PI values")
    @ValueSource(doubles = {-2 * Math.PI, -1.5*Math.PI, -Math.PI, -0.5*Math.PI, 0,
                0.5*Math.PI, Math.PI, 1.5*Math.PI, 2*Math.PI})
    public void checkPiValues(double val) {
        assertAll(
                () -> assertEquals(Math.cos(val), CosFunction.calculate(val, 0.0001), 0.0001)
        );
    }

    @ParameterizedTest(name = "cos({0}) = cos({0} + 2*PI)")
    @DisplayName("Check cos(n) = cos(n + 2*PI)")
    @ValueSource(doubles = {0.7893, -1.2345, 3.1416, -2.7183, 0.0457, -0.9091, 4.5678, -2.2222, 0.9876, -3.3333})
    public void checkCyclicValues(double val) {
        assertAll(
                () -> assertEquals(CosFunction.calculate(val, 0.0001),
                        CosFunction.calculate(val + 2 * Math.PI, 0.0001), 0.0001)
        );
    }

    @ParameterizedTest(name = "cos({0}) = cos(-{0})")
    @DisplayName("Check cos(n) = cos(n + 2*PI)")
    @ValueSource(doubles = {-1.2345, 3.1416, -2.7183, 0.0457, -0.7893, 0.9091, -4.5678, 2.2222, -0.9876, 3.3333})
    public void checkOppositeValues(double val) {
        assertAll(
                () -> assertEquals(CosFunction.calculate(val, 0.0001),
                        CosFunction.calculate(-val, 0.0001), 0.0001)
        );
    }

}