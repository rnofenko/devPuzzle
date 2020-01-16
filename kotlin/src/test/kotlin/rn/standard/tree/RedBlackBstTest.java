package rn.standard.tree;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.Stopwatch;

import java.util.Arrays;
import java.util.List;

public class RedBlackBstTest {

    @Test
    public void test1() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        RedBlackBst<Integer, Long> tree = new RedBlackBst<>();

        for (Integer item : items) {
            tree.add(item,  Long.valueOf(item) * 10);
        }

        for (Integer item : items) {
            Long res = tree.get(item);
            Long expected = Long.valueOf(item) * 10;
            Assert.assertEquals(expected, res);
        }
    }

    @Test
    public void performance1() {
        RedBlackBst<Integer, Long> tree = new RedBlackBst<>();

        for (Integer count : Arrays.asList(10000, 20000, 40000)) {
            performance(count);
        }
    }

    public void performance(int count) {
        Stopwatch w = Stopwatch.Companion.start();
        w.show("For " + count + " elements");

        RedBlackBst<Integer, Long> tree = new RedBlackBst<>();
        for (int i = 0; i < count; i++) {
            tree.add(i, (long) i);
        }
        w.show("INSERTION");

        for (int i = 0; i < count; i++) {
            boolean res = tree.contains(i);
            Assert.assertTrue("Item " + i, res);
        }
        w.show("FIND ONE BY ONE");

        /* before
For 10000 elements time=0.0
INSERTION time=0.22
FIND ONE BY ONE time=0.43
For 20000 elements time=0.0
INSERTION time=0.81
FIND ONE BY ONE time=1.61
For 40000 elements time=0.0
INSERTION time=3.21
FIND ONE BY ONE time=6.49
         */

        /* after
        For 10000 elements time=0.0
INSERTION time=0.01
FIND ONE BY ONE time=0.02
For 20000 elements time=0.0
INSERTION time=0.01
FIND ONE BY ONE time=0.02
For 40000 elements time=0.0
INSERTION time=0.01
FIND ONE BY ONE time=0.02
         */
    }
}
