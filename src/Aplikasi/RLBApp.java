package Aplikasi;

import Matrix.*;
import java.util.*;
import java.io.*;
import Utils.*;

public class RLBApp {
    private static Scanner in = new Scanner(System.in);

    public static Matrix createNormalEq(Matrix m) {
        Matrix rlbSPL = new Matrix(m.col, m.col + 1);
        Matrix rlbX = new Matrix();
        Matrix rlbY = new Matrix();
        m.splitMatrix(rlbX, rlbY, m.col - 1);
        for (int i = 0; i < rlbSPL.row; i++) {
            for (int j = 0; j < rlbSPL.col - 1; j++) {
                if (i == 0) {
                    if (j == 0) {
                        rlbSPL.mat[i][j] = m.row;
                    } else {
                        rlbSPL.mat[i][j] = 0;
                        for (int k = 0; k < m.row; k++) {
                            rlbSPL.mat[i][j] += m.mat[k][j - 1];
                        }
                    }
                } else if (j == 0) {
                    rlbSPL.mat[i][j] = 0;
                    for (int k = 0; k < m.row; k++) {
                        rlbSPL.mat[i][j] += m.mat[k][i - 1];
                    }
                } else {
                    rlbSPL.mat[i][j] = 0;
                    for (int k = 0; k < m.row; k++) {
                        rlbSPL.mat[i][j] += m.mat[k][i - 1] * m.mat[k][j - 1];
                    }
                }
            }
            rlbSPL.mat[i][rlbSPL.col - 1] = 0;
            for (int k = 0; k < m.row; k++) {
                if (i == 0) {
                    rlbSPL.mat[i][rlbSPL.col - 1] += m.mat[i][m.col - 1];
                } else {
                    rlbSPL.mat[i][rlbSPL.col - 1] += m.mat[k][i - 1] * m.mat[k][m.col - 1];
                }
            }
        }
        return rlbSPL;
    }

    public static Matrix readFromFile() {
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
                if (colcnt == 0) {
                    colcnt = temp.length;
                }
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        }
        ret.row = rowcnt - 1;
        ret.col = colcnt;
        ret.mat = new double[rowcnt - 1][colcnt];
        try {
            File file = new File("../test/input/" + fileName);
            Scanner fReader = new Scanner(file);
            int i = 0;
            while (i < rowcnt - 1) {
                String s = fReader.nextLine();
                String[] temp = s.split(" ", 0);
                for (int j = 0; j < temp.length; j++) {
                    ret.mat[i][j] = Utils.toDouble(temp[j]);
                }
                i++;
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public static Matrix readFromKey() {
        int n, m;
        System.out.print("Masukkan jumlah peubah (n): ");
        n = in.nextInt();
        System.out.print("Masukkan jumlah sampel (m): ");
        m = in.nextInt();
        System.out.print("Masukkan x1i, x2i,... xni, y");
        Matrix matrixInput = new Matrix(m, n + 1);
        matrixInput.readMatrix();

        return matrixInput;
    }

    public static void menu() {
        Matrix input = readFromFile();
        input = createNormalEq(input);
        input.displayMatrix();
    }
}
