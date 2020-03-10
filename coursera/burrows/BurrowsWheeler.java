/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class BurrowsWheeler {
    public static void transform() {
        String text = BinaryStdIn.readString();
        int len = text.length();
        CircularSuffixArray suffixArray = new CircularSuffixArray(text);

        for (int i = 0; i < len; i++) {
            if (suffixArray.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < len; i++) {
            int idx = suffixArray.index(i);
            char c = text.charAt((idx + len - 1) % len);
            BinaryStdOut.write(c);
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    public static void inverseTransform() {
        int buildIndex = BinaryStdIn.readInt();
        String codedStr = BinaryStdIn.readString();
        int len = codedStr.length();

        List<List<Integer>> reverseMap = buildReverseMap(codedStr);
        List<int[]> sortedObj = createSortedArrayFromReverseMap(reverseMap, len);

        int[] sortedChars = sortedObj.get(0);
        int[] nextIndex = sortedObj.get(1);

        for (int i = 0; i < len; i++) {
            BinaryStdOut.write((char) sortedChars[buildIndex]);
            buildIndex = nextIndex[buildIndex];
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    private static List<int[]> createSortedArrayFromReverseMap(List<List<Integer>> reverseMap, int size) {
        int[] sortedChars = new int[size];
        int[] nextIndex = new int[size];

        int i = 0;
        for (int c = 0; c < reverseMap.size(); c++) {
            List<Integer> indexes = reverseMap.get(c);
            for (int index : indexes) {
                sortedChars[i] = c;
                nextIndex[i] = index;
                i++;
            }
        }

        List<int[]> result = new ArrayList<>();
        result.add(sortedChars);
        result.add(nextIndex);

        return result;
    }

    private static List<List<Integer>> buildReverseMap(String s) {
        final int R = 256;
        List<List<Integer>> charsMap = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            charsMap.add(new ArrayList<>());
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            List<Integer> indexes = charsMap.get(c);
            indexes.add(i);
        }

        return charsMap;
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args.length == 0) {
            List<List<Integer>> reverseMap = buildReverseMap("ARD!RCAAAABB");
            createSortedArrayFromReverseMap(reverseMap, 12);
            StdOut.println("No args. Use - or +");
        } else {
            if (args[0].equals("+")) {
                inverseTransform();
            } else {
                transform();
            }
        }
    }
}
