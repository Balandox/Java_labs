import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args){
        SortedIntegerList sl1 = new SortedIntegerList(true);
        sl1.add(12);
        sl1.add(3);
        sl1.add(-4);
        sl1.add(3);
        sl1.add(-18);
        sl1.add(7);
        System.out.println(sl1);
        sl1.remove(3);
        System.out.println("Resulting list after removing:\n" + sl1);
        SortedIntegerList sl2 = new SortedIntegerList(true);
        sl2.add(12);
        sl2.add(1);
        sl2.add(-4);
        sl2.add(3);
        sl2.add(-18);
        sl2.add(7);
        System.out.println(sl1.equals(sl2));

    }

}
