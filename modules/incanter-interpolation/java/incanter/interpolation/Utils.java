package incanter.interpolation;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public final class Utils {

    private Utils() {}


    // Matrix representation:
    // B C 0 0 ... 0 D
    // A B C 0 ... 0 D
    // 0 A B C ... 0 D
    // 0 0 A B ... 0 D
    // ...............
    // 0 0 0 0 ... C D
    // 0 0 0 0 ... B D

    private static double[] solveTridiagonal(double[] a, double[] b, double[] c, double[] d) {
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

    private static double[] calcB(List<Double> y, List<Double> h) {
        int n = y.size() - 2;
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = 6 * ((y.get(i + 2) - y.get(i + 1)) / h.get(i + 1)
                          -
                          (y.get(i + 1) - y.get(i)) / h.get(i))
                     / (h.get(i) + h.get(i + 1));
        }
        return res;
    }

    private static double[] calcCE(List<Double> h, int add) {
        int n = h.size() - 1;
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = h.get(i + add) / (h.get(i) + h.get(i + 1));
        }
        return res;
    }

    public static List<Double> calcGammas(List<Double> y, List<Double> h) {
        double[] c = calcCE(h, 0);
        double[] e = calcCE(h, 1);
        double[] b = calcB(y, h);
        int n = b.length;
        double[] twos = new double[n];
        Arrays.fill(twos, 2);
        double[] res = solveTridiagonal(c, twos, e, b);
        List<Double> gammas = new ArrayList<Double>(n + 2);
        gammas.add(0.0);
        for (double gamma : res) {
            gammas.add(gamma);
        }
        gammas.add(0.0);
        return gammas;
    }
}
