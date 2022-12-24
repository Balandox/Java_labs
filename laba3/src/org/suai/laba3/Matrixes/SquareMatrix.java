package org.suai.laba3.Matrixes;

import org.suai.laba3.Matrixes.Matrix;

public class SquareMatrix extends Matrix { // наследник(квардратные матрцы)

    public SquareMatrix(int size) {
        super(size, size);
        for(int i = 0; i < this.rows; i++)
            for(int j = 0; j < this.rows; j++)
                if(i == j)
                    this.data[i][j] = 1;
    }

    @Override
    public Matrix sum(Matrix m) {
        Matrix res = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.data.length; i++)
            for (int j = 0; j < this.columns; j++)
                res.data[i][j] = this.data[i][j] + m.data[i][j];
        return res;
    }
}
