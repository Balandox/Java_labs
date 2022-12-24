import com.suai.laba5.Matrixes.IMatrix;
import com.suai.laba5.Matrixes.ParallelMatrixProduct;
import com.suai.laba5.Matrixes.UsualMatrix;

import java.util.Random;


public class Main {

    public static void main(String[] args) {
        int size = 1500;
        Random rand = new Random();
        UsualMatrix firstMatrix = new UsualMatrix(size, size);
        UsualMatrix secondMatrix = new UsualMatrix(size, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int valFirst = rand.nextInt(9) + 1;
                int valSecond = rand.nextInt(9) + 1;
                firstMatrix.setElement(i, j, valFirst);
                secondMatrix.setElement(i, j, valSecond);
            }
        }
        long timeMultiThreading = System.currentTimeMillis();

        ParallelMatrixProduct parallelMatrixProduct = new ParallelMatrixProduct(1);
        UsualMatrix resultMatrixThread = (UsualMatrix) parallelMatrixProduct.multiThreadingMultiply(firstMatrix, secondMatrix);
        System.out.println("Time for multi threading multiplication of matrix: " + (System.currentTimeMillis() - timeMultiThreading));

        long timeDefaultMultiplication = System.currentTimeMillis();

        IMatrix resultMatrixDefault = firstMatrix.product(secondMatrix);
        System.out.println("Time for default multiplication of matrix: " + (System.currentTimeMillis() - timeDefaultMultiplication));

        System.out.println("Result of multithreading and default multiplication is equals: " + resultMatrixDefault.equals(resultMatrixThread));


    }

}