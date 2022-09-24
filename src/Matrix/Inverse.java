package Matrix;

import Matrix.*;

public class Inverse {
    // Inversi Gauss Jordan
    // mengembalikan matriks m^-1, invers dari matriks m. matriks m harus berupa matriks persegi dengan dimensi > 0
    public static Matrix inversiGaussJordan(Matrix m) {
        int dimension = m.row;

        Matrix matinv = Matrix.createIdMat(dimension);
        Matrix maug = Matrix.augMatrix(m, matinv);
        maug.eliminasiGauss();                                                          // lakukan eliminasi Gauss (fase turun)
        // eliminasi fase naik

        int diag = dimension-1;                                                         // inisialisasi, jalankan dari diagonal akhir
        while (diag > 0 && maug.mat[diag][diag] != 0) {                                          
            for (int i = diag-1; i >= 0; i--) {                                         // penjumlahan baris untuk setiap baris sebelum r
                maug.addRow(i, diag, -maug.mat[i][diag]);
            }
            diag--;
        }
        // diag == 0 ATAU maug.mat[i][i] == 0 (elemen diagonal matriks utama terdapat 0, tidak memiliki invers)

        if(maug.mat[diag][diag] != 0) {                                                 // tercapai baris pertama, dan diagonal tidak terdapat 0 (memiliki invers)
            for (int i=0; i < dimension; i++) {                                         // matinv adalah inverse matriks (hasil OBE matriks augment)
                for (int j=0; j < dimension; j++) {
                    matinv.mat[i][j] = maug.mat[i][dimension+j];
                }
            }
        }
        else {                                                                          // jika tidak terdapat inverse, kembalikan matriks 0
            matinv = new Matrix(dimension, dimension);
        }
        return matinv;
    }
    
    public static Matrix inversiKofaktor(Matrix m) {
        Matrix inversMat = new Matrix(m.row, m.col);
        Matrix adjMat = new Matrix(m.row, m.col);
        double det = Determinant.determinanKofaktor(m);

        if (det == 0) {
            return null;
        } else {
            adjMat = Matrix.adjoint(m);
            adjMat.scalarMulti(1/det);
            inversMat = adjMat;

            return inversMat;
        }
        


    }
}
