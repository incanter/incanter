package incanter.interpolation;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public final class Utils {

    private Utils() {}


    // Matrix representation:
    // B C 0 0 ... 0   D
    // A B C 0 ... 0   D
    // 0 A B C ... 0   D
    // 0 0 A B ... 0   D
    // .............   .
    // 0 0 0 0 ... C   D
    // 0 0 0 0 ... B   D

    private static double[] solveNaturalTridiagonal(double[] a, double[] b, double[] c, double[] d) {
        int n = b.length;
        for (int i = 0; i < n - 1; i++) {
            double coef = - a[i + 1] / b[i];
            b[i + 1] += c[i] * coef;
            d[i + 1] += d[i] * coef;
        }
        d[n - 1] /= b[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            d[i] = (d[i] - d[i + 1] * c[i]) / b[i];
        }
        return d;
    }


    // Matrix representation:
    // B C 0 0 ... 0 A   D
    // A B C 0 ... 0 0   D
    // 0 A B C ... 0 0   D
    // 0 0 A B ... 0 0   D
    // ...............   .
    // 0 0 0 0 ... B C   D
    // C 0 0 0 ... A B   D

    public static double[] solveClosedTridiagonal(double[] a, double[] b, double[] c, double[] d) {
        int n = b.length;
        // Store rightmost column (that initially starts with A) in 'a' array.

        // Eliminat A below main diagonal.
        for (int i = 0; i < n - 1; i++) {
            double coef = - a[i + 1] / b[i];
            b[i + 1] += c[i] * coef;
            d[i + 1] += d[i] * coef;
            a[i + 1] = a[i] * coef;
            if (i == n - 3) {
                a[i + 1] += c[i + 1];
                c[i + 1] = a[i + 1];
            }
        }

        // Eliminate single C in bottom left corner.
        double cur = c[n - 1];
        for (int i = 0; i < n - 1; i++) {
            double coef = -cur / b[i];
            d[n - 1] += coef * d[i];
            b[n - 1] += coef * a[i];
            cur = coef * c[i];
        }

        a[n - 2] = 0;
        d[n - 1] /= b[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            d[i] = (d[i] - d[i + 1] * c[i] - d[n - 1] * a[i]) / b[i];
        }
        return d;
    }

    private static double[] calcB(List<Double> y, List<Double> h, boolean natural) {
        int n = y.size();
        int size = y.size() - 1;
        if (natural) {
            size -= 1;
        }
        double[] res = new double[size];
        for (int i = 0; i < n - 2; i++) {
            res[i] = 6 * ((y.get(i + 2) - y.get(i + 1)) / h.get(i + 1)
                          -
                          (y.get(i + 1) - y.get(i)) / h.get(i))
                     / (h.get(i) + h.get(i + 1));
        }
        if (!natural) {
            res[size - 1] = 6 * ((y.get(1) - y.get(0)) / h.get(0)
                                 -
                                 (y.get(n - 1) - y.get(n - 2)) / h.get(n - 2))
                            / (h.get(0) + h.get(n - 2));
        }
        return res;
    }

    private static double[] calcCE(List<Double> h, int add, boolean natural) {
        int n = h.size();
        int size = n;
        if (!natural) {
            n -= 1;
        }
        double[] res = new double[size];
        for (int i = 0; i < size; i++) {
            res[i] = h.get((i + add) % n) / (h.get(i) + h.get((i + 1) % n));
        }
        return res;
    }

    // Calculate gamma coefficients with natural boundary conditions: S''(a) = S''(b) = 0
    public static List<Double> calcNaturalGammas(List<Double> y, List<Double> h) {
        double[] c = calcCE(h, 0, true);
        double[] e = calcCE(h, 1, true);
        double[] b = calcB(y, h, true);
        int n = b.length;
        double[] twos = new double[n];
        Arrays.fill(twos, 2);
        double[] res = solveNaturalTridiagonal(c, twos, e, b);
        List<Double> gammas = new ArrayList<Double>(n + 2);
        gammas.add(0.0);
        for (double gamma : res) {
            gammas.add(gamma);
        }
        gammas.add(0.0);
        return gammas;
    }

    // Calculate gamma coefficients with closed boundary conditions: S'(a) = S'(b), S''(a) = S''(b)
    public static List<Double> calcClosedGammas(List<Double> y, List<Double> h) {
        double[] c = calcCE(h, 0, false);
        double[] e = calcCE(h, 1, false);
        double[] b = calcB(y, h, false);
        int n = b.length;
        double[] twos = new double[n];
        Arrays.fill(twos, 2);
        double[] res = solveClosedTridiagonal(c, twos, e, b);
        List<Double> gammas = new ArrayList<Double>(n + 1);
        gammas.add(res[n - 1]);
        for (double gamma : res) {
            gammas.add(gamma);
        }
        return gammas;
    }

}
