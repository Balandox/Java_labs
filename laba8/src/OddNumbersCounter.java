import com.suai.laba5.Matrixes.ParallelMatrixProduct;
import com.suai.laba5.Matrixes.UsualMatrix;

import java.util.ArrayList;
import java.util.Random;

public class OddNumbersCounter{

    int amountOfThreads;

    private static class CounterThread extends Thread{
        private ArrayList<Integer> listOfNum;
        private int resultCounter;
        private int startIdx;
        private int endIdx;

        public CounterThread(ArrayList<Integer> list, int counter, int startIdx, int endIdx){
            this.listOfNum = list;
            this.resultCounter = counter;
            this.startIdx = startIdx;
            this.endIdx = endIdx;
        }

        @Override
        public void run() {
            for (int i = this.startIdx; i <= this.endIdx; i++) {
                if(this.listOfNum.get(i) % 2 != 0)
                        this.resultCounter += 1;
            }
        }

    }


    public int calcOddNum(ArrayList<Integer> array, int threadNum){ // координатор потоков
        this.amountOfThreads = threadNum;

        if (this.amountOfThreads > array.size()) // если потоков больше, чем элементов
            this.amountOfThreads = array.size();

        int count = array.size() / this.amountOfThreads; // сколько строк будет высчитывать один поток
        int additional = array.size() % this.amountOfThreads; //если не делится на threadsCount, то добавим к первому потоку

        Thread[] threads = new Thread[this.amountOfThreads];

        int start = 0;
        int amountElemForThread = 0;
        int countOfOddNumbers = 0;

        for (int i = 0; i < this.amountOfThreads; i++) {

            if (i == 0) {
                amountElemForThread = count + additional;
                threads[i] = new CounterThread(array, countOfOddNumbers, start,start + amountElemForThread - 1);
            } else {
                amountElemForThread = count;
                threads[i] = new CounterThread(array, ((CounterThread) threads[i - 1]).resultCounter, start, start + amountElemForThread - 1);
            }

            start += amountElemForThread; // пересчитываем индекс для некст потока
            threads[i].start(); // запускаем поток
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        int sum = 0;

        for(int i = 0; i < threads.length; i++){
            sum += ((CounterThread)threads[i]).resultCounter;
        }

        return sum;

    }

    public static void main(String[] args) {
        int size = 150000000;
        Random rand = new Random();
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < size; i++){
            int val = rand.nextInt(9) + 1;
            list.add(val);
        }


        OddNumbersCounter oddNumbersCounter = new OddNumbersCounter();
        long timeMultiThreading = System.currentTimeMillis();
        System.out.println("Amount off odd numbers(multithreading algorithm): " + oddNumbersCounter.calcOddNum(list, 12));
        System.out.println("Time for multithreading calculate amount of odd numbers: " + (System.currentTimeMillis() - timeMultiThreading));

        long timeDefault = System.currentTimeMillis();
        System.out.println("Amount off odd numbers(default algorithm): " + oddNumbersCounter.calcOddNum(list, 1));
        System.out.println("Time for default calculate amount of odd numbers: " + (System.currentTimeMillis() - timeDefault));


    }

}
