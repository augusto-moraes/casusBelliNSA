import java.util.*;

public class Pair {
    public Case case1, case2;

    public Pair(Case case1, Case case2) {
        this.case1 = case1.id <= case2.id ? case1 : case2;
        this.case2 = case1.id <= case2.id ? case2 : case1;
    }

    public String toString(){
        return "["+case1+", "+case2+"]";
    }
}
