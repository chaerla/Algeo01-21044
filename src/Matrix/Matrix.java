package Matrix;

import java.util.*;
import java.io.*;
import Utils.*;

public class Matrix {
    public int row;
    public int col;
    public double mat[][];

    private static Scanner in = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // ############## KONSTRUKTOR ##############
    // Inisialisasi matriks kosong
    public Matrix() {
        this.row = 0;
        this.col = 0;
    }

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
                System.out.printf(String.format("%.4f", (Utils.setPrec(this.mat[i][j], 8))));
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    // Membaca input matriks dari user
    // Cara pakai: input row dan col dulu

    // int row = 0;
    // int col = 0;System.out.print("Masukkan jumlah baris matriks: ");try
    // {
    // row = in.nextInt();
    // }catch(
    // InputMismatchException e)
    // {
    // e.printStackTrace();
    // }System.out.print("Masukkan jumlah kolom matriks: ");try
    // {
    // col = in.nextInt();
    // }catch(
    // InputMismatchException e)
    // {
    // e.printStackTrace();
    // }
    // buat matriks baru, mis. m = new Matrix(row, col)
    // m.readMatrix
    public void inputMatrix() {
        int row = 0;
        int col = 0;
        System.out.println("Input harus berupa matriks ukuran m x n.");
        System.out.print("Masukkan jumlah baris (m) matriks: ");
        try {
            row = in.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        System.out.print("Masukkan jumlah kolom (n) matriks: ");
        try {
            col = in.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        this.row = row;
        this.col = col;
        this.mat = new double[row][col];
        System.out.println("Masukkan matriks:");
        this.readMatrix();
    }

    public void inputSquareMatrix() {
        int n = 0;
        System.out.print("Input harus berupa matriks segiempat dengan ukuran n x n. Masukkan n: ");
        try {
            n = in.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        this.row = n;
        this.col = n;
        this.mat = new double[this.row][this.col];
        System.out.println("Masukkan matriks:");
        this.readMatrix();
    }

    // Prekondisi, row, col, dan matriks terdefinisi.
    public void readMatrix() {
        for (int i = 0; i < this.row; i++) {
            String line = new String();
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] elmt = line.split(" ", 0);
            for (int j = 0; j < this.col; j++) {
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
        return (this.row == this.col);
    }

    public boolean isSingular() {
        if (Determinant.determinanEliminasiGauss(this) == 0) {
            return true;
        }
        return false;
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

    // SPLIT MATRIKS
    // m1 dan m2 adalah hasil dari split matrix. colNum adalah jumlah kolom m1.
    public void splitMatrix(Matrix m1, Matrix m2, int colNum) {
        m1.row = this.row;
        m2.row = this.row;
        m1.col = colNum;
        m2.col = this.col - m1.col;
        m1.mat = new double[m1.row][m1.col];
        m2.mat = new double[m2.row][m2.col];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < colNum; j++) {
                m1.mat[i][j] = this.mat[i][j];
            }
        }
        for (int i = 0; i < this.row; i++) {
            int k = 0;
            for (int j = colNum; j < this.col; j++) {
                m2.mat[i][k] = this.mat[i][j];
                k++;
            }
        }

    }

    public void copyMatrix(Matrix m) {
        this.row = m.row;
        this.col = m.col;
        this.mat = new double[m.row][m.col];
        for (int i = 0; i < m.row; i++) {
            for (int j = 0; j < m.col; j++) {
                this.mat[i][j] = m.mat[i][j];
            }
        }
    }

    // Eliminasi Gauss
    // I.S. matriks terdefinisi, F.S. matriks menjadi bentuk matriks eselon baris
    public void eliminasiGauss() {
        int r = 0, c = 0; // inisialisasi
        while (r < this.row && c < this.col) {
            int rpivot = r;
            while (rpivot < this.row - 1 && this.mat[rpivot][c] == 0) { // periksa kolom dari baris rpivot untuk
                                                                        // kemunculan elemen bukan 0 (mencari indeks
                                                                        // pivot)
                rpivot++;
            }
            // rpivot == this.row-1 (baris terakhir) ATAU this.mat[rpivot][col] != 0
            if (this.mat[rpivot][c] != 0) { // terdapat elemen bukan 0 dari kolom (pivot)
                if (r != rpivot) {
                    this.swapRow(r, rpivot); // tukar baris dengan baris pivot
                }
                this.multRow(r, 1 / this.mat[r][c]); // pembagian baris dengan nilai elemen bukan 0 pertama (pivot)
                for (int i = r + 1; i < this.row; i++) { // penjumlahan baris untuk setiap baris setelah rpivot
                    this.addRow(i, r, -this.mat[i][c]);
                }
                r++;
            }
            c++;
        }
        // r == this.row ATAU c == this.col
    }

    // Eliminasi Gauss-Jordan
    // I.S. matriks terdefinisi, F.S. matriks menjadi bentuk matriks eselon baris
    // tereduksi
    public void eliminasiGaussJordan() {
        this.eliminasiGauss(); // lakukan eliminasi Gauss (fase turun)
        // eliminasi fase naik
        int r = 0, c = 0; // inisialisasi
        while (r < this.row && c < this.col) {
            if (this.mat[r][c] != 0) { // terdapat elemen bukan 0 dari kolom (pivot)
                for (int i = r - 1; i >= 0; i--) { // penjumlahan baris untuk setiap baris sebelum r
                    this.addRow(i, r, -1 * this.mat[i][c]);
                }
                r++;
            }
            c++;
        }
        // r == this.row ATAU c == this.col
    }

    // Matriks Kofaktor
    // I.S. matriks terdefinisi, F.S. mengubah matriks menjadi matriks kofaktor

    public Matrix getTempKofaktor(int p, int q) {
        Matrix tempMat = new Matrix(this.col - 1, this.row - 1);

        int i = 0;
        int j = 0;
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                if (r != p && c != q) {
                    tempMat.mat[i][j++] = this.mat[r][c];

                    if (j == this.col - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return tempMat;

    }

    public Matrix kofaktor() {
        Matrix resultMat = new Matrix(this.col, this.row);
        Matrix tempMat = new Matrix(this.col - 1, this.row - 1);
        double det;

        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {

                tempMat = this.getTempKofaktor(r, c);
                det = Determinant.determinanEliminasiGauss(tempMat);

                if ((r + c + 2) % 2 == 0) {
                    resultMat.mat[r][c] = det;
                } else {
                    resultMat.mat[r][c] = -1 * det;
                }

            }
        }
        return resultMat;
    }

    public Matrix adjoint() {
        Matrix resultMat = new Matrix(this.col, this.row);
        Matrix kofaktorMat = new Matrix(this.col, this.row);

        kofaktorMat = this.kofaktor();
        resultMat = kofaktorMat.transpose();

        return resultMat;
    }
}
