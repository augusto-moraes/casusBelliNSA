import java.util.*;

public class Pair implements Comparable<Pair>  {
    public Case case1, case2;

    public Pair(Case case1, Case case2) {
        this.case1 = case1.id <= case2.id ? case1 : case2;
        this.case2 = case1.id <= case2.id ? case2 : case1;
    }

    public boolean equals(Object obj){
        Pair other = (Pair)obj;
        return (other.case1 == this.case1 && other.case2 == this.case2);
    }

    public int compareTo(Pair other) {
        int res;

        if(this.case1.id == other.case1.id) {
            if(this.case2.id == other.case2.id) res=0;
            else if(this.case2.id > other.case2.id) res=1;
            else res=-1;
        } else if(this.case1.id > other.case1.id) res = 1;
        else res = -1;

        return res;
    }

    public String toString(){
        return "["+case1+", "+case2+"]";
    }
}
