package Aplikasi;

import Matrix.*;
import Utils.*;
import java.io.*;
import java.lang.Math.*;
import java.util.*;

public class InterpolasiApp {
    private static Scanner in = new Scanner(System.in);

    // Mengubah input titik menjadi Augmented Matrix persamaan
    public static Matrix inputToMatrix(int n) {
        Matrix res = new Matrix(n, n + 1);
        Matrix inputMat = new Matrix(n, 2);
        inputMat.readMatrix();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (j == n) {
                    res.mat[i][j] = inputMat.mat[i][1];
                } else {
                    res.mat[i][j] = Math.pow(inputMat.mat[i][0], j);
                }
            }
        }

        return res;
    }

    // Membaca file dan menghasilkan ret sebagai matriks titik, x sebagai array nilai yang ditaksir
    public static void readFileInterpolasi(Matrix ret, double[] x) {
        String fileName = new String();
        in.nextLine();
        System.out.print("Masukkan nama file: ");
        fileName = in.nextLine();
        int rowcnt = 0;
        try {
            File file = new File("../test/input/" + fileName);
            Scanner fReader = new Scanner(file);
            while (fReader.hasNextLine()) {
                String s = fReader.nextLine();
                String[] temp = s.split(" ", 0);
                rowcnt++;
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        }
        ret.row = rowcnt - 1;
        ret.col = 2;
        ret.mat = new double[rowcnt - 1][2];
        try {
            File file = new File("../test/input/" + fileName);
            Scanner fReader = new Scanner(file);
            int i = 0;
            while (i < rowcnt - 1) {
                String s = fReader.nextLine();
                String[] temp = s.split(" ", 0);
                for (int j = 0; j < 2; j++) {
                    ret.mat[i][j] = Utils.toDouble(temp[j]);
                }
                i++;
            }
            String temp = fReader.nextLine();
            x[0] = Utils.toDouble(temp);
            fReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
    }

    // Mengubah matriks titik menjadi Augmented Matrix persamaan
    public static Matrix fileToMatrix(Matrix inputMat) {
        Matrix res = new Matrix(inputMat.row, inputMat.row + 1);

        for (int i = 0; i < inputMat.row; i++) {
            for (int j = 0; j < inputMat.row + 1; j++) {
                if (j == inputMat.row) {
                    res.mat[i][j] = inputMat.mat[i][1];
                } else {
                    res.mat[i][j] = Math.pow(inputMat.mat[i][0], j);
                }
            }
        }

        return res;
    }

    // Menyimpan solusi Augmented Matrix dalam bentuk Matrix
    public static Matrix solusiMatrix(Matrix m) {
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m.splitMatrix(m1, m2, m.col - 1);
        m1 = Inverse.inversiGaussJordan(m1);
        Matrix ansMat = Matrix.multiplyMat(m1, m2);

        return ansMat;
    }

    // Menghasilkan string polinom dari solusi yang diperoleh
    public static String printPolinom(Matrix m) {
        String res = "f(x) = ";
        for (int i = m.row - 1; i >= 0; i--) {
            if (i == 0) {
                res += (m.mat[i][0] <= 0 ? " " : " + ") + String.format("%.4f", m.mat[i][0]);
            } else if (i == 1) {
                res += (m.mat[i][0] <= 0 ? " " : " + ") + String.format("%.4f", m.mat[i][0]) + "x";
            } else {
                res += (m.mat[i][0] <= 0 ? " " : " + ") + String.format("%.4f", m.mat[i][0]) + "x^" + i;
            }
        }
        return res;
    }

    // Menghasilkan string hasil taksiran nilai x dari polinom yang telah diperoleh
    public static String printTaksiran(Matrix m, double x) {
        double taksiran = 0;
        String res = "f(" + x + ") = ";

        for (int i = 0; i < m.row; i++) {
            taksiran += m.mat[i][0] * Math.pow(x, i);
        }

        res += String.format("%.4f", taksiran);
        return res;
    }

    public static void menu() {
        System.out.println();
        System.out.println("*************************************************************************");
        System.out.println("                           INTERPOLASI POLINOM");
        System.out.println("*************************************************************************");
        System.out.println("1. Keyboard input");
        System.out.println("2. File input");
        System.out.print("Masukkan pilihan input: ");
        int method = 0;
        boolean inputValid = false;

        Matrix mat = new Matrix();
        Matrix matPoint = new Matrix();
        double x = 0;
        double[] taksir = new double[1];

        // Input Method
        try {
            method = in.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Perulangan hingga input user valis
        while (!inputValid) {
            // Switch Case Method, menginisialisasi Augmented Matrix sesuai pilihan input user
            switch (method) {
                case 1:
                    System.out.println("Masukkan jumlah pasangan titik: ");
                    int n = 0;
                    try {
                        n = in.nextInt();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Masukkan pasangan titik: ");
                    mat = inputToMatrix(n);
                    System.out.print("Masukkan nilai x yang ingin ditaksir nilai fungsinya: ");
                    
                    try {
                        x = in.nextDouble();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    inputValid = true;
                    break;
                case 2:
                    readFileInterpolasi(matPoint, taksir);
                    mat = fileToMatrix(matPoint);
                    x = taksir[0];
                    inputValid = true;
                    break;
                default:
                    System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.\n");
            }
        }

        if (inputValid) {
            Matrix ans = solusiMatrix(mat);

            String polinom = printPolinom(ans);
            System.out.println("Polinom yang melalui titik-titik tersebut yaitu: ");
            System.out.println(polinom);

            String res = polinom;

            String taksiran = printTaksiran(ans, x);
            System.out.println("Taksiran nilai fungsi dari nilai x yaitu: ");
            System.out.println(taksiran);
            res += "\n" + taksiran;

            Utils.stringToFile(res);
        }

    }
}
