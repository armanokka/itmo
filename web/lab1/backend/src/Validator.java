import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Validator {
    private final List<Integer> xRange = Arrays.asList(-4, -3, -2, -1, 0, 1, 2, 3, 4);
    private final List<Integer> yRange = Arrays.asList(3, -2, -1, 0, 1, 2, 3);
    private final List<Integer> rRange = Arrays.asList(1, 2, 3, 4, 5);
    private String log = "all ok";

    // x = 4
    // y = 1
    // r = 1
    public boolean validate(int x, float y, int r) {
        return (checkX(x) && checkY(y) && checkR(r)) && (inRect(x, y, r) || inTriangle(x, y, r) || inCircle(x, y, r));
    }

    // x = 1
    // y = 0
    // r = 1
    private boolean inRect(int x, float y, int r) {
        return x >= 0 && x <= 1 && y <= 0 && y >= -1;
    }

    private boolean inTriangle(int x, float y, int r) {
        return (y >= 0) && (x <= 0) && (y <= x + 1);
    }

    private boolean inCircle(int x, float y, int r) {
        return (x >= 0) && (y >= 0) && (x * x + y * y <= 0.25);
    }

    public Validator(){
    }

    public String getErr(){
        return log;
    }

    public boolean checkX(int x){
        if (xRange.contains(x)){
            return true;
        }
        log = "X must be selected";
        return false;
    }

    public boolean checkY(Float y){
        if (-3 <= y && y <= 5){
            return true;
        }
        log = "Y value must be -3<=y<=5";
        return false;
    }

    public boolean checkR(int r){
        if (rRange.contains(r)){
            return true;
        }
        log = "R must be selected";
        return false;
    }
}
