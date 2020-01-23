import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

public class SeamCarverTester {

    @Test
    public void print_6x5() {
        print("data/6x5.png");
    }

    @Test
    public void print_3x4() {
        print("data/3x4.png");
    }

    @Test
    public void findVertical_3x4() {
        printVertical("data/3x4.png");
    }

    @Test
    public void findVertical_6x5() {
        printVertical("data/6x5.png");
    }

    @Test
    public void findVerticalAndRemove_6x5() {
        removeVerticalAndHorizontal("data/6x5.png");
    }

    @Test
    public void findVerticalAndRemove_3x4() {
        removeVerticalAndHorizontal("data/3x4.png");
    }

    @Test
    public void findHorizontal_3x4() {
        printHorizontal("data/3x4.png");
    }

    @Test
    public void findHorizontal_6x5() {
        printHorizontal("data/6x5.png");
    }

    public void removeVerticalAndHorizontal(String filename) {
        SeamCarver carver = createCarver(filename);

        int[] seam = carver.findVerticalSeam();
        printSeam(carver, seam, false);
        carver.removeVerticalSeam(seam);
        printEnergy(carver);

        seam = carver.findHorizontalSeam();
        printSeam(carver, seam, true);
        carver.removeHorizontalSeam(seam);

        printEnergy(carver);
    }

    private void print(String filename) {
        Picture picture = new Picture(filename);
        StdOut.printf("image is %d pixels wide by %d pixels high.\n", picture.width(), picture.height());

        SeamCarver sc = new SeamCarver(picture);

        StdOut.printf("Printing energy calculated for each pixel.\n");
        printEnergy(sc);
    }

    private void printEnergy(SeamCarver sc) {
        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++)
                StdOut.printf("%9.0f ", sc.energy(col, row));
            StdOut.println();
        }
    }

    public static void printSeam(SeamCarver carver, int[] seam, boolean direction) {
        double totalSeamEnergy = 0.0;

        for (int row = 0; row < carver.height(); row++) {
            for (int col = 0; col < carver.width(); col++) {
                double energy = carver.energy(col, row);
                String marker = " ";
                if ((direction == HORIZONTAL && row == seam[col]) || (direction == VERTICAL && col == seam[row])) {
                    marker = "*";
                    totalSeamEnergy += energy;
                }
                StdOut.printf("%7.2f%s ", energy, marker);
            }
            StdOut.println();
        }
        StdOut.printf("Total energy = %f\n", totalSeamEnergy);
        StdOut.println();
        StdOut.println();
    }

    public static void printVertical(String filename) {
        SeamCarver carver = createCarver(filename);

        StdOut.printf("Vertical seam: { ");
        int[] verticalSeam = carver.findVerticalSeam();
        for (int x : verticalSeam)
            StdOut.print(x + " ");
        StdOut.println("}");
        printSeam(carver, verticalSeam, VERTICAL);
    }

    public static void printHorizontal(String filename) {
        Picture picture = new Picture(filename);
        SeamCarver carver = new SeamCarver(picture);

        StdOut.printf("Horizontal seam: { ");
        int[] horizontalSeam = carver.findHorizontalSeam();
        for (int y : horizontalSeam)
            StdOut.print(y + " ");
        StdOut.println("}");
        SeamCarverTester.printSeam(carver, horizontalSeam, HORIZONTAL);
    }

    private static SeamCarver createCarver(String filename) {
        Picture picture = new Picture(filename);
        return new SeamCarver(picture);
    }

    private static final boolean HORIZONTAL   = true;
    private static final boolean VERTICAL     = false;
}
