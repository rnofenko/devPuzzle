import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Tester {

    @Test
    public void test4() {
        test("teams4");
    }

    @Test
    public void test5() {
        test("teams5");
    }

    @Test
    public void test12() {
        test("teams12");
    }

    @Test
    public void test12all() {
        test("teams12-allgames");
    }

    private void test(String filename) {
        BaseballElimination division = new BaseballElimination("data/" + filename + ".txt");
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}