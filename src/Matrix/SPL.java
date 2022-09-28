package Matrix;

import Matrix.*;
import Utils.*;

public class SPL {

    // 1. Metode Eliminasi Gauss
    public static String gaussMethod(Matrix m) {
        String res = new String();
        //Matrix mx = mx.copyMatrix(m);

        m.eliminasiGauss();

        int p=0;    // label variabel parametrik
        int[] param = new int[m.col];  // variabel parametrik
        for (int i=0; i < m.col; i++){
            param[i] = -1;   // variabel parametrik x_(i+1); label berdasarkan p, inisialisasi dengan -1
        }
        
        double[][] subs = new double[m.row][m.col];

        boolean noSolution = false;
        int r = m.row-1, c;
        while(r >= 0 && !noSolution){
            c = 0;
            while(m.mat[r][c] == 0 && c < mat.col-1){
                c++;
            }
            // m.mat[r][c] == 0 ATAU c == m.col-1 (dicapai kolom terakhir / vektor kolom konstanta)
            if(c == mat.col-1 && m.mat[r][c != 0]){
                noSolution = true;
            }
            for(int i=c+1; i < m.col; i++){
                
            }
        }

        /*double ans;
        boolean hasparam;
        int r=0, c=0;
        while(r < m.row && c < m.col-1){
            if(m.mat[r][c] != 0){   // maju hingga terdapat elemen bukan 0 (leading 1) atau kolom terakhir
                res += ("x_" + (c + 1) + " =");
                hasparam = false;
                for(int i=c+1; i < m.col-1; i++){
                    if(m.mat[r][i] != 0){
                        if(param[i] == -1){
                            p++;
                            param[i] = p;
                        }
                        ans = Utils.setPrec((-m.mat[r][i]), 6);
                        if(ans < 0){
                            res += (" - " + (-ans));
                        } else {
                            res += (" + " + (ans));
                        }
                        res += ("*" + "t_" + (param[i]));

                        hasparam = true;
                    }
                }
                // kolom terakhir, kolom vektor konstanta
                ans = Utils.setPrec((0.000000 + m.mat[r][m.col-1]), 6);
                if(hasparam){
                    if(ans < 0){
                            res += (" - " + (-ans));
                    } else if(ans > 0) {
                            res += (" + " + (ans));
                    }
                } else {
                    res += (" " + (ans));
                }
                res += ("\n");
                r++;    // lanjutkan ke baris berikutnya
            } else if(param[c] != -1){
                res += ("x_" + (c + 1) + " = t_" + (param[c]) + "\n");
            }
            c++;
        }*/
        /* r == m.row (telah dilewati baris terakhir)
        ATAU r == m.col-1 (dicapai kolom terakhir / vektor kolom konstanta) */
        if (r < m.row && m.mat[r][c] != 0){
            res = "SPL tidak memiliki solusi.\n";
        }
        return res;
    }

    // 2. Metode Eliminasi Gauss-Jordan
    public static String gaussjordanMethod(Matrix m) {
        String res = new String();
        //Matrix mx = mx.copyMatrix(m);

        m.eliminasiGaussJordan();

        int p=0;    // label variabel parametrik
        int[] param = new int[m.col];  // variabel parametrik
        for (int i=0; i < m.col; i++){
            param[i] = -1;   // variabel parametrik x_(i+1); label berdasarkan p, inisialisasi dengan -1
        }
        
        double ans;
        boolean hasparam;
        int r=0, c=0;
        while(r < m.row && c < m.col-1){
            if(m.mat[r][c] != 0){   // maju hingga terdapat elemen bukan 0 (leading 1) atau kolom terakhir
                res += ("x_" + (c + 1) + " =");
                hasparam = false;
                for(int i=c+1; i < m.col-1; i++){
                    if(m.mat[r][i] != 0){
                        if(param[i] == -1){
                            p++;
                            param[i] = p;
                        }
                        ans = Utils.setPrec((-m.mat[r][i]), 6);
                        if(ans < 0){
                            res += (" - " + (-ans));
                        } else {
                            res += (" + " + (ans));
                        }
                        res += ("*" + "t_" + (param[i]));

                        hasparam = true;
                    }
                }
                // kolom terakhir, kolom vektor konstanta
                ans = Utils.setPrec((0.000000 + m.mat[r][m.col-1]), 6);
                if(hasparam){
                    if(ans < 0){
                            res += (" - " + (-ans));
                    } else if(ans > 0) {
                            res += (" + " + (ans));
                    }
                } else {
                    res += (" " + (ans));
                }
                res += ("\n");
                r++;    // lanjutkan ke baris berikutnya
            } else if(param[c] != -1){
                res += ("x_" + (c + 1) + " = t_" + (param[c]) + "\n");
            }
            c++;
        }
        /* r == m.row (telah dilewati baris terakhir)
        ATAU c == m.col-1 (dicapai kolom terakhir / vektor kolom konstanta) */
        if (r < m.row && m.mat[r][c] != 0){
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
        if (m1.isSingular() || !m1.isSquare()) { // matriks tidak memiliki invers, tidak ada solusi unik
            res = "SPL memiliki banyak solusi atau tidak memiliki solusi. Silakan gunakan metode lain.\n";
        } else {
            m1 = Inverse.inversiGaussJordan(m1);
            Matrix ansMat = Matrix.multiplyMat(m1, m2);
            for (int i = 0; i < ansMat.row; i++) {
                double ans = Utils.setPrec((0.000000 + ansMat.mat[i][0]), 6);
                res += ("x_" + (i + 1) + " = " + (ans) + "\n");
            }
        }
        return res;
    }

    // 4. Kaidah Cramer
    public static String cramersRule(Matrix m) {
        String res = new String();
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m.splitMatrix(m1, m2, m.col - 1);
        if (m1.isSingular() || !m1.isSquare()) { // matriks tidak memiliki invers, tidak ada solusi unik
            res = "SPL memiliki banyak solusi atau tidak memiliki solusi. Silakan gunakan metode lain.\n";
        } else {
            double det = Determinant.determinanEliminasiGauss(m);
            double[] valX = new double[m.row];
            Matrix temp = new Matrix();
            for (int i = 0; i < m1.col; i++) {
                temp.copyMatrix(m1);
                for (int j = 0; j < m1.row; j++) {  // temp <- masukkan kolom ke i matriks persamaan dengan matriks hasil
                    temp.mat[j][i] = m2.mat[j][0];
                }
                valX[i] = Determinant.determinanEliminasiGauss(temp)/det;   // hitung nilai xi
            }
            for (int i = 0; i < m.row; i++) {
                double ans = Utils.setPrec((0.000000 + valX[i] / det), 6);
                res += ("x_" + (i + 1) + " = " + (ans) + "\n");
            }
        }
        return res;
    }
}
