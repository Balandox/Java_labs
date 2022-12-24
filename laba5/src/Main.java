import com.suai.laba5.Matrixes.*;

import java.awt.desktop.SystemEventListener;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Random;
import java.util.Objects;

public class Main {

    public static void main(String[] args){
        int size = 10;
        IMatrix us1 = new UsualMatrix(size, size);
        IMatrix us2 = new UsualMatrix(size, size);
        IMatrix sm1 = new SparseMatrix(size, size);
        IMatrix sm2 = new SparseMatrix(size, size);
        int seed = 1;
        for(int i = 0, j = 0; i < size; i++, j++){
            sm1.setElement(i, j, seed);
            sm2.setElement(i, j, seed);
            us1.setElement(i, j, seed);
            us2.setElement(i, j, seed);
            seed++;
        }
        System.out.println(sm1);
        System.out.println((sm1.product(sm1)).equals(us1.product(us1)));
    }

}
