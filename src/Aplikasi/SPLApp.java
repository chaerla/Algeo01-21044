package Aplikasi;

import Matrix.*;
import java.io.*;
import java.util.*;
import Utils.*;

public class SPLApp {
    private static Scanner in = new Scanner(System.in);

    public static boolean fromFile() {
        System.out.println();
        System.out.println("######## PILIHAN INPUT ########");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        int opt = 0;
        boolean isValid = false;
        boolean isFromFile = false;
        // if (fromFile == 1) {
        //     return false;
        // }
        // return true;
        while (!isValid) {
            System.out.print("Masukkan pilihan input (1/2): ");
            try {
                opt = in.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch (opt) {
                case 1:
                    isFromFile = false;
                    isValid = true;
                    break;
                case 2:
                    isFromFile = true;
                    isValid = true;
                    break;
                default:
                    System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.\n");
            }
        }
        return isFromFile;
        
    }

    public static void menu() {
        System.out.println();
        System.out.println("*************************************************************************");
        System.out.println("                        SISTEM PERSAMAAN LINIER");
        System.out.println("*************************************************************************");
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.print("Masukkan pilihan metode (1-4): ");
        int method = 0;
        boolean inputValid = true;
        try {
            method = in.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Matrix m = new Matrix();
        String res = new String();
        if (1 <= method && method <= 4){
            if (fromFile()) {
                    m = Utils.readMatrixFromFile();
                } else {
                    m.inputMatrix();
                }
        }
        switch (method) {
            case 1:
                res = SPL.gaussMethod(m);
                break;
            case 2:
                res = SPL.gaussjordanMethod(m);
                break;
            case 3:
                res = SPL.inversMethod(m);
                break;
            case 4:
                res = SPL.cramersRule(m);
                break;
            default:
                inputValid = false;
                System.out.println("Input tidak dikenali. Mohon hanya masukkan bilangan bulat 1 hingga 4.\n");
        }
        if (inputValid) {
            if (m != null) {
                System.out.print(res);
                Utils.stringToFile(res);
            }
        }

    }
}
