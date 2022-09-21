package Matrix;

import Matrix.*;

public class Determinant {
    // Determinan Matriks Eliminasi Gauss
    // Mengembalikan nilai determinan matriks m dengan metode reduksi baris bentuk segitiga menggunakan eliminasi Gauss
    public static double determinanEliminasiGauss(Matrix m) {
        double det = 1;   // inisialisasi nilai determinan dengan 1 (det(I) = 1 sebagai standar)
        int r=0, c=0;                                                                   // inisialisasi
        while (r < m.row && c < m.col) {
            int rpivot = r;
            while (rpivot < m.row-1 && m.mat[rpivot][c] == 0) {                         // periksa kolom dari baris rpivot untuk kemunculan elemen bukan 0 (mencari indeks pivot)
                rpivot++;
            }
            // rpivot == this.row-1 (baris terakhir) ATAU this.mat[rpivot][col] != 0
            if (m.mat[rpivot][c] != 0) {                                                // terdapat elemen bukan 0 dari kolom (pivot)
                if (r != rpivot) {
                    m.swapRow(r, rpivot);                                               // tukar baris dengan baris pivot
                    det *= (-1);                                                        // kalikan nilai k dengan -1 setiap operasi pertukaran baris
                }
                for (int i = r+1; i < m.row; i++) {                                     // penjumlahan baris untuk setiap baris setelah rpivot
                    double s = -m.mat[i][c]/m.mat[r][c]; // konstanta kelipatan baris dalam operasi penjumlahan baris
                    m.addRow(i, r, s);
                }
                r++;
            }
            c++;
        }
        // r == this.row ATAU c == this.col
        for(int i=0; i < m.row; i++) {                                                  // kalikan determinan dengan nilai diagonal matriks tereduksi
            det *= m.mat[i][i];
        }
        return det;
    }
}
