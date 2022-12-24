import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.io.InputStreamReader;


public class Main {

    public static void main (String[] args){
        Object[] values;
        values = FormattedInput.scanf( "%d %s %f %C"); // scanf

        Scanner console = new Scanner(System.in);
        String format = "%d %f %c";
        while(true) {

            try {
                System.out.println("\nFormat: " + format);
                System.out.print("Enter symbols: ");
                String source = console.nextLine();
                values = FormattedInput.sscanf(format, source); // sscanf
                break;
            }
            catch(InputMismatchException exception){
                System.out.println(exception.getMessage() + ".Try again");

            }
        }

        System.out.println(Arrays.toString(values));

    }

}
