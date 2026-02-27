package itmo.testing.lab1.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CosFunctionTest {

    @Test
    void shouldReturnOneForZero() {
        double result = CosFunction.calculate(0.0, 1e-12);

        assertEquals(1.0, result, 1e-12);
    }

    @Test
    void shouldMatchMathCosForTypicalValues() {
        double[] values = {-2.7, -1.2, -0.1, 0.4, 1.3, 2.5};

        for (double x : values) {
            double expected = Math.cos(x);
            double actual = CosFunction.calculate(x, 1e-12);
            assertEquals(expected, actual, 1e-10, "Mismatch for x=" + x);
        }
    }

    @Test
    void shouldReduceArgumentForLargeValues() {
        double x = 1_000_000.0;

        double expected = Math.cos(x);
        double actual = CosFunction.calculate(x, 1e-12);

        assertEquals(expected, actual, 1e-10);
    }

    @Test
    void shouldThrowForNonPositiveEpsilon() {
        assertThrows(IllegalArgumentException.class, () -> CosFunction.calculate(1.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> CosFunction.calculate(1.0, -1e-6));
    }

    @Test
    void shouldWorkForValuesSlightlyGreaterThanPi() {
        double x = Math.PI + 0.2;

        double expected = Math.cos(x);
        double actual = CosFunction.calculate(x, 1e-12);

        assertEquals(expected, actual, 1e-10);
    }

    @Test
    void shouldWorkForValuesSlightlyLessThanMinusPi() {
        double x = -Math.PI - 0.2;

        double expected = Math.cos(x);
        double actual = CosFunction.calculate(x, 1e-12);

        assertEquals(expected, actual, 1e-10);
    }
}
