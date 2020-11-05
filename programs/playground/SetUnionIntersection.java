import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;


public class SetUnionIntersection {
    public static void main(String[] args) {
        Set<String> refSet = new HashSet<>();
        refSet.add("Washington");
        refSet.add("Alabama");
        refSet.add("Virginia");
        refSet.add("Minnesota");
        refSet.add("Illinois");

        Set<String> candidate  = new HashSet<>();
        candidate.add("Washington");
        candidate.add("Alabama");
        candidate.add("California");

        System.out.println("Reference  States : " + refSet);
        System.out.println("Candidate States : " + candidate);
        System.out.println("Is valid operation: " + refSet.removeAll(candidate));
        System.out.println("Remaining States : " + refSet);
    }
}

