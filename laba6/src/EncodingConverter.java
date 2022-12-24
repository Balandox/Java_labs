import java.io.*;
import java.nio.charset.Charset;
import java.util.SortedMap;

public class EncodingConverter{

    public static void main(String[] args) throws IOException{
        FileInputStream fileInput = null;
        FileOutputStream fileOutput = null;
        if(args.length != 4){
            System.out.println("Incorrect parameters of command line!");
            return;
        }
        try {
            fileInput = new FileInputStream(args[0]);
            fileOutput = new FileOutputStream(args[1]);
        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
            return;
        }

        Reader reader = null;
        Writer writer = null;
        try {
            reader = new InputStreamReader(fileInput, args[2]);
            writer = new OutputStreamWriter(fileOutput, args[3]);
        } catch (UnsupportedEncodingException exception) {
            System.out.println("Unsupported encoding! Try again");
            return;
        }

        int ch = 0;
        try {
            writer.write(args[2] + " -> " + args[3] + "\n");
            while ((ch = reader.read()) >= 0)
                writer.write(ch);
        }
        catch (IOException exception){
            System.out.println("Error when reading from or writing to a file! Try again");
        }
        finally{
            writer.close();
            reader.close();
        }

    }

}
