package Aplikasi;

import Matrix.*;
import java.io.*;
import java.util.*;
import Utils.*;

public class InverseApp {
    private static Scanner in = new Scanner(System.in);

    public static void menu() {
        System.out.println("######## MATRIKS BALIKAN ########");
        System.out.println("1. Metode Eliminasi Gauss Jordan");
        System.out.println("2. Metode Kofaktor");
        System.out.print("Masukkan pilihan metode (1/2): ");
        int method = 0;
        boolean inputValid = true;
        try {
            method = in.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Matrix m = new Matrix();
        m.inputSquareMatrix();
        switch (method) {
            case 1:
                m = Inverse.inversiGaussJordan(m);
                break;
            case 2:
                m = Inverse.inversiKofaktor(m);
                break;
            default:
                inputValid = false;
                System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.\n");
        }
        if (inputValid) {
            if (m != null) {
                m.displayMatrix();
                Utils.matrixToFile(m);
            } else {
                String ret = "Matriks tidak memiliki balikan";
                System.out.println(ret);
                Utils.stringToFile(ret);
            }
        }

    }
}
