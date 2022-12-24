package com.suai.laba5.Matrixes;


public class ParallelMatrixProduct {
    private int amountOfThreads;


    private static class MatrixThread extends Thread {
        private UsualMatrix resultMatrix;
        private UsualMatrix firstMatrix;
        private UsualMatrix secondMatrix;
        private int startRow;
        private int endRow;

        public MatrixThread(UsualMatrix firstMatrix, UsualMatrix secondMatrix, UsualMatrix resultMatrix, int startRow, int endRow) {
            this.firstMatrix = firstMatrix;
            this.secondMatrix = secondMatrix;
            this.resultMatrix = resultMatrix;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            //System.out.println("Считаю со строки " + startRow + " до строки " + endRow + " включительно");
            for (int row = this.startRow; row <= this.endRow; row++) {
                for (int col = 0; col < this.resultMatrix.getColumns(); col++) {
                    this.resultMatrix.setElement(row, col, calcSingleValue(row, col));
                }
            }
        }

        private int calcSingleValue(int rowIdx, int colIdx) {
            int num = 0;
            for (int i = 0; i < this.secondMatrix.getRows(); i++)
                num += this.firstMatrix.getElement(rowIdx, i) * this.secondMatrix.getElement(i, colIdx);

            return num;
        }
    }


    public ParallelMatrixProduct(int amountOfThreads) {
        if (amountOfThreads > 0)
            this.amountOfThreads = amountOfThreads;
        else
            throw new IllegalArgumentException("Amount of Threads can't be negative number!");
    }

    public UsualMatrix multiThreadingMultiply(UsualMatrix firstMatrix, UsualMatrix secondMatrix) { // координатор потоков

        if (firstMatrix == null || secondMatrix == null)
            throw new NullPointerException("Input matrix is a null parameter");
        if (firstMatrix.getColumns() != secondMatrix.getRows())
            throw new IllegalArgumentException("Amount of columns first matrix not equal amount of rows!");

        UsualMatrix resultMatrix = new UsualMatrix(firstMatrix.getRows(), secondMatrix.getColumns());
        if (this.amountOfThreads > resultMatrix.getRows()) // если потоков больше, чем строк
            this.amountOfThreads = resultMatrix.getRows();

        int count = firstMatrix.getRows() / this.amountOfThreads; // сколько строк будет высчитывать один поток
        int additional = firstMatrix.getRows() % this.amountOfThreads; //если не делится на threadsCount, то добавим к первому потоку

        Thread[] threads = new Thread[this.amountOfThreads];

        int start = 0;
        int amountRowsForThread = 0;
        for (int i = 0; i < this.amountOfThreads; i++) {

            if (i == 0) {
                amountRowsForThread = count + additional;
                threads[i] = new MatrixThread(firstMatrix, secondMatrix, resultMatrix, start, start + amountRowsForThread - 1);
            } else {
                amountRowsForThread = count;
                threads[i] = new MatrixThread(firstMatrix, secondMatrix, ((MatrixThread) threads[i - 1]).resultMatrix, start, start + amountRowsForThread - 1);
            }

            start += amountRowsForThread; // пересчитываем индекс для некст потока
            threads[i].start(); // запускаем поток
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        return ((MatrixThread) threads[this.amountOfThreads - 1]).resultMatrix;
    }


}
