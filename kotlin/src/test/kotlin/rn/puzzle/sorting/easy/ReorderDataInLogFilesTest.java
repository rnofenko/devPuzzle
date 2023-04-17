package rn.puzzle.sorting.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/reorder-data-in-log-files/
public class ReorderDataInLogFilesTest {

    @Test
    public void test1() {
        String[] res = reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"});
        Assert.assertArrayEquals(new String[] {"let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"}, res);
    }

    public String[] reorderLogFiles(String[] logs) {
        List<String> digitLogs = new ArrayList<>();
        List<LogItem> letterLogs = new ArrayList<>();
        for (String log : logs) {
            LogItem item = new LogItem(log);
            if (item.isDigit()) {
                digitLogs.add(log);
            } else {
                letterLogs.add(item);
            }
        }
        letterLogs.sort(LogItem::compareTo);
        String[] result = new String[logs.length];
        for (int i = 0; i < letterLogs.size(); i++) {
            result[i] = letterLogs.get(i).toString();
        }
        for (int i = 0; i < digitLogs.size(); i++) {
            result[letterLogs.size() + i] = digitLogs.get(i);
        }

        return result;
    }

    private static class LogItem implements Comparable<LogItem> {
        private final String _input;
        private final String _id;
        private final String _body;

        LogItem(String input) {
            this._input = input;
            String[] parts = input.split(" ");
            this._id = parts[0];
            this._body = input.substring(parts[0].length() + 1);
        }

        public boolean isDigit() {
            for(int i = 0; i < this._body.length(); i++) {
                char c = this._body.charAt(i);
                if (c == ' ') continue;
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int compareTo(LogItem o) {
            int res = this._body.compareTo(o._body);
            if (res == 0) {
                return this._id.compareTo(o._id);
            }
            return res;
        }

        @Override
        public String toString() {
            return _input;
        }
    }
}
