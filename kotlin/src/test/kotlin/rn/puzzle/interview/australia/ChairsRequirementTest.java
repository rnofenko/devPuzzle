package rn.puzzle.interview.australia;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChairsRequirementTest {

    @Test
    public void test1() {
        List<Integer> result = minChairs(Arrays.asList("CCRURC"));
        Assert.assertEquals(Integer.valueOf(2), result.get(0));
    }

    private static final char GO_TO_WORK = 'C';//new employee in workroom
    private static final char MEETING = 'R';//an employee goes to meeting room
    private static final char BACK_TO_WORK = 'U';
    private static final char GO_HOME = 'L';

    public List<Integer> minChairs(List<String> simulations) {
        return simulations.stream().map(s -> calcMinChairs(s)).collect(Collectors.toList());
    }

    private int calcMinChairs(String simulation) {
        int usedChairs = 0;
        int freeChairs = 0;

        for (int i = 0; i < simulation.length(); i++) {
            char c = simulation.charAt(i);

            switch (c) {
                case BACK_TO_WORK:
                case GO_TO_WORK:
                    if (freeChairs > 0) {
                        freeChairs--;
                    }
                    usedChairs++;
                    break;
                case MEETING:
                case GO_HOME:
                    freeChairs++;
                    usedChairs--;
                    break;
                default:
                    throw new RuntimeException("Invalid char=" + c);
            }
        }

        return usedChairs + freeChairs;
    }
}
