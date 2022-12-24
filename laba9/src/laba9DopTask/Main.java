package laba9DopTask;

public class Main {

    public static void main(String[] args) {

        WordCounter wordCounter = new WordCounter();
        wordCounter.calculateAmountOfWords(args);
        System.out.println(wordCounter);
    }

}
