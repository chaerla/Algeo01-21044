package Matrix;

import Matrix.*;
import Utils.*;

public class SPL {

    // 1. Metode Eliminasi Gauss
    public static String gaussMethod(Matrix m) {
        String res = new String();
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m.splitMatrix(m1, m2, m.col - 1);
        if (m.row != m.col - 1) {
            res = "SPL tidak bisa diselesaikan karena jumlah persamaan != jumlah variabel.\n";
            return res;
        }
        if (m.mat[m.row - 1][m.col - 2] == 0) {
            if (m.mat[m.row - 1][m.col - 1] == 0) {
                res = "SPL memiliki banyak solusi.\n";
            } else {
                res = "SPL tidak memiliki solusi. \n";
            }
            return res;
        }
        double ansArr[] = new double[m1.row];
        for (int i = m.row - 1; i >= 0; i--) {
            ansArr[i] = m.mat[i][m.row];
            for (int j = i + 1; j < m.row; j++) {
                ansArr[i] -= m.mat[i][j] * ansArr[j];
            }
            ansArr[i] = ansArr[i] / m.mat[i][i];
        }

        for (int i = 0; i < m.row; i++) {
            double ans = Utils.setPrec((ansArr[i]), 6);
            res += ("x-" + (i + 1) + " : " + (ans) + "\n");
        }
        return res;
    }

    // 3. Metode Invers

    public static String inversMethod(Matrix m) {
        String res = new String();
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m.splitMatrix(m1, m2, m.col - 1);
        if (m1.isSingular()) {
            res = "SPL memiliki banyak solusi atau tidak memiliki solusi. Silahkan gunakan metode lain. \n";
            return res;
        } else {
            if (!m1.isSquare()) {
                res = "SPL tidak bisa diselesaikan karena jumlah persamaan != jumlah variabel.\n";
                return res;
            }
            m1 = Inverse.inversiGaussJordan(m1);
            Matrix ansMat = Matrix.multiplyMat(m1, m2);
            for (int i = 0; i < ansMat.row; i++) {
                double ans = Utils.setPrec((ansMat.mat[i][0]), 6);
                res += ("x-" + (i + 1) + " : " + (ans) + "\n");
            }
        }
        return res;
    }

    // 4. Kaidah Cramer
    public static String cramersRule(Matrix m) {
        String res = new String();
        if (m.row != m.col - 1) {
            res = "SPL tidak bisa diselesaikan karena jumlah persamaan != jumlah variabel.\n";
            return res;
        } else {
            Matrix m1 = new Matrix();
            Matrix m2 = new Matrix();
            m.splitMatrix(m1, m2, m.col - 2);
            double det = Determinant.determinanEliminasiGauss(m);
            double[] detX = new double[m.row];
            Matrix temp = new Matrix();
            for (int i = 0; i < m1.col; i++) {
                temp.copyMatrix(m1);
                for (int j = 0; j < m1.row; j++) {
                    temp.mat[j][i] = m2.mat[j][0];
                }
                detX[i] = Determinant.determinanEliminasiGauss(temp);
            }
            if (det == 0) {
                boolean flag = true;
                for (int i = 0; i < m.row; i++) {
                    if (detX[i] != 0) {
                        System.out.println("SPL tidak memiliki solusi.\n");
                        flag = false; // Ada determinan yang bernilai tidak 0
                        break;
                    }
                }
                if (!flag) {
                    res = "SPL memiliki banyak solusi.\n";
                    return res;
                }
            } else {
                for (int i = 0; i < m.row; i++) {
                    double ans = Utils.setPrec((detX[i] / det), 6);
                    res += ("x-" + (i + 1) + " : " + (ans) + "\n");
                }
            }
        }
        return res;
    }
}
