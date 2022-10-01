package Aplikasi;

import Matrix.*;
import java.util.*;
import Utils.*;

public class DeterminantApp {
    
    private static Scanner in = new Scanner(System.in);

    public static boolean fromFile() {
        System.out.println();
        System.out.println("######## PILIHAN INPUT ########");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        int opt = 0;
        boolean isValid = false;
        boolean isFromFile = false;
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
        System.out.println("                            DETERMINAN");
        System.out.println("*************************************************************************");
        System.out.println("1. Metode Reduksi Baris");
        System.out.println("2. Metode Ekspansi Kofaktor");
        System.out.print("Masukkan pilihan metode (1/2): ");
        int method = 0;
        boolean inputValid = true;
        try {
            method = in.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Matrix m = new Matrix();
        
        double det = 0;

        switch (method) {
            case 1:
                if (fromFile()) {
                    m = Utils.readMatrixFromFile();
                } else {
                    m.inputSquareMatrix();
                }
                det = Determinant.determinanEliminasiGauss(m);
                if (det == -0.0) {
                    det += 0.0;
                } 
                break;
            case 2:
                if (fromFile()) {
                    m = Utils.readMatrixFromFile();
                } else {
                    m.inputSquareMatrix();
                }
                det = Determinant.determinanKofaktor(m);
                break;
            default:
                inputValid = false;
                System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.\n");
        }
        if (inputValid) {
            System.out.println();
            System.out.print("Determinan: ");
            System.out.printf("%.4f", det);
            Utils.stringToFile(String.format("%.4f", det));
        }
    }
}
