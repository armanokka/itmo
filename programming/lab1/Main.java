import java.util.Arrays;
import java.text.DecimalFormat;
     
public class Main {
    public static boolean in(double[] arr, Double value) {
        for (double d : arr) {
            if (d == value) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        // STEP 1.
        short[] C1 = new short[9];
        int idx = 0;
        for (short i = 6; i <= 22; i+=2) {
            C1[idx] = i;
            idx += 1;
        }
        System.out.println("1) " + Arrays.toString(C1));

        // STEP 2.
        double[] X = new double[17];
        double min = -13.0;
        double max = -9.0;
        for (int i = 0; i < 17; i++) {
            X[i] = min + Math.random() * (max - min);
        }
        System.out.println("2) " + Arrays.toString(X));

        // STEP 3.
        double[][] C2 = new double[9][17];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 17; j++) {
                double x = X[j];
                double c = C1[i];
                if (c == 12) {
                    double s1 = Math.PI - Math.pow(0.75 / x, 3);
                    double s2 = Math.pow(s1 / 3, 2) - 0.5;
                    double s3 = s2 / 4;
                    double u1 = 4*(Math.pow(x, 2*x) - 0.75);
                    C2[i][j] = Math.pow(s3, u1);
                } else if (Main.in(new double[]{14.0, 18.0, 29.0, 22.0}, c)) {
                    C2[i][j] = Math.pow(Math.cbrt(x*(x+1)) / 2, 2);
                } else {
                    C2[i][j] = Math.pow(
                            2.0*Math.log10(
                                    Math.pow(
                                            Math.tan(
                                                    Math.sin(x)/2
                                            ), 2)
                            ), 2);
                }
            }
        }
        System.out.println("3)");
        for (double[] a : C2) {
            for (double b: a) {
                System.out.printf("%7.3f ", b);
            }
            System.out.println();
        }
    }
}