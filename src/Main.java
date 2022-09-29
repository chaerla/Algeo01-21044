import Aplikasi.*;
import java.util.*;

public class Main {
        private static Scanner in = new Scanner(System.in);

        public static void main(String[] args) throws Exception {
                int choice = -1;
                while (choice != 7) {
                        System.out.println();
                        System.out.println("*************************************************************************");
                        System.out.println("                                 MENU");
                        System.out.println("*************************************************************************");
                        System.out.println("1. Sistem Persamaan Linear");
                        System.out.println("2. Determinan");
                        System.out.println("3. Matriks Balikan");
                        System.out.println("4. Interpolasi Polinom");
                        System.out.println("5. Interpolasi Bicubic");
                        System.out.println("6. Regresi Linear Berganda");
                        System.out.println("7. Keluar");
                        System.out.print("Masukkan pilihan menu: ");
                        try {
                                choice = in.nextInt();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        switch (choice) {
                                case 1:
                                        // SPL
                                case 2:
                                        DeterminantApp.menu();
                                        break;
                                case 3:
                                        InverseApp.menu();
                                        break;
                                case 4:
                                        InterpolasiApp.menu();
                                        break;
                                case 5:
                                        BicubicInterpolationApp.menu();
                                        break;
                                case 6:
                                        RLBApp.menu();
                                        // RLB
                                case 7:
                                        System.out.println(
                                                        "*************************************************************************");
                                        System.out.println("Terima kasih telah menggunakan ");
                                        System.out.println(
                                                        "*************************************************************************");
                                        break;
                                default:
                                        System.out.println(
                                                        "Input tidak dikenali. Mohon hanya masukkan bilangan bulat antara 1-7.");
                                        break;
                        }
                }

        }
}
