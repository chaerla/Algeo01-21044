import Matrix.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Matrix m = new Matrix(3, 3);
        // testing funcs
        m.displayMatrix();
        m.readMatrix(false);
        m.displayMatrix();
        Matrix mt = new Matrix(m.row, m.col);
        mt = m.transpose();
        mt.displayMatrix();
    }
}
