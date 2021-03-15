package rn.puzzle.structure.heap.median;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MedianUpdatesTest {
    public static void consoleMix() {
        Scanner scan = new Scanner(System.in);
        int queriesCount = Integer.parseInt(scan.nextLine());
        String[] input = new String[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            input[i] = scan.nextLine();
        }
        String[] output = runningMedian(input);
        StringBuilder builder = new StringBuilder();
        for (String s : output) {
            builder.append(s).append("\n");
        }
        System.out.println(builder.toString());
    }

    static String[] runningMedian(String[] input) {
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>(Integer::compareTo);
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            String command = input[i];
            int n = Integer.parseInt(command.substring(2));
            if (command.charAt(0) == 'a') {
                PriorityQueue<Integer> heap = !right.isEmpty() && n >= right.peek() ? right : left;
                heap.add(n);
            } else {
                PriorityQueue<Integer> heap = !left.isEmpty() && left.peek() >= n ? left : right;
                if (!heap.remove(n)) {
                    result[i] = "Wrong!";
                    continue;
                }
            }

            while (left.size() - right.size() > 1) {
                right.add(left.poll());
            }
            while (right.size() - left.size() > 1) {
                left.add(right.poll());
            }
            result[i] = getMedian(left, right);
        }
        return result;
    }

    static String getMedian(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.isEmpty() && right.isEmpty()) {
            return "Wrong!";
        }
        if (left.size() > right.size()) {
            return left.peek() + "";
        }
        if (right.size() > left.size()) {
            return right.peek() + "";
        }
        long sum = ((long) left.peek()) + right.peek();
        if (sum == -1) {
            return "-0.5";
        }
        long res = sum / 2;
        boolean isRest = res * 2 != sum;
        if (isRest) {
            return res + ".5";
        }
        return res + "";
    }

    @Test
    public void test1() {
        String[] result = runningMedian(new String[] {"r 1","a 1","a 2","a 1","r 1","r 2","r 1"});
        Assert.assertArrayEquals(new String[] {"Wrong!","1","1.5","1","1.5","1","Wrong!"}, result);
    }

    @Test
    public void test2() {
        String[] result = runningMedian(new String[] {"a -2147483648", "a -2147483648", "a -2147483647","r -2147483648","a 2147483647","r -2147483648"});
        Assert.assertArrayEquals(new String[] {"-2147483648","-2147483648","-2147483648","-2147483647.5","-2147483647","0"}, result);
    }

    @Test
    public void test3() {
        String[] result = runningMedian(new String[] {"a -2147483648","a -2147483648","a -2147483647","r -2147483648","a 2147483647",
                "r -2147483648","a 10","a 10","a 10","r 10","r 10","r 10","r 100","r 100","r 100","r -2147483648","r 2147483647",
                "r 10","a 1","a -1","a 1", "a -1","r 1","r -1","r -1","r -1","r -1","r 1","r 1","r 0","a 0","a 1",
                "a 2147483647","a 2","r 1","a 2147483646","r 2","a 2147483640","a 10","r 2","r 2","r 2","r 1","r 1","r 1",
                "a 2147483640","a 2147483640","a -2147483648","a -2147483640","r 2147483640"});
        Assert.assertArrayEquals(new String[] {"-2147483648","-2147483648","-2147483648","-2147483647.5","-2147483647","0",
                "10","10","10","10","10","0","Wrong!","Wrong!","Wrong!","Wrong!","-2147483647","Wrong!","-1073741823","-1",
                "0","-1","-1","-1","-1073741823","Wrong!","Wrong!","-2147483647","Wrong!","Wrong!","-1073741823.5","0","0.5",
                "1","1","2","1073741823","2147483640","1073741825","Wrong!","Wrong!","Wrong!","Wrong!","Wrong!","Wrong!",
                "2147483640","2147483640","2147483640","1073741825","10"}, result);
    }

    @Test
    public void test4() {
        String[] result = runningMedian(new String[] {"a -2147483648","a -2147483648","a -2147483647","r -2147483648","a 2147483647",
                "r -2147483648","a 10","a 10","a 10","r 10","r 10","r 10","r 100"});
        Assert.assertArrayEquals(new String[] {"-2147483648","-2147483648","-2147483648","-2147483647.5","-2147483647","0",
                "10","10","10","10","10","0","Wrong!"}, result);
    }

    @Test
    public void test5() {
        String[] result = runningMedian(new String[] {"a 0","a -1"});
        Assert.assertArrayEquals(new String[] {"0","-0.5"}, result);
    }
}
