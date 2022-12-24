import java.io.*;
import java.util.*;

public class LibraryStorage {

    private static class Book{
        private final String bookName;
        private final String authorName;
        private final int year;

        public Book(String bookName, String authorName, int year){
            this.bookName = bookName;
            this.authorName = authorName;
            this.year = year;
        }

        public String toString(){
            return this.bookName + " " + this.authorName + " " + this.year;
        }

    }

    private ArrayList<Book> dataBase;

    public LibraryStorage(){
        this.dataBase = new ArrayList<>();
    }

    public void add(String bookName, String authorName, int year){
        dataBase.add(new Book(bookName, authorName, year));
    }

    public Book find(String bookName, String authorName, int year){
        Book tmp = new Book(bookName, authorName, year);
        for(Book idx : this.dataBase){
            if(Objects.equals(idx.bookName, tmp.bookName) && Objects.equals(idx.authorName, tmp.authorName) && idx.year == tmp.year)
                return tmp;
        }
        return null;
    }

    public Book[] findYear(int year){
        ArrayList<Book> tmp = new ArrayList<>();

        for(Book idx: this.dataBase)
            if(idx.year > year)
                tmp.add(idx);

        return tmp.toArray(tmp.toArray(new Book[0]));
    }

    public void loadFromTextFile(String filename){
        File file = new File("D:\\JavaProjects\\laba7\\src\\" + filename);
        String author = "";
        String book = "";
        int year = 0;
        boolean flagName = false; // если имя уже считали
        boolean flagString = false;
        Scanner scanner;

        try{
            scanner = new Scanner(file);
            while(scanner.hasNext()){
                if(scanner.hasNextLine()) {
                    if(!flagName)
                        book = scanner.next();
                    else
                        author = scanner.next();
                    flagName = true;
                }
                if(scanner.hasNextInt()) {
                    year = scanner.nextInt();
                    flagString = true;
                }
                if(flagString && author.length() > 0 && book.length() > 0 && year > 0) {
                    this.dataBase.add(new Book(book, author, year));
                    flagName = false;
                    scanner.nextLine();
                    flagString = false;
                }
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("Error! File not found.");
        }
        catch (InputMismatchException exception){
            System.out.println("Incorrect format of input data!");
        }
    }

    public void saveToTextFile(String filename){
        try {
            FileWriter file = new FileWriter("D:\\JavaProjects\\laba7\\src\\" + filename);

            BufferedWriter buffer = new BufferedWriter(file);
            for (Book idx: this.dataBase)
                buffer.write(idx.bookName + " " + idx.authorName + " " + idx.year + "\n");
            buffer.close();
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }


    public static void main(String[] args) {
        LibraryStorage dataBase = new LibraryStorage();
        dataBase.loadFromTextFile("input.txt");
        dataBase.saveToTextFile("output.txt");
        System.out.println(dataBase.find("Book2", "Author2", 1692));
        dataBase.add("Book5", "Author5", 1874);
        System.out.println(Arrays.toString(dataBase.findYear(1700)));
        dataBase.saveToTextFile("output.txt");
        //dataBase.saveToTextFile("output.txt");

    }






}
