
import java.io.*;
import java.util.*;

public class Settings {

    private Map<String, Integer> settingsData;

    public Settings(){
        this.settingsData = new HashMap<String, Integer>();
    }

    public void put(String setting, int value){
        this.settingsData.put(setting, value);
    }

    public int get(String setting){
        return this.settingsData.get(setting);
    }

    public int delete(String setting){

        return this.settingsData.remove(setting);
    }

   public void loadFromTextFile(String filename){
        File file = new File("D:\\JavaProjects\\laba7\\src\\" + filename);
        String key = "";
        boolean flagString = false;
        boolean flagInt = false;
        int value = 0;
        Scanner scanner;

        try{
            scanner = new Scanner(file);
            while(scanner.hasNext()){
                if(scanner.hasNextLine()) {
                    key = scanner.next();
                    flagString = true;
                }
                if(scanner.hasNextInt()) {
                    value = scanner.nextInt();
                    flagInt = true;
                }
                if(flagString && flagInt) {
                    this.settingsData.put(key, value);
                    flagString = false;
                    flagInt = false;
                    scanner.nextLine();
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
            for (Map.Entry<String, Integer> pair : this.settingsData.entrySet())
                buffer.write(pair.getKey() + ": " + pair.getValue() + "\n");
            buffer.close();
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }

   }

   public void saveToBinaryFile(String filename){
       try {
           FileOutputStream fos = new FileOutputStream("D:\\JavaProjects\\laba7\\src\\" + filename);
           byte[] buffer;
           for (Map.Entry<String, Integer> i : this.settingsData.entrySet()) {
               String s = i.getKey() + ": " + i.getValue() + "\n";
               buffer = s.getBytes();
               fos.write(buffer);
           }
       }
       catch (FileNotFoundException exception){
           System.out.println("File not found. Try again!");
       }
       catch (IOException exception){
           System.out.println("Cannot read data from file!");
       }
   }

   public void loadFromBinaryFile(String filename){
       byte[] buffer = null;
       FileInputStream fin = null;
        try {
           fin = new FileInputStream("D:\\JavaProjects\\laba7\\src\\" + filename);
           buffer = new byte[fin.available()];
           if(fin.read(buffer, 0, fin.available()) == -1){
               throw new IOException("Cannot read data from file");
           }
       }
       catch (FileNotFoundException exception){
           System.out.println("File not found. Try again!");
       }
       catch (IOException exception){
           System.out.println(exception.getMessage());
       }

       StringBuilder keyBuilder = new StringBuilder();
       StringBuilder valBuilder = new StringBuilder();

       boolean afterWord = false; // если true, то мы уже считали строку, осталось считать int
       int val = 0;

       for(int i = 0; i < buffer.length; i++) {

           if ((char) buffer[i] == '\n') {
               afterWord = false;
               val = Integer.parseInt(valBuilder.toString());
               this.settingsData.put(keyBuilder.toString(), val);
               keyBuilder.setLength(0);
               valBuilder.setLength(0);
           }

           else if ((char) buffer[i] == ' ') {
               afterWord = true; // мы уже считали строку или
               if(keyBuilder.length() > 0 && valBuilder.length() > 0) //если попали на некорректный пробел после int
                   while(buffer[i + 1] != '\n')
                       i++;
           }

           else if (!afterWord)
               keyBuilder.append((char)buffer[i]); // записываем элементы строки

           else if (afterWord && keyBuilder.length() != 0) {
               if (Character.isDigit((char)buffer[i]))
                   valBuilder.append((char) buffer[i]); // записываем эелементы значения int
           }
       }
   }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, Integer> pair : this.settingsData.entrySet())
            sb.append(pair.getKey()).append(": ").append(pair.getValue()).append("\n");
        return sb.toString();
    }

    public boolean equals(Object objectValue){
        if(this == objectValue)
            return true;
        if(!(objectValue instanceof Settings))
            return false;
        Settings cmpObject = (Settings) objectValue;

        return this.settingsData.equals(cmpObject.settingsData);
    }




}
