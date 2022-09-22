import Matrix.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Matrix m = new Matrix(3, 3);
        // testing funcs
        m.displayMatrix();
        m.readMatrix();
        // Matrix m1 = new Matrix(m.row, m.col);
        // double d;
        // m1.displayMatrix();
        // Matrix mt = new Matrix(m1.row, m1.col);
        // mt = m1.transpose();
        // mt.displayMatrix();
        // Matrix m2 = new Matrix(3, 3);
        // m2.readMatrix();
        // Matrix m3 = Matrix.augMatrix(m1, m2);
        // m3.displayMatrix();
        //SPL.cramersRule(m);

        // System.out.println("\n Test Kofaktor \n");
        // m1 = m.kofaktor();
        // m1.displayMatrix();
        // d = Determinant.determinanKofaktor(m);
        // System.out.println("\nDeterminan adalah " + d);
        // m1 = Inverse.inversiKofaktor(m);
        // m1.displayMatrix();


        /*
         * System.out.println("\ntest IdMatrix\n");
         * //Matrix mId = new Matrix(0,0);
         * Matrix mId = Matrix.createIdMat(3);
         * mId.displayMatrix();
         * 
         * System.out.println("\ntest eliminasi gauss\n");
         * Matrix m4 = new Matrix(0, 0);
         * m4.readMatrix();
         * m4.eliminasiGauss();
         * m4.displayMatrix();
         * 
         * System.out.println("\ntest eliminasi gauss jordan\n");
         * Matrix m5 = new Matrix(0, 0);
         * m5.readMatrix();
         * m5.eliminasiGaussJordan();
         * m5.displayMatrix();
         */
    }
}
