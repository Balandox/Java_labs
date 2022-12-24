package com.suai.laba5.Matrixes;

import com.suai.laba5.Matrixes.IMatrix;
import com.suai.laba5.Matrixes.SparseMatrix;

import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class UsualMatrix implements IMatrix {
    protected final int rows;
    protected final int columns;
    protected final int[][] data;


    public UsualMatrix(int row, int col) {
        try {
            if (row <= 0 | col <= 0)
                throw new NegativeArraySizeException("Init size is negative number");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        this.rows = row;
        this.columns = col;
        this.data = new int[row][col];
    }

    public final void matrixInput() {
        Scanner console = new Scanner(System.in);
        System.out.println("Input matrix: ");
        for (int i = 0; i < this.data.length; i++)
            for (int j = 0; j < this.data[i].length; j++) {
                System.out.print("pos[" + (i + 1) + "][" + (j + 1) + "]" + " = ");
                this.data[i][j] = console.nextInt();
            }
        System.out.println("Init Matrix:\n" + this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++)
                sb.append(this.getElement(i, j)).append("\t\t");
            sb.append("\n");
        }
        return sb.toString();
    }

    public IMatrix product(IMatrix m) {
        if (m == null)
            throw new NullPointerException("Input matrix is a null parameter");
        if (this.columns != m.getRows())
            throw new IllegalArgumentException("Amount of columns first matrix not equal amount of rows!");

        IMatrix res = new UsualMatrix(this.rows, m.getColumns());
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                for (int k = 0; k < this.columns; k++)
                    res.setElement(i, j, res.getElement(i, j) + (this.getElement(i, k) * m.getElement(k, j)));
            }
        }
        return res;
    }

    public IMatrix sum(IMatrix m) {
        if (m == null)
            throw new NullPointerException("Input matrix is a null parameter");

        if (this.rows != m.getRows() | this.columns != m.getColumns())
            throw new IllegalArgumentException("Matrix sizes are not equals!");

        IMatrix res = new UsualMatrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.columns; j++)
                res.setElement(i, j, this.getElement(i, j) + m.getElement(i, j));
        return res;
    }


    public void setElement(int row, int col, int value) {
        if (row > this.rows | col > this.columns | row < 0 | col < 0)
            throw new IllegalArgumentException("This position does not exist!");

        this.data[row][col] = value;
    }

    public int getElement(int row, int col) {
        if (row > this.rows | col > this.columns | row < 0 | col < 0)
            throw new IllegalArgumentException("This position does not exist!");

        return this.data[row][col];
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof IMatrix))
            return false;
        IMatrix matrix = (IMatrix) o;
        if (matrix.getColumns() != this.getColumns() & matrix.getRows() != this.getRows())
            return false;
        for (int i = 0; i < this.getRows(); i++)
            for (int j = 0; j < this.getColumns(); j++) {
                if (!(this.getElement(i, j) == matrix.getElement(i, j)))
                    return false;
            }
        return true;
    }

}