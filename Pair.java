import java.util.*;

public class Pair {
    public Case c1, c2;

    public Pair(Case c1, Case c2) {
        this.c1 = c1.id <= c2.id ? c1 : c2;
        this.c2 = c1.id <= c2.id ? c2 : c1;
    }

    public String toString(){
        return "["+c1+", "+c2+"]";
    }
}