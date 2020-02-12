package rn.puzzle.constructive.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FunctionTimeTest {

    @Test
    public void test1() {
        int[] res = exclusiveTime(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6"));
        Assert.assertArrayEquals(new int[] {3, 4}, res);
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int startTime = 0;

        for (String log : logs) {
            String[] parts = log.split(":");
            int time = Integer.parseInt(parts[2]);

            if (parts[1].equals("end")) {
                res[stack.peek()] += time - startTime + 1;
                startTime = time + 1;
                stack.pop();
            } else {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += time - startTime;
                }
                startTime = time;

                int funcId = Integer.parseInt(parts[0]);
                stack.add(funcId);
            }
        }

        return res;
    }
}
