package Aplikasi;

import Matrix.*;
import java.util.*;
import java.io.*;
import Utils.*;

public class RLBApp {
    private static Scanner in = new Scanner(System.in);

    // Membuat normal estimastion equation berdasarkan Matrix m dalam bentuk
    // augmented matrix dan mengembalikan Matrix eselon tereduksi dari augmented
    // matrix tersebut.
    public static Matrix solve(Matrix m) {
        Matrix rlbSPL = new Matrix(m.col - 1, m.col);
        Matrix rlbX = new Matrix();
        Matrix rlbY = new Matrix();
        m.splitMatrix(rlbX, rlbY, m.col - 1);
        for (int i = 0; i < rlbSPL.row; i++) {
            for (int j = 0; j < rlbSPL.col - 1; j++) {
                rlbSPL.mat[i][j] = 0;
                for (int k = 0; k < m.row; k++) {
                    rlbSPL.mat[i][j] += m.mat[k][i] * m.mat[k][j];
                }
            }
            rlbSPL.mat[i][rlbSPL.col - 1] = 0;
            for (int k = 0; k < m.row; k++) {
                rlbSPL.mat[i][rlbSPL.col - 1] += m.mat[k][i] * m.mat[k][m.col - 1];
            }
        }
        rlbSPL.eliminasiGaussJordan();
        return rlbSPL;
    }

    // Membaca semua nilai-nilai x1i, x2i, ..., xni, nilai yi dari sebuah file, dan
    // menyimpannya ke Matrix ret, serta membaca nilai-nilai xk yang akan ditaksir
    // nilai fungsinya dari file yang sama dan menyimpannya ke Matrix x.

    public static void readFile(Matrix ret, Matrix x) {
        String fileName = new String();
        in.nextLine();
        System.out.print("Masukkan nama file: ");
        fileName = in.nextLine();
        int rowcnt = 0;
        int colcnt = 0;
        try {
            File file = new File("../test/input/" + fileName);
            Scanner fReader = new Scanner(file);
            while (fReader.hasNextLine()) {
                String s = fReader.nextLine();
                String[] temp = s.split(" ", 0);
                rowcnt++;
                if (colcnt == 0) {
                    colcnt = temp.length;
                }
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        }
        ret.row = rowcnt - (colcnt - 2);
        ret.col = colcnt + 1;
        ret.mat = new double[ret.row][ret.col];
        try {
            File file = new File("../test/input/" + fileName);
            Scanner fReader = new Scanner(file);
            int i = 0;
            while (i < ret.row) {
                if (i != 0) {
                    String s = fReader.nextLine();
                    String[] temp = s.split(" ", 0);
                    ret.mat[i][0] = 1.00000000;
                    for (int j = 1; j < ret.col; j++) {
                        ret.mat[i][j] = Utils.setPrec(Utils.toDouble(temp[j - 1]), 8);
                    }
                }
                i++;
            }
            x.row = ret.col - 1;
            x.col = 1;
            x.mat = new double[x.row][1];
            i = 0;
            while (fReader.hasNextLine()) {
                String s = fReader.nextLine();
                String[] temp = s.split(" ", 0);
                for (int j = 0; j < temp.length; j++) {
                    x.mat[i][j] = Utils.setPrec(Utils.toDouble(temp[j]), 8);
                }
                i++;
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    // Membaca semua nilai-nilai x1i, x2i, ..., xni, nilai yi dari keyboard, dan
    // menyimpannya ke Matrix ret, serta membaca nilai-nilai xk yang akan ditaksir
    // nilai
    // fungsinya dari keyboard dan menyimpannya ke Matrix x.

    public static void readKey(Matrix ret, Matrix x) {
        int n, m;
        System.out.print("Masukkan jumlah peubah (n): ");
        in.nextLine();
        n = in.nextInt();
        System.out.print("Masukkan jumlah sampel (m): ");
        in.nextLine();
        m = in.nextInt();
        in.nextLine();
        System.out.println("Masukkan x1i, x2i,... xni, y: ");
        ret.row = m + 1;
        ret.col = n + 2;
        ret.mat = new double[m + 1][n + 2];
        int i = 0;
        while (i < ret.row) {
            if (i != 0) {
                String s = in.nextLine();
                String[] temp = s.split(" ", 0);
                ret.mat[i][0] = 1.00000000;
                for (int j = 1; j < ret.col; j++) {
                    ret.mat[i][j] = Utils.setPrec(Utils.toDouble(temp[j - 1]), 8);
                }
            }
            i++;
        }
        x.row = n;
        x.col = 1;
        x.mat = new double[n][1];
        for (i = 0; i < n; i++) {
            System.out.print("Masukkan x ke-" + (i + 1) + ": ");
            x.mat[i][0] = in.nextDouble();
            System.out.println();
        }
    }

    // I.S. : Matrix m terdefinisi sebagai augmented matrix yang memuat koefisien
    // persamaan regresi dan Matrix x
    // terdefinisi sebagai matrix yang memuat nilai-nilai xk yang akan ditaksir
    // nilai fungsinya.
    // F.S. : Persamaan regresi dan hasil taksiran ditampilkan di layar dan atau
    // tersimpan ke dalam sebuah
    // file jika pengguna memilih untuk menyimpan hasil ke file.

    public static void output(Matrix m, Matrix x) {
        String regresi = "";
        double res = 0;
        boolean isFirst = true;
        for (int i = 0; i < m.row; i++) {
            if (Utils.setPrec(m.mat[i][i], 8) != 0) {
                if (isFirst) {
                    regresi += String.format("%.4f", m.mat[i][m.col - 1]);
                    isFirst = false;
                } else {
                    regresi += " + " + String.format("%.4f", m.mat[i][m.col - 1]);
                }
                if (i != 0) {
                    regresi += "x" + (i);
                    res += x.mat[i - 1][0] * m.mat[i][m.col - 1];
                } else {
                    res += m.mat[i][m.col - 1];
                }
            }
        }
        System.out.println();
        System.out.print("f(x) = ");
        System.out.println(regresi);
        String fx = "f(";
        for (int i = 0; i < x.row; i++) {
            fx += String.format(" %.2f ", x.mat[i][0]);
        }
        fx += ") = " + String.format("%.4f", res);
        System.out.println(fx);
    }

    public static void menu() {
        System.out.println();
        System.out.println("*************************************************************************");
        System.out.println("                         REGRESI LINEAR BERGANDA");
        System.out.println("*************************************************************************");
        System.out.println("1. Keyboard input");
        System.out.println("2. File input");
        System.out.print("Masukkan pilihan input: ");
        int method = 0;
        boolean inputValid = true;
        Matrix inputMat = new Matrix();
        Matrix x = new Matrix();
        try {
            method = in.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        switch (method) {
            case 1:
                readKey(inputMat, x);
                break;
            case 2:
                readFile(inputMat, x);
                break;
            default:
                inputValid = false;
                System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.\n");
        }
        if (inputValid) {
            inputMat = solve(inputMat);
            output(inputMat, x);
        }
    }
}
