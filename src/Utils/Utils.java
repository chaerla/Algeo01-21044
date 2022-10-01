package Utils;

import Matrix.*;
import java.io.*;
import java.util.Scanner;
import java.math.*;

public class Utils {
    private static Scanner in = new Scanner(System.in);

    public static double toDouble(String str) {
        double ret = 0;
        String[] temp = str.split("/");
        if (temp.length == 1) {
            try {
                ret = Double.parseDouble(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ret = Double.parseDouble(temp[0]) / Double.parseDouble(temp[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static void matrixToFile(Matrix m) {
        System.out.println();
        System.out.print("Apakah Anda ingin menyimpan hasil ke dalam sebuah file (Y/N)? ");
        String resp = (in.nextLine()).toUpperCase();
        switch (resp) {
            case "Y":
                String fileName = new String();
                System.out.print("Masukkan nama file: ");
                fileName = in.nextLine();
                try {
                    FileWriter fWriter = new FileWriter("../test/output/" + fileName);
                    for (int i = 0; i < m.row; i++) {
                        for (int j = 0; j < m.col; j++) {
                            String temp = String.format("%.4f", m.mat[i][j] + 0.00000000);
                            fWriter.write(temp);
                            if (j != m.col - 1) {
                                fWriter.write(" ");
                            }
                        }
                        fWriter.write("\n");
                    }
                    fWriter.close();
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
                break;
            case "N":
                System.out.println("Anda tidak melakukan penyimpanan hasil.");
                break;
            default:
                System.out.println("Input tidak dikenali. Hasil tidak disimpan.");
                break;
        }
        // String fileName = new String();
        // System.out.print("Masukkan nama file: ");
        // fileName = in.nextLine();
        // try {
        // FileWriter fWriter = new FileWriter("../test/output/" + fileName);
        // for (int i = 0; i < m.row; i++) {
        // for (int j = 0; j < m.col; j++) {
        // String temp = Double.toString(m.mat[i][j]);
        // fWriter.write(temp);
        // if (j != m.col - 1) {
        // fWriter.write(" ");
        // }
        // }
        // fWriter.write("\n");
        // }
        // fWriter.close();
        // } catch (IOException e) {
        // System.out.print(e.getMessage());
        // }
    }

    public static void stringToFile(String s) {
        System.out.println();
        System.out.print("Apakah Anda ingin menyimpan hasil ke dalam sebuah file (Y/N)? ");
        String resp = (in.nextLine()).toUpperCase();
        switch (resp) {
            case "Y":
                String fileName = new String();
                System.out.print("Masukkan nama file: ");
                fileName = in.nextLine();
                try {
                    FileWriter fWriter = new FileWriter("../test/output/" + fileName);
                    fWriter.write(s);
                    fWriter.close();
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
                break;
            case "N":
                System.out.println("Anda tidak melakukan penyimpanan hasil.");
                break;
            default:
                System.out.println("Input tidak dikenali. Hasil tidak disimpan.");
                break;
        }
        // String fileName = new String();
        // System.out.print("Masukkan nama file: ");
        // fileName = in.nextLine();
        // try {
        // FileWriter fWriter = new FileWriter("../test/output/" + fileName);
        // fWriter.write(s);
        // fWriter.close();
        // } catch (IOException e) {
        // System.out.print(e.getMessage());
        // }
    }

    public static Matrix readMatrixFromFile() {
        String fileName = new String();
        System.out.print("Masukkan nama file: ");
        fileName = in.nextLine();
        int rowcnt = 0;
        int colcnt = 0;
        Matrix ret = new Matrix();
        try {
            File file = new File("../test/input/" + fileName);
            Scanner fReader = new Scanner(file);
            while (fReader.hasNextLine()) {
                String s = fReader.nextLine();
                String[] temp = s.split(" ", 0);
                rowcnt++;
                colcnt = temp.length;
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        }
        ret.row = rowcnt;
        ret.col = colcnt;
        ret.mat = new double[rowcnt][colcnt];
        try {
            File file = new File("../test/input/" + fileName);
            Scanner fReader = new Scanner(file);
            int i = 0;
            while (fReader.hasNextLine()) {
                String s = fReader.nextLine();
                String[] temp = s.split(" ", 0);
                for (int j = 0; j < temp.length; j++) {
                    ret.mat[i][j] = Utils.setPrec(Utils.toDouble(temp[j]), 8);
                }
                i++;
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public static double setPrec(double num, int decPlaces) {
        BigDecimal bd = new BigDecimal(num).setScale(decPlaces, RoundingMode.HALF_UP);
        double res = bd.doubleValue();
        return res;
    }
}
