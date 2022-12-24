package org.suai.laba3;

import org.suai.laba3.Matrixes.Matrix;
import org.suai.laba3.Matrixes.SquareMatrix;
import org.suai.laba3.MyExeptions.MyException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Matrix m1 = new Matrix(3,3);
        m1.matrixInput();
        SquareMatrix m = new SquareMatrix(3);
        m1 = m.sum(m1); // демонстрируем, что сумма работает одновременно для Matrix and SquareMatrix
        System.out.println("Result matrix after sum\n" + m1);
        System.out.println("Second pow of previous matrix\n" + m1.product(m1)); // выводим на экран произведение, чтоб показать, что корректно работает
        try {
            m1.setElement(2, 2, -10); // првоеряем корректность работы set, обрабатываем все try and catch
        }
        catch (MyException e){
            System.out.println(e.getErrorMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            System.out.println(m1.getElement(2, 2)); // првоеряем корректность работы get, обрабатываем все try and catch
        }
        catch (MyException e){
            System.out.println(e.getErrorMessage());
            e.printStackTrace();
            System.exit(1);
        }

        if(m1.equals(m))
            System.out.println("This matrix is equals"); // тестируем метод equals(равенство матрци)
        else
            System.out.println("This matrix is not equals");

    }

}
