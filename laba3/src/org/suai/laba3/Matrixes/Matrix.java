package org.suai.laba3.Matrixes;

import org.suai.laba3.MyExeptions.MyException;
import java.util.Scanner;
import  java.util.Arrays;


public class Matrix {
    protected final int rows;
    protected final int columns;
    protected final int[][] data;


    public Matrix(int row, int col) {
        try {
            if (row <= 0 | col <= 0)
                throw new MyException("Init size is negative number");
        }
        catch (MyException e){
            System.err.println(e.getErrorMessage());
            e.printStackTrace();
            System.exit(1);
        }
        this.rows = row;
        this.columns = col;
        this.data = new int [this.rows][this.columns];
    }

    public final void matrixInput(){ // ваще это не надо, но я написал на всякий, пользовательский ввод матрицы
        Scanner console = new Scanner(System.in);
        System.out.println("Input matrix: ");
        for(int i = 0; i < this.data.length; i++)
            for(int j = 0; j < this.data[i].length; j++) {
                System.out.print("pos[" + (i + 1) + "][" + (j + 1) + "]" + " = ");
                this.data[i][j] = console.nextInt();
            }
        System.out.println("Result Matrix:\n" + this);
    }

    public final String toString() { // метод для вывода матрицы на экран через System.out
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.data[i].length; j++)
                sb.append(this.data[i][j]).append("\t");
            sb.append("\n");
        }
        return sb.toString();
    }

    public Matrix product(Matrix m) { // метод для умножения матриц(может принимать SquareMatrix, т.к. наследник)
        if(m == null)
            throw new MyException("Input matrix is a null parameter");
        if(this.columns != m.rows)
            throw  new MyException("Amount of columns first matrix not equal amount of rows!");

        Matrix res = new Matrix(this.rows, m.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < m.columns; j++) {
                for (int k = 0; k < this.columns; k++)
                    res.data[i][j] += this.data[i][k] * m.data[k][j];
            }
        }
        return res;
    }

    public Matrix sum(Matrix m) { // метод для суммы матриц(может принимать SquareMatrix, т.к. наследник)
        if(m == null)
            throw new MyException("Input matrix is a null parameter");

        if(this.rows != m.rows | this.columns != m.columns) {
           throw new MyException("Matrix sizes are not equals!");
       }

        Matrix res = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.data.length; i++)
            for (int j = 0; j < this.columns; j++)
                res.data[i][j] = this.data[i][j] + m.data[i][j];
        return res;
    }

    public final void setElement(int row, int col, int value) {
        if (row > this.rows | col > this.columns | row < 0 | col < 0)
            throw new MyException("This position not found!");

        this.data[row - 1][col - 1] = value;
    }

    public final int getElement(int row, int col) {
        if (row > this.rows | col > this.columns | row < 0 | col < 0)
            throw new MyException("This position not found!");

        return this.data[row - 1][col - 1];
    }


    public boolean equals(Matrix m){ // для проверки равенства двух матриц
        return this.rows == m.rows & this.columns == m.columns & Arrays.deepEquals(this.data, m.data);
    }

}
