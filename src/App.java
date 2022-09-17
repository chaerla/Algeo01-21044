import Matrix.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Matrix m1 = new Matrix(3, 3);
        // testing funcs
        m1.displayMatrix();
        m1.readMatrix();
        m1.displayMatrix();
        Matrix mt = new Matrix(m1.row, m1.col);
        mt = m1.transpose();
        mt.displayMatrix();
        Matrix m2 = new Matrix(3, 3);
        m2.readMatrix();
        Matrix m3 = Matrix.augMatrix(m1, m2);
        m3.displayMatrix();

        System.out.println("\ntest eliminasi gauss\n");
        Matrix m4 = m2;
        m4.eliminasiGauss();
        m4.displayMatrix();
    }
}
