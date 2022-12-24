import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class SortedIntegerList {

    private final LinkedList<Integer> data;
    private final boolean duplicate;

    public SortedIntegerList(boolean duplicate){
        this.duplicate = duplicate;
        this.data = new LinkedList<Integer>();
    }

        public void add(Integer element){
            ListIterator<Integer> it = this.data.listIterator();
            while(it.hasNext()){
            int tmpNum = (Integer)it.next();

            if(this.duplicate & element == tmpNum) {
                it.add(element);
                return;
            }
            else if(!this.duplicate & element == tmpNum)
                return;

            if(tmpNum > element) {
                it.previous();
                it.add(element);
                return;
            }
        }
        this.data.add(element);
    }


    public void remove(Integer num){

        ListIterator<Integer> it = this.data.listIterator();

        while(it.hasNext()){
            if(num.equals((Integer)it.next())) {
                it.remove();
                return;
            }
        }

    }

    public Integer size(){return this.data.size();}


   @Override
   public String toString(){
       StringBuilder sb = new StringBuilder();
     for(Integer elem : this.data)
        sb.append(elem).append("\t");
     return sb.toString();
  }


    public boolean equals(Object o) {
        if(this == o)
            return true;

      if (o instanceof SortedIntegerList) {
          SortedIntegerList sl = (SortedIntegerList) o;
          ListIterator<Integer> it1 = this.data.listIterator();
          ListIterator<Integer> it2 = sl.data.listIterator();
          if(sl.data.size() != this.data.size() | this.duplicate != sl.duplicate)
              return false;

          while(it1.hasNext()){
              if((Integer)it1.next() != (Integer)it2.next())
                  return false;
          }
          return true;
      }
      else
          return false;
  }


}