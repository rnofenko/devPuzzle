/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(c);
        }
        BinaryStdIn.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {

    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args.length == 0) {
            StdOut.println("No args. User - or +");
        } else {
            if (args[0].equals("+")) {
                decode();
            } else {
                encode();
            }
        }
    }
}
