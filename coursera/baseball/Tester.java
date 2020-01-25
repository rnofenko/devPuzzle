import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

public class Tester {

    @Test
    public void test1() {

    }

    @Test
    public void test2() {
        BaseballElimination division = new BaseballElimination("data/teams4.txt");
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