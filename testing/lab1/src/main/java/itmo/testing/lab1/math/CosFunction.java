package itmo.testing.lab1.math;

public final class CosFunction {
    private static final double TWO_PI = 2.0 * Math.PI;

    private CosFunction() {
    }

    public static double calculate(double x, double epsilon) {
        if (epsilon <= 0.0) {
            throw new IllegalArgumentException("epsilon must be positive");
        }

        double normalizedX = normalizeToMinusPiToPi(x);

        double term = 1.0;
        double sum = term;
        int n = 1;
        int maxIterations = 10_000;

        while (Math.abs(term) > epsilon && n < maxIterations) {
            term *= -normalizedX * normalizedX / ((2.0 * n - 1.0) * (2.0 * n));
            sum += term;
            n++;
        }

        return sum;
    }

    private static double normalizeToMinusPiToPi(double x) {
        double reduced = x % TWO_PI;
        if (reduced > Math.PI) {
            return reduced - TWO_PI;
        }
        if (reduced < -Math.PI) {
            return reduced + TWO_PI;
        }
        return reduced;
    }
}
