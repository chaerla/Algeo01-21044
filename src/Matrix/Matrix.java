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
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                System.out.print(this.mat[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    // Membaca input matriks dari user
    public void readMatrix(boolean isSquare) {
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
    public void addRow(int row1, int row2, int k) {
        for (int j = 0; j < this.col; j++) {
            this.mat[row1][j] += k * this.mat[row2][j];
        }

    }

    // Mengalikan semua elemen matriks dengan sebuah konstanta k
    public void scalarMulti(int k) {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.mat[i][j] *= k;
            }
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
}
