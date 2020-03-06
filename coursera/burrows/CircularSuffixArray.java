/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {
    private final Suffix[] sortedSuffixes;

    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }

        Suffix[] suffixes = createArray(s);
        Arrays.sort(suffixes);
        sortedSuffixes = suffixes;
    }

    public int length() {
        return sortedSuffixes.length;
    }

    public int index(int i) {
        if (i < 0 || i >= sortedSuffixes.length) {
            throw new IllegalArgumentException();
        }
        return sortedSuffixes[i].index;
    }

    private static Suffix[] createArray(String text) {
        Suffix[] a = new Suffix[text.length()];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Suffix(text, i);
        }
        return a;
    }

    private static class Suffix implements Comparable<Suffix> {
        private final String text;
        private final int index;
        private final int len;

        public Suffix(String text, int index) {
            this.text = text;
            this.index = index;
            this.len = text.length();
        }

        public int compareTo(Suffix o) {
            for (int i = 0; i < len; i++) {
                int res = Character.compare(getChar(i), o.getChar(i));
                if (res != 0) {
                    return res;
                }
            }
            return 0;
        }

        private char getChar(int shift) {
            int i = (index + shift) % len;
            return text.charAt(i);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            CircularSuffixArray a = new CircularSuffixArray("ABRACADABRA!");
            StdOut.println(a.index(0));
            StdOut.println(a.index(1));
            StdOut.println(a.index(2));
            StdOut.println(a.index(3));
        }
    }
}
