package Matrix;

import Matrix.*;
import Utils.*;

public class SPL {

    // 1. Metode Eliminasi Gauss
    public static String gaussMethod(Matrix m) {
        String res = new String();
        // Matrix mx = mx.copyMatrix(m);

        m.eliminasiGauss();
        int p = 0; // label variabel parametrik
        int[] param = new int[m.col - 1]; // variabel parametrik
        for (int i = 0; i < m.col - 1; i++) {
            param[i] = -1; // variabel parametrik x_(i+1); label berdasarkan p, inisialisasi dengan -1
        }

        double[][] subs = new double[m.row][m.col]; // hasil substitusi subs[i][j] menunjukkan x_i mengandung konstanta
                                                    // tiap ekspresi
        for (int i = 0; i < m.row; i++) {
            for (int j = 0; j < m.col; j++) {
                subs[i][j] = 0.000000;
            }
        }

        boolean noSolution = false;
        int r = m.row - 1, c;
        while (r >= 0 && !noSolution) {
            c = 0;
            while (m.mat[r][c] == 0 && c < m.col - 1) {
                c++;
            }
            // m.mat[r][c] == 0 ATAU c == m.col-1 (dicapai kolom terakhir / vektor kolom
            // konstanta)
            if (c == m.col - 1) { // periksa jika SPL tidak memiliki solusi
                noSolution = (m.mat[r][c] != 0);
            } else {
                boolean hasparam = false, isfirst = true;
                String temp = new String();
                String tempparam = new String();
                double ans;
                temp = ("x_" + (c + 1) + " =");
                for (int i = c + 1; i < m.col - 1; i++) {
                    if ((m.mat[r][i]) != 0) {
                        if (param[i] == -1) {
                            p++;
                            param[i] = p;
                            tempparam = ("x_" + (i + 1) + " = t_" + (param[i]) + "\n") + tempparam;
                        }
                        subs[r][i] -= m.mat[r][i]; // pindahkan ke ruas kanan persamaan
                        if (subs[r][i] != 0) {
                            ans = Utils.setPrec((subs[r][i]), 6);
                            if (isfirst) {
                                if (ans == -1.000000) {
                                    temp += " -";
                                } else if (ans == 1.000000) {
                                    temp += " ";
                                } else {
                                    temp += (" " + (ans) + "*");
                                }
                                isfirst = false;
                            } else {
                                if (ans < 0) {
                                    temp += (" - ");
                                    if (ans != -1.000000) {
                                        temp += (-ans) + "*";
                                    }
                                } else {
                                    temp += (" + ");
                                    if (ans != 1.000000) {
                                        temp += (ans) + "*";
                                    }
                                }
                            }
                            temp += ("t_" + (param[i]));
                            hasparam = true;
                        }
                    }
                }
                // kolom terakhir (kolom vektor konstanta)
                subs[r][m.col - 1] += m.mat[r][m.col - 1];
                ans = Utils.setPrec((0.000000 + subs[r][m.col - 1]), 6);
                if (hasparam) {
                    if (ans < 0) {
                        temp += (" - " + (-ans));
                    } else if (ans > 0) {
                        temp += (" + " + (ans));
                    }
                } else {
                    temp += (" " + String.format("%.2f", ans));
                }
                temp += ("\n");
                res = tempparam + res;
                res = temp + res;
                // SUBSTITUSI BALIK / BACKWARD SUBSTITUTION (INI BUKAN METODE GAUSS-JORDAN)
                for (int i = r - 1; i >= 0; i--) { // SUBSTITUSI BALIK / BACKWARD SUBSSTITUTION (INI BUKAN METODE
                                                   // GAUSS-JORDAN)
                    // SUBSTITUSI BALIK / BACKWARD SUBSTITUTION (INI BUKAN METODE GAUSS-JORDAN)
                    for (int j = c + 1; j < m.col; j++) {
                        subs[i][j] += (-m.mat[i][c]) * subs[r][j]; // substitusi, kemudian pindahkan ke ruas kanan
                    }
                    m.mat[i][c] = 0; // x_c telah selesai disubstitusi
                }
            }
            r--;
        }
        if (noSolution) {
            res = "SPL tidak memiliki solusi.\n";
        }
        return res;
    }

    // 2. Metode Eliminasi Gauss-Jordan
    public static String gaussjordanMethod(Matrix m) {
        String res = new String();
        // Matrix mx = mx.copyMatrix(m);

        m.eliminasiGaussJordan();

        int p = 0; // label variabel parametrik
        int[] param = new int[m.col - 1]; // variabel parametrik
        for (int i = 0; i < m.col - 1; i++) {
            param[i] = -1; // variabel parametrik x_(i+1); label berdasarkan p, inisialisasi dengan -1
        }

        double ans;
        int r = 0, c = 0;
        while (r < m.row && c < m.col - 1) {
            if (m.mat[r][c] != 0) { // maju hingga terdapat elemen bukan 0 (leading 1) atau kolom terakhir
                boolean hasparam = false, isfirst = true;
                res += ("x_" + (c + 1) + " =");
                for (int i = c + 1; i < m.col - 1; i++) {
                    if (Utils.setPrec(m.mat[r][i], 4) != 0) {
                        if (param[i] == -1) {
                            p++;
                            param[i] = p;
                        }
                        ans = Utils.setPrec((-m.mat[r][i]), 6);
                        if (isfirst) {
                            if (ans == -1.000000) {
                                    res += " -";
                                } else if (ans == 1.000000) {
                                    res += " ";
                                } else {
                                    res += (" " + (ans) + "*");
                                }
                            isfirst = false;
                        } else {
                            if (ans < 0) {
                                    res += (" - ");
                                    if (ans != -1.000000) {
                                        res += (-ans) + "*";
                                    }
                                } else {
                                    res += (" + ");
                                    if (ans != 1.000000) {
                                        res += (ans) + "*";
                                    }
                                }
                        }
                        res += ("t_" + (param[i]));

                        hasparam = true;
                    }
                }
                // kolom terakhir, kolom vektor konstanta
                ans = Utils.setPrec((0.000000 + m.mat[r][m.col - 1]), 6);
                if (hasparam) {
                    if (ans < 0) {
                        res += (" - " + (-ans));
                    } else if (ans > 0) {
                        res += (" + " + (ans));
                    }
                } else {
                    res += (" " + String.format("%.2f", ans));
                }
                res += ("\n");
                r++; // lanjutkan ke baris berikutnya
            } else if (param[c] != -1) {
                res += ("x_" + (c + 1) + " = t_" + (param[c]) + "\n");
            }
            c++;
        }
        /*
         * r == m.row (telah dilewati baris terakhir)
         * ATAU c == m.col-1 (dicapai kolom terakhir / vektor kolom konstanta)
         */
        for(int i=c; i < m.col-1; i++){ // print sisa parameter
            if(param[i] != -1){
                res += ("x_" + (i + 1) + " = t_" + (param[i]) + "\n");
            }
        }
        if (r < m.row && m.mat[r][c] != 0) {
            res = "SPL tidak memiliki solusi.\n";
        }
        return res;
    }

    // 3. Metode Invers
    public static String inversMethod(Matrix m) {
        String res = new String();
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m.splitMatrix(m1, m2, m.col - 1);
        if (m1.isSquare()) {
            if (m1.isSingular()) {
                res = "SPL memiliki banyak solusi atau tidak memiliki solusi. Silakan gunakan metode lain.\n";
            } else {
                m1 = Inverse.inversiGaussJordan(m1);
                Matrix ansMat = Matrix.multiplyMat(m1, m2);
                for (int i = 0; i < ansMat.row; i++) {
                    double ans = Utils.setPrec((0.000000 + ansMat.mat[i][0]), 6);
                    res += ("x_" + (i + 1) + " = " + String.format("%.2f", ans) + "\n");
                }
            }
        } else {
            res = "SPL tidak bisa diselesaikan dengan metode invers. Silakan gunakan metode lain.\n";
        }
        // if (m1.isSingular() || !m1.isSquare()) { // matriks tidak memiliki invers,
        // tidak ada solusi unik
        // res = "SPL memiliki banyak solusi atau tidak memiliki solusi. Silakan gunakan
        // metode lain.\n";
        // } else {
        // m1 = Inverse.inversiGaussJordan(m1);
        // Matrix ansMat = Matrix.multiplyMat(m1, m2);
        // for (int i = 0; i < ansMat.row; i++) {
        // double ans = Utils.setPrec((0.000000 + ansMat.mat[i][0]), 6);
        // res += ("x_" + (i + 1) + " = " + String.format("%.2f", ans) + "\n");
        // }
        // }
        return res;
    }

    // 4. Kaidah Cramer
    public static String cramersRule(Matrix m) {
        String res = new String();
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m.splitMatrix(m1, m2, m.col - 1);
        if (m1.isSquare()) {
            if (m1.isSingular()) {
                res = "SPL memiliki banyak solusi atau tidak memiliki solusi. Silakan gunakan metode lain.\n";
            } else {
                double det = Determinant.determinanKofaktor(m1);
                double[] valX = new double[m.row];
                Matrix temp = new Matrix();
                for (int i = 0; i < m1.col; i++) {
                    temp.copyMatrix(m1);
                    for (int j = 0; j < m1.row; j++) { // temp <- masukkan kolom ke i matriks persamaan dengan matriks
                                                       // hasil
                        temp.mat[j][i] = m2.mat[j][0];
                    }
                    valX[i] = Determinant.determinanKofaktor(temp) / det; // hitung nilai xi
                }
                for (int i = 0; i < m.row; i++) {
                    double ans = Utils.setPrec((0.000000 + valX[i]), 6);
                    res += ("x_" + (i + 1) + " = " + String.format("%.2f", ans) + "\n");
                }
            }
        } else {
            res = "SPL tidak bisa diselesaikan dengan kaidah Cramer. Silakan gunakan metode lain.\n";
        }
        // if (m1.isSingular() || !m1.isSquare()) { // matriks tidak memiliki invers,
        // tidak ada solusi unik
        // res = "SPL memiliki banyak solusi atau tidak memiliki solusi. Silakan gunakan
        // metode lain.\n";
        // } else {
        // double det = Determinant.determinanKofaktor(m1);
        // double[] valX = new double[m.row];
        // Matrix temp = new Matrix();
        // for (int i = 0; i < m1.col; i++) {
        // temp.copyMatrix(m1);
        // for (int j = 0; j < m1.row; j++) { // temp <- masukkan kolom ke i matriks
        // persamaan dengan matriks hasil
        // temp.mat[j][i] = m2.mat[j][0];
        // }
        // valX[i] = Determinant.determinanKofaktor(temp) / det; // hitung nilai xi
        // }
        // for (int i = 0; i < m.row; i++) {
        // double ans = Utils.setPrec((0.000000 + valX[i]), 6);
        // res += ("x_" + (i + 1) + " = " + String.format("%.2f", ans) + "\n");
        // }
        // }
        return res;
    }
}
