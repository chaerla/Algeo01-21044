package Matrix;

public class Matrix {
    int row;
    int col;
    double mat[][];

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.mat = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.mat[i][j] = 0;
            }
        }
    }

    public void displayMatrix() {
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                System.out.print(this.mat[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public void readMatrix() {
        
    }

    public Matrix transpose() {
        Matrix tempMat = new Matrix(this.col, this.row);
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                tempMat.mat[i][j] = this.mat[j][i];
            }
        }
        return tempMat;
    }


}
