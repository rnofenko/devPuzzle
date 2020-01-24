import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private static final int MIN_SIZE = 1;
    private static final double MAX_ENERGY = 1000;
    private int[][] grid;
    private int width;
    private int height;
    private double[][] energyTable;

    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        this.width = picture.width();
        this.height = picture.height();
        this.grid = copyPictureToArray(picture);
        this.energyTable = createEnergyTable();
    }

    public Picture picture() {
        return createPicture();
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException();
        }

        if (energyTable == null) {
            energyTable = createEnergyTable();
        }

        return energyTable[y][x];
    }

    private double calcEnergy(int x, int y) {
        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
            return MAX_ENERGY;
        }

        int leftRgb = grid[y][x - 1];
        int rightRgb = grid[y][x + 1];
        int rowEnergy = calcEnergyPart(leftRgb, rightRgb);

        int topRgb = grid[y - 1][x];
        int bottomRgb = grid[y + 1][x];
        int colEnergy = calcEnergyPart(topRgb, bottomRgb);

        return Math.sqrt(rowEnergy + colEnergy);
    }

    private int calcEnergyPart(int rgb1, int rgb2) {
        int r = ((rgb1 >> 16) & 0xFF) - ((rgb2 >> 16) & 0xFF);
        int g = ((rgb1 >> 8) & 0xFF) - ((rgb2 >> 8) & 0xFF);
        int b = (rgb1 & 0xFF) - (rgb2 & 0xFF);
        return r * r + g * g + b * b;
    }

    public int[] findHorizontalSeam() {
        int[][] edgeTo = createIntMatrix();
        double[][] distTo = createDoubleMatrix();

        if (energyTable == null) {
            energyTable = createEnergyTable();
        }

        for (int x = 1; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double v1 = y == 0 ? Double.POSITIVE_INFINITY : energyTable[y - 1][x - 1] + distTo[y - 1][x - 1];
                double v2 = energyTable[y][x - 1] + distTo[y][x - 1];
                double v3 = y == height - 1 ? Double.POSITIVE_INFINITY : energyTable[y + 1][x - 1] + distTo[y + 1][x - 1];

                double minV = Math.min(v1, Math.min(v2, v3));
                edgeTo[y][x] = minV == v1 ? y - 1 : minV == v2 ? y : y + 1;
                distTo[y][x] = minV + energyTable[y][x];
            }
        }

        int minIndex = 0;
        int lastWidthIndex = width - 1;
        for (int i = 1; i < height; i++) {
            if (distTo[i][lastWidthIndex] < distTo[minIndex][lastWidthIndex]) {
                minIndex = i;
            }
        }

        int[] result = new int[width];
        for (int i = width - 1; i >= 0; i--) {
            result[i] = minIndex;
            minIndex = edgeTo[minIndex][i];
        }

        return result;
    }

    public int[] findVerticalSeam() {
        int[][] edgeTo = createIntMatrix();
        double[][] distTo = createDoubleMatrix();

        if (energyTable == null) {
            energyTable = createEnergyTable();
        }

        for (int y = 1; y < height; y++) {
            double[] energyRow = energyTable[y - 1];
            double[] distRow = distTo[y - 1];

            for (int x = 0; x < width; x++) {
                double v1 = x == 0 ? Double.POSITIVE_INFINITY : energyRow[x - 1] + distRow[x - 1];
                double v2 = energyRow[x] + distRow[x];
                double v3 = x == width - 1 ? Double.POSITIVE_INFINITY : energyRow[x + 1] + distRow[x + 1];

                double minV = Math.min(v1, Math.min(v2, v3));
                edgeTo[y][x] = minV == v1 ? x - 1 : minV == v2 ? x : x + 1;
                distTo[y][x] = minV + energyTable[y][x];
            }
        }

        int minIndex = 0;
        double[] lastRow = distTo[height - 1];
        for (int i = 1; i < width; i++) {
            if (lastRow[i] < lastRow[minIndex]) {
                minIndex = i;
            }
        }

        int[] result = new int[height];
        for (int i = height - 1; i >= 0; i--) {
            result[i] = minIndex;
            minIndex = edgeTo[i][minIndex];
        }

        return result;
    }

    private int[][] createIntMatrix() {
        int[][] res = new int[height][];
        for (int i = 0; i < height; i++) {
            res[i] = new int[width];
        }
        return res;
    }

    private double[][] createDoubleMatrix() {
        double[][] res = new double[height][];
        for (int i = 0; i < height; i++) {
            res[i] = new double[width];
        }
        return res;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || height <= MIN_SIZE || width != seam.length) {
            throw new IllegalArgumentException();
        }

        height--;
        for (int x = 0; x < seam.length; x++) {
            int yy = seam[x];
            if (yy < 0 || yy > height || (x > 0 && Math.abs(seam[x - 1] - yy) > 1)) {
                throw new IllegalArgumentException();
            }
            for (int y = yy; y < height; y++) {
                grid[y][x] = grid[y + 1][x];
            }
        }

        energyTable = null;
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null || width <= MIN_SIZE || height != seam.length) {
            throw new IllegalArgumentException();
        }

        width--;
        for (int y = 0; y < seam.length; y++) {
            int x = seam[y];
            if (x < 0 || x > width || (y > 0 && Math.abs(seam[y - 1] - x) > 1)) {
                throw new IllegalArgumentException();
            }
            if (x < width) {
                int[] gridRow = grid[y];
                System.arraycopy(gridRow, x + 1, gridRow, x, width - x);
            }
        }

        energyTable = null;
    }

    private Picture createPicture() {
        Picture result = new Picture(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.setRGB(x, y, grid[y][x]);
            }
        }

        return result;
    }

    private int[][] copyPictureToArray(Picture pic) {
        int[][] result = new int[pic.height()][];
        for (int y = 0; y < pic.height(); y++) {
            int[] row = new int[pic.width()];
            result[y] = row;
            for (int x = 0; x < pic.width(); x++) {
                row[x] = pic.getRGB(x, y);
            }
        }

        return result;
    }

    private double[][] createEnergyTable() {
        double[][] res = new double[height][];
        for (int y = 0; y < height; y++) {
            double[] row = new double[width];
            res[y] = row;
            for (int x = 0; x < width; x++) {
                row[x] = calcEnergy(x, y);
            }
        }
        return res;
    }
}
