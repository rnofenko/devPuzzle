package rn.puzzle.constructive.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LoggerRateLimiterTest {
    private final Map<String, Integer> timeMap = new HashMap<>();
    private final LinkedList<String> queue = new LinkedList<>();

    public boolean shouldPrintMessage(int timestamp, String message) {
        this._removeOld(timestamp);

        Integer time = this.timeMap.get(message);
        if (time != null) {
            return false;
        }

        this.timeMap.put(message, timestamp);
        this.queue.addLast(message);

        return true;
    }

    private void _removeOld(int now) {
        while (!queue.isEmpty()) {
            String message = queue.peek();
            Integer time = timeMap.get(message);

            if (now - time >= 10) {
                queue.poll();
                timeMap.remove(message);
            } else {
                break;
            }
        }
    }

    @Test
    public void test1() {
        Assert.assertTrue(shouldPrintMessage(1, "a"));
        Assert.assertTrue(shouldPrintMessage(1, "b"));
        Assert.assertFalse(shouldPrintMessage(1, "a"));
        Assert.assertFalse(shouldPrintMessage(9, "a"));
        Assert.assertFalse(shouldPrintMessage(10, "a"));
        Assert.assertTrue(shouldPrintMessage(11, "a"));
        Assert.assertFalse(shouldPrintMessage(11, "a"));
    }
}
