package Matrix;

import Matrix.*;

public class SPL {

    // Kaidah Cramer
    public static void cramersRule(Matrix m) {
        if (m.row != m.col - 1) {
            System.out.println("SPL tidak bisa diselesaikan karena jumlah persamaan != jumlah variabel.");
        } else {
            Matrix m1 = new Matrix();
            Matrix m2 = new Matrix();
            m.splitMatriks(m1, m2, 2);
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
                        System.out.println("Tidak ada solusi.");
                        flag = false; // Ada determinan yang bernilai tidak 0
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("SPL memiliki banyak solusi.");
                }
            } else {
                for (int i = 0; i < m.row; i++) {
                    double ans = detX[i] / det;
                    System.out.println("x-" + (i + 1) + " : " + (ans));
                }
            }
        }
    }
}
