package Matrix;

import java.util.*;
import java.io.*;
import Utils.*;

public class Matrix {
    public int row;
    public int col;
    public double mat[][];

    static Scanner in = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // ############## KONSTRUKTOR ##############
    // Menginisialisasi sebuah matriks kosong dengan ukuran baris row dan kolom col
    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.mat = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.mat[i][j] = 0;
            }
        }
    }

    // ############## METHODS ##############
    // Mencetak matriks ke layar
    public void displayMatrix() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.mat[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    // Membaca input matriks dari user
    public void readMatrix() {
        int row = 0;
        int col = 0;
        System.out.print("Masukkan jumlah baris matriks: ");
        try {
            row = in.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        System.out.print("Masukkan jumlah kolom matriks: ");
        try {
            col = in.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        this.row = row;
        this.col = col;
        this.mat = new double[row][col];
        for (int i = 0; i < row; i++) {
            String line = new String();
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] elmt = line.split(" ", 0);
            for (int j = 0; j < col; j++) {
                this.mat[i][j] = Utils.toDouble(elmt[j]);
            }
        }

    }

    // Mengembalikan matriks identitas berukuran n x n
    public static Matrix createIdMat(int n) {
        Matrix ret = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    ret.mat[i][j] = 1;
                } else {
                    ret.mat[i][j] = 0;
                }
            }
        }
        return ret;
    }

    // Mengembalikan transpose dari matriks
    public Matrix transpose() {
        Matrix tempMat = new Matrix(this.col, this.row);
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                tempMat.mat[i][j] = this.mat[j][i];
            }
        }
        return tempMat;
    }

    // ############## OBE ##############
    // Menukar row1 dengan row2 dalam sebuah matriks
    public void swapRow(int row1, int row2) {
        for (int j = 0; j < this.col; j++) {
            double temp = this.mat[row1][j];
            this.mat[row1][j] = this.mat[row2][j];
            this.mat[row2][j] = temp;
        }
    }

    // Menambahkan row1 dengan k*row2
    public void addRow(int row1, int row2, double k) {
        for (int j = 0; j < this.col; j++) {
            this.mat[row1][j] += k * this.mat[row2][j];
        }

    }

    // Mengalikan semua elemen matriks dengan sebuah konstanta k
    public void scalarMulti(double k) {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.mat[i][j] *= k;
            }
        }
    }

    public void multRow(int row, double k) {
        for (int i = 0; i < this.col; i++) {
            this.mat[row][i] *= k;
        }
    }

    // Mengecek apakah matriks adalah matriks persegi
    public boolean isSquare() {
        boolean isSquare = true;
        if (this.row != this.col) {
            isSquare = false;
        }
        return isSquare;
    }

    // ########### OPERASI MATRIKS ##############
    // PERKALIAN MATRIKS
    // Prekondisi jumlah kolom m1 harus = jumlah baris m2
    public static Matrix multiplyMat(Matrix m1, Matrix m2) {
        Matrix ret = new Matrix(m1.row, m2.col);
        for (int i = 0; i < ret.row; i++) {
            for (int j = 0; j < ret.col; j++) {
                for (int k = 0; k < m1.col; k++) {
                    ret.mat[i][j] += m1.mat[i][k] * m2.mat[k][j];
                }
            }
        }
        return ret;
    }

    // PENJUMLAHAN MATRIKS
    // Prekondisi ukuran m1 = m2
    public static Matrix addMat(Matrix m1, Matrix m2) {
        Matrix ret = new Matrix(m1.row, m1.col);
        for (int i = 0; i < ret.row; i++) {
            for (int j = 0; j < ret.col; j++) {
                ret.mat[i][j] = m1.mat[i][j] + m2.mat[i][j];
            }
        }
        return ret;
    }

    // PENGURANGAN MATRIKS
    // Prekondisi ukuran m1 = m2
    public static Matrix subsMat(Matrix m1, Matrix m2) {
        Matrix ret = new Matrix(m1.row, m1.col);
        for (int i = 0; i < ret.row; i++) {
            for (int j = 0; j < ret.col; j++) {
                ret.mat[i][j] = m1.mat[i][j] - m2.mat[i][j];
            }
        }
        return ret;
    }

    // AUGMENTED MATRIKS
    // Prekondisi jumlah baris m1 = jumlah baris m2
    public static Matrix augMatrix(Matrix m1, Matrix m2) {
        Matrix ret = new Matrix(m1.row, m1.col + m2.col);
        for (int i = 0; i < ret.row; i++) {
            int j = 0;
            for (; j < m1.col; j++) {
                ret.mat[i][j] = m1.mat[i][j];
            }
            for (; j < ret.col; j++) {
                ret.mat[i][j] = m2.mat[i][j - m1.col];
            }
        }
        return ret;
    }

    // Eliminasi Gauss
    // I.S. matriks terdefinisi, F.S. matriks menjadi bentuk matriks eselon baris
    public void eliminasiGauss() {
        int r=0, c=0;                                                                   // inisialisasi
        while (r < this.row && c < this.col) {
            int rpivot = r;
            while (rpivot < this.row-1 && this.mat[rpivot][c] == 0) {                   // periksa kolom dari baris rpivot untuk kemunculan elemen bukan 0 (mencari indeks pivot)
                rpivot++;
            }
            // rpivot == this.row-1 (baris terakhir) ATAU this.mat[rpivot][col] != 0
            if (this.mat[rpivot][c] != 0) {                                             // terdapat elemen bukan 0 dari kolom (pivot)
                if(r != rpivot) {
                    this.swapRow(r, rpivot);                                            // tukar baris dengan baris pivot
                }
                this.multRow(r, 1/this.mat[r][c]);                                      // pembagian baris dengan nilai elemen bukan 0 pertama (pivot)
                for (int i = r+1; i < this.row; i++) {                                  // penjumlahan baris untuk setiap baris setelah rpivot
                    this.addRow(i, r, -this.mat[i][c]);
                }
                r++;
            }
            c++;
        }
        // r == this.row ATAU c == this.col
    }

    // Eliminasi Gauss-Jordan
    // I.S. matriks terdefinisi, F.S. matriks menjadi bentuk matriks eselon baris tereduksi
    public void eliminasiGaussJordan() {
        this.eliminasiGauss();                                                          // lakukan eliminasi Gauss (fase turun)
        // eliminasi fase naik
        int r=0, c=0;                                                                   // inisialisasi
        while (r < this.row && c < this.col) {
            if (this.mat[r][c] != 0) {                                                  // terdapat elemen bukan 0 dari kolom (pivot)
                for (int i = r-1; i >= 0; i--) {                                        // penjumlahan baris untuk setiap baris sebelum r
                    this.addRow(i, r, -this.mat[i][c]);
                }
                r++;
            }
            c++;
        }
        // r == this.row ATAU c == this.col
    }
}
