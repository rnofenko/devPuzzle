/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

public class BurrowsWheeler {
    public static void transform() {
        String text = BinaryStdIn.readString();
        int len = text.length();
        CircularSuffixArray suffixArray = new CircularSuffixArray(text);

        //index of sorted first
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

    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args.length == 0) {
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
