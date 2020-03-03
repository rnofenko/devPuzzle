package rn.standard.sorting;

public class RadixMsdStringSorter {
    private static final char FIRST_CHAR = 'a';
    private static final char LAST_CHAR = 'z';
    private static final int R = LAST_CHAR - FIRST_CHAR + 2;

    public void sort(String[] a) {
        String[] aux = new String[a.length];
        sort(a, aux, 0, a.length - 1, 0);
    }

    private void sort(String[] a, String[] aux, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }

        int[] count = new int[R + 1];
        for (int i = lo; i <= hi; i++) {
            count[getChar(a, i, d) + 1]++;
        }
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) {
            int charCode = getChar(a, i, d);
            aux[count[charCode]++] = a[i];
        }
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        for (int r = 0; r < R; r++) {
            sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    private int getChar(String[] strs, int strIdx, int charIdx) {
        String s = strs[strIdx];
        if (s.length() <= charIdx) {
            return 0;
        }
        return s.charAt(charIdx) - FIRST_CHAR + 1;
    }
}
