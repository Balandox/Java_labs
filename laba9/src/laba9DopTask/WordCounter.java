package laba9DopTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {

    private Map<String, Integer> dataCounter;


    public WordCounter() {
        dataCounter = new HashMap<String, Integer>();
    }

    private static class FileThread extends Thread {

        private String fileName;
        private Map<String, Integer> oneFileMap;

        public FileThread(String fileName) {
            this.fileName = fileName;
            this.oneFileMap = new HashMap<>();
        }


        public void loadFromTextFileAndCount() {
            File file = new File("D:\\JavaProjects\\laba9\\src\\laba9DopTask\\" + this.fileName);
            Scanner scanner;
            try {
                scanner = new Scanner(file);

                while (scanner.hasNext()) {
                    String str = scanner.next();
                    if (this.oneFileMap.containsKey(str))
                        this.oneFileMap.put(str, this.oneFileMap.get(str) + 1);
                    else
                        this.oneFileMap.put(str, 1);
                }
            } catch (FileNotFoundException exception) {
                System.out.println("Error! File not found.");
            } catch (InputMismatchException exception) {
                System.out.println("Incorrect format of input data!");
            }
        }


        public void run() {
            this.loadFromTextFileAndCount();
        }

    }

    public void mergeMap(Thread[] threads) {
        this.dataCounter = ((FileThread) threads[0]).oneFileMap; // присвоим для начала первую карту

        for (int i = 1; i < threads.length; i++) {
            Map<String, Integer> tmpMap = ((FileThread) threads[i]).oneFileMap; // проходим по каждой карте

            for (Map.Entry<String, Integer> elem : tmpMap.entrySet()) {
                String key = elem.getKey();
                int value = elem.getValue();

                if (this.dataCounter.containsKey(key))
                    this.dataCounter.put(key, this.dataCounter.get(key) + value);
                else
                    this.dataCounter.put(elem.getKey(), elem.getValue());
            }
        }
    }

    public void calculateAmountOfWords(String[] args) {
        Thread[] threads = new Thread[args.length];

        for (int i = 0; i < args.length; i++) {
            threads[i] = new FileThread(args[i]);
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        this.mergeMap(threads);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Word frequency statistics for all files:");
        for (Map.Entry<String, Integer> pair : dataCounter.entrySet())
            sb.append(pair.getKey()).append(": ").append(pair.getValue()).append("\n");
        return sb.toString();
    }


}
