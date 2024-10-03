import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Validator {
    private final List<Integer> xRange = Arrays.asList(-4, -3, -2, -1, 0, 1, 2, 3, 4);
    private final List<Integer> yRange = Arrays.asList(3, -2, -1, 0, 1, 2, 3);
    private final List<Integer> rRange = Arrays.asList(1, 2, 3, 4, 5);

    public boolean validate(int x, float y, int r) {
        x /= r;
        y /= r;
        return (xRange.contains(x) && yRange.contains(y) && rRange.contains(r)) && (inRect(x, y) || inTriangle(x, y) || inCircle(x, y));
    }
    private boolean inRect(int x, float y) {
        return x >= 0 && x <= 1 && y <= 0 && y >= -1;
    }

    private boolean inTriangle(int x, float y) {
        return (y >= 0) && (x <= 0) && (y <= x + 1);
    }

    private boolean inCircle(int x, float y) {
        return (x >= 0) && (y >= 0) && (x * x + y * y <= 0.25);
    }
}
