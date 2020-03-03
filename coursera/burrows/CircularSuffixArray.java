/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class CircularSuffixArray {
    private final int[] sortedSuffixes;

    public CircularSuffixArray(String s) {
        int[] suffixes = createArray(s.length());
        sortSuffixes(suffixes, s);
        sortedSuffixes = suffixes;
    }

    public int length() {
        return 0;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        return sortedSuffixes[i];
    }

    private static void sortSuffixes(int[] suffixes, String s) {
        sortMsd(suffixes, s);
    }

    private static void sortMsd(int[] suffixes, String s) {
        
    }

    private static int[] createArray(int n) {
        int[] a = new int[n];
        for (int i = 1; i < n; i++) {
            a[i] = i;
        }
        return a;
    }
}
