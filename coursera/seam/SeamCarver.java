import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private static final int MIN_SIZE = 1;
    private static final int MAX_SQUARED_ENERGY = 1_000_000;
    private static final int OVER_SIZED_ENERGY = MAX_SQUARED_ENERGY + 1;
    private Picture cachedPicture;
    private int[] grid;
    private int width;
    private int height;
    private int[] energyTable;

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
        if (cachedPicture == null) {
            cachedPicture = createPicture();
        }
        return cachedPicture;
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

        int square = energyTable[x + width * y];
        return Math.sqrt(square);
    }

    private int calcEnergySquare(int x, int y) {
        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
            return MAX_SQUARED_ENERGY;
        }

        int shift = y * width + x;
        int leftRgb = grid[shift - 1];
        int rightRgb = grid[shift + 1];
        int rowEnergy = calcEnergyPart(leftRgb, rightRgb);

        int topRgb = grid[shift - width];
        int bottomRgb = grid[shift + width];
        int colEnergy = calcEnergyPart(topRgb, bottomRgb);

        return rowEnergy + colEnergy;
    }

    private int calcEnergyPart(int rgb1, int rgb2) {
        int r = ((rgb1 >> 16) & 0xFF) - ((rgb2 >> 16) & 0xFF);
        int g = ((rgb1 >> 8) & 0xFF) - ((rgb2 >> 8) & 0xFF);
        int b = (rgb1 & 0xFF) - (rgb2 & 0xFF);
        return r * r + g * g + b * b;
    }

    public int[] findHorizontalSeam() {
        int verticesCount = width * height;
        int[] edgeTo = new int[verticesCount];
        int[] distTo = new int[verticesCount];

        for (int x = 1; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int prevIndex = y * width + x - 1;
                int v1 = y == 0 ? OVER_SIZED_ENERGY : energyTable[prevIndex - width];
                int v2 = energyTable[prevIndex];
                int v3 = y == height - 1 ? OVER_SIZED_ENERGY : energyTable[prevIndex + width];

                int minV = Math.min(v1, Math.min(v2, v3));
                int index = prevIndex + 1;
                if (minV == v1) {
                    edgeTo[index] = prevIndex - width;
                } else if (minV == v2) {
                    edgeTo[index] = prevIndex;
                } else {
                    edgeTo[index] = prevIndex + width;
                }
                distTo[index] = minV + distTo[edgeTo[index]];
            }
        }

        int minIndex = width - 1;
        for (int i = minIndex + width; i < verticesCount; i += width) {
            if (distTo[i] < distTo[minIndex]) {
                minIndex = i;
            }
        }

        int[] result = new int[width];
        for (int i = width - 1; i >= 0; i--) {
            result[i] = minIndex / width;
            minIndex = edgeTo[minIndex];
        }

        return result;
    }

    public int[] findVerticalSeam() {
        int verticesCount = width * height;
        int[] edgeTo = new int[verticesCount];
        int[] distTo = new int[verticesCount];

        for (int y = 1; y < height; y++) {
            int prevRow = width * y - width;
            for (int x = 0; x < width; x++) {
                int prevIndex = prevRow + x;
                int v1 = x == 0 ? Integer.MAX_VALUE : energyTable[prevIndex - 1] + distTo[prevIndex - 1];
                int v2 = energyTable[prevIndex] + distTo[prevIndex];
                int v3 = x == width - 1 ? Integer.MAX_VALUE : energyTable[prevIndex + 1] + distTo[prevIndex + 1];

                int minV = Math.min(v1, Math.min(v2, v3));
                int index = width * y + x;
                if (minV == v1) {
                    edgeTo[index] = prevIndex - 1;
                } else if (minV == v2) {
                    edgeTo[index] = prevIndex;
                } else {
                    edgeTo[index] = prevIndex + 1;
                }
                distTo[index] = minV + energyTable[index];
            }
        }

        int minIndex = verticesCount - width;
        for (int i = minIndex + 1; i < verticesCount; i++) {
            if (distTo[i] < distTo[minIndex]) {
                minIndex = i;
            }
        }

        int[] result = new int[height];
        for (int i = height - 1; i >= 0; i--) {
            result[i] = minIndex % width;
            minIndex = edgeTo[minIndex];
        }

        return result;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || height <= MIN_SIZE || width != seam.length) {
            throw new IllegalArgumentException();
        }

        int[] seamCoords = new int[seam.length];
        for (int i = 0; i < seam.length; i++) {
            seamCoords[i] = seam[i] * width + i;
        }
        grid = deleteSeam(seamCoords);
        height--;

        energyTable = createEnergyTable();
        cachedPicture = null;
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null || width <= MIN_SIZE || height != seam.length) {
            throw new IllegalArgumentException();
        }

        int[] seamCoords = new int[seam.length];
        for (int i = 0; i < seam.length; i++) {
            seamCoords[i] = i * width + seam[i];
        }
        grid = deleteSeam(seamCoords);
        width--;

        energyTable = createEnergyTable();
        cachedPicture = null;
    }

    private int[] deleteSeam(int[] seam) {
        int[] newGrid = new int[grid.length - seam.length];
        int targetInd = 0;
        int startInd = 0;
        for (int i: seam) {
            int endInd = i - 1;
            int size = endInd - startInd + 1;
            if (size > 0) {
                System.arraycopy(grid, startInd, newGrid, targetInd, size);
                targetInd += size;
            }
            startInd = i + 1;
        }

        return newGrid;
    }

    private Picture createPicture() {
        Picture result = new Picture(width, height);
        for (int y = 0; y < height; y++) {
            int shift = y * width;
            for (int x = 0; x < width; x++) {
                result.setRGB(x, y, grid[shift + x]);
            }
        }

        return result;
    }

    private int[] copyPictureToArray(Picture pic) {
        int[] result = new int[pic.width() * pic.height()];
        for (int y = 0; y < pic.height(); y++) {
            int shift = y * pic.width();
            for (int x = 0; x < pic.width(); x++) {
                result[shift + x] = pic.getRGB(x, y);
            }
        }

        return result;
    }

    private int[] createEnergyTable() {
        int[] res = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                res[x + width * y] = calcEnergySquare(x, y);
            }
        }
        return res;
    }
}
