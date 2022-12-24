import java.io.IOException;
import java.util.*;

public class FormattedInput {

    public static Object[] scanf(String format){
        while(true) {

            try {
                System.out.print("Enter symbols " + format + ": ");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                Object[] vals = sscanf(format, str);
                scanner.close();
                return vals;
            }
            catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                //exception.printStackTrace();
                System.out.println();
            }
        }
    }

    public static Object[] sscanf(String format, String in){
        Scanner scanner = new Scanner(in);
        scanner.useLocale(Locale.UK);
        ArrayList<Object> vals = new ArrayList<>();
        int charArrayIdx = 0;

        while (scanner.hasNext()) {
            for (int i = 0; i < format.length(); ) {

                if (format.startsWith("%d", i)) {
                    if (scanner.hasNextInt()) {
                        vals.add(scanner.nextInt());
                        i += "%d".length();
                    }
                    else
                        throw new InputMismatchException("Incorrect value");

                }

                else if (format.startsWith("%f", i)) {
                    if (scanner.hasNextDouble()) {
                        vals.add(scanner.nextDouble());
                        i += "%f".length();
                    }
                    else
                        throw new InputMismatchException("Incorrect value");
                }

                else if(format.startsWith("%C", i)){
                    StringBuilder sizeValue = new StringBuilder();
                    charArrayIdx = i + 3;

                    if(charArrayIdx >= format.length()) // если криво указали размер в строке format
                        throw new InputMismatchException("Incorrect size of char array!");

                    while(Character.isDigit(format.charAt(charArrayIdx))) {
                        sizeValue.append(format.charAt(charArrayIdx));
                        charArrayIdx++;
                        if(charArrayIdx >= format.length())
                            break;
                    }

                    if(sizeValue.length() == 0)
                        throw new InputMismatchException("Incorrect size of char array!");
                    ArrayList<Character> charArray = new ArrayList<>();
                    int sizeCharArray = Integer.parseInt(sizeValue.toString());

                    while(scanner.hasNext()){
                        String str = scanner.next();        // начинаем считывать данные в массив
                        if(str.length() == 1)
                            charArray.add(str.charAt(0));

                        if(charArray.size() > sizeCharArray)
                            throw new InputMismatchException("Amount of char symbols more than input size of Array!");

                        if(charArray.size() == sizeCharArray)
                            break;
                    }
                    if(charArray.size() < sizeCharArray)
                        throw new InputMismatchException("Amount of char symbols less than input size of Array!");
                    else{
                        vals.add(charArray);
                        i += "%C".length();
                    }
                }

                else if (format.startsWith("%s", i)) {
                    if (scanner.hasNextLine()) {
                        vals.add(scanner.next());
                        i += "%s".length();
                    }
                }

                else if (format.startsWith("%c", i)) {
                    if (scanner.hasNext()) {
                            String str = scanner.next();
                        if(str.length() == 1)
                            vals.add(str.charAt(0));
                        i += "%c".length();
                    }
                    else
                        throw new InputMismatchException("Incorrect value");
                }
                else
                    i++;
            }
        }

        scanner.close();
        return vals.toArray();
    }
}
