import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        int[] a = createArray();

        while (!BinaryStdIn.isEmpty()) {
            int c = BinaryStdIn.readChar();
            char out = (char)replacForward(a, c);
            BinaryStdOut.write(out);
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    private static int[] createArray() {
        int ALPHABET_SIZE = 256;
        int[] a = new int[ALPHABET_SIZE];
        for (int i = 1; i < ALPHABET_SIZE; i++) {
            a[i] = i;
        }
        return a;
    }

    private static int replacForward(int[] a, int c) {
        int i = 0;
        int prev = a[0];
        while (prev != c) {
            i++;
            int current = a[i];
            a[i] = prev;
            prev = current;
        }
        a[0] = prev;

        return i;
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        int[] a = createArray();

        while (!BinaryStdIn.isEmpty()) {
            int c = BinaryStdIn.readChar();
            BinaryStdOut.write((char)a[c]);
            replacForward(a, a[c]);
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args.length == 0) {
            String encoded = encodeStr("ABRACADABRA!");
            String decoded = decodeStr(encoded);

            if (!decoded.isEmpty()) {
                StdOut.println("No args. User - or +");
            }
        } else {
            if (args[0].equals("+")) {
                decode();
            } else {
                encode();
            }
        }
    }

    private static String decodeStr(String s) {
        int[] a = createArray();
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            b.append((char)a[c]);
            replacForward(a, a[c]);
        }

        return b.toString();
    }

    private static String encodeStr(String s) {
        int[] a = createArray();
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            char out = (char)replacForward(a, c);
            b.append(out);
        }

        return b.toString();
    }
}
