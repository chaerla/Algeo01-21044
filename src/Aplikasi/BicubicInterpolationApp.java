package Aplikasi;

import Matrix.*;
import Utils.*;
import java.lang.Math.*;
import java.io.*;
import java.util.*;

public class BicubicInterpolationApp {
    private static Scanner in = new Scanner(System.in);

    // persamaan matrix : y = Xa

    public static Matrix getMatrixX() {
        Matrix ret = new Matrix(16, 16);
        int row = 0;
        int col = 0;
        for (int y = -1; y <= 2; y++) {
            for (int x = -1; x <= 2; x++) {
                col = 0;
                for (int j = 0; j <= 3; j++) {
                    for (int i = 0; i <= 3; i++) {
                        ret.mat[row][col] = Math.pow(x, i) * Math.pow(y, j);
                        col++;
                    }
                }
                row++;
            }
        }
        return ret;
    }

    public static Matrix getMatrixY(Matrix m) {
        Matrix y = new Matrix(16, 1);
        int row = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                y.mat[row][0] = m.mat[i][j];
                row++;
            }
        }
        return y;
    }

    public static void readFile(Matrix inputMat, double[] point) {
        String fileName = new String();
        in.nextLine();
        System.out.print("Masukkan nama file: ");
        fileName = in.nextLine();
        try {
            File file = new File("../test/input/" + fileName);
            Scanner fReader = new Scanner(file);
            int i = 0;
            while (i < 4) {
                String s = fReader.nextLine();
                String[] temp = s.split(" ", 0);
                for (int j = 0; j < temp.length; j++) {
                    inputMat.mat[i][j] = Utils.toDouble(temp[j]);
                }
                i++;
            }
            String s = fReader.nextLine();
            String[] temp = s.split(" ", 0);
            for (int j = 0; j < temp.length; j++) {
                point[j] = Utils.toDouble(temp[j]);
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Matrix solve(double[] point, Matrix a) {
        Matrix X = new Matrix(1, 16);
        int row = 0;
        int col = 0;
        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i <= 3; i++) {
                X.mat[row][col] = Math.pow(point[0], i) * Math.pow(point[1], j);
                col++;
            }
        }
        Matrix res = Matrix.multiplyMat(X, a);
        return res;
    }

    public static void menu() {
        System.out.println();
        System.out.println("*************************************************************************");
        System.out.println("                           INTERPOLASI BICUBIC");
        System.out.println("*************************************************************************");
        System.out.println("1. Keyboard input");
        System.out.println("2. File input");
        System.out.print("Masukkan pilihan input: ");
        int method = 0;
        boolean inputValid = true;
        Matrix inputMat = new Matrix(4, 4);
        double[] point = new double[2];
        try {
            method = in.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        switch (method) {
            case 1:
                System.out.println("Matriks input harus berupa matriks 4x4.");
                inputMat.readMatrix();
                point[0] = Utils.setPrec(in.nextDouble(), 8);
                point[1] = Utils.setPrec(in.nextDouble(), 8);
                break;
            case 2:
                readFile(inputMat, point);
                break;
            default:
                inputValid = false;
                System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.\n");
        }
        if (inputValid) {
            Matrix X = getMatrixX();
            Matrix invX = Inverse.inversiGaussJordan(X);
            Matrix y = getMatrixY(inputMat);
            Matrix a = Matrix.multiplyMat(invX, y);
            double res = solve(point, a).mat[0][0];
            System.out.print("Hasil interpolasi bikubik f(");
            System.out.printf("%.2f", point[0]);
            System.out.print(",");
            System.out.printf("%.2f", point[1]);
            System.out.print("): ");
            System.out.printf("%.6f\n", res);
            Utils.stringToFile(String.format("%.6f", res));
        }
    }
}
