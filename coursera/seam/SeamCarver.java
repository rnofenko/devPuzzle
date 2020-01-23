import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private static final int MIN_SIZE = 1;
    private static final int MAX_SQUARED_ENERGY = 1_000_000;
    private static final int OVER_SIZED_ENERGY = MAX_SQUARED_ENERGY + 1;
    private final Picture picture;
    private int width;
    private int height;
    private int[] energyTable;

    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        this.picture = clonePicture(picture);
        this.width = picture.width();
        this.height = picture.height();
        this.energyTable = createEnergyTable(picture);
    }

    public Picture picture() {
        return picture;
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

    private int calcEnergySquare(int x, int y, Picture pic) {
        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
            return MAX_SQUARED_ENERGY;
        }

        int leftRgb = pic.getRGB(x - 1, y);
        int rightRgb = pic.getRGB(x + 1, y);
        int rowEnergy = calcEnergyPart(leftRgb, rightRgb);

        int topRgb = pic.getRGB(x, y - 1);
        int bottomRgb = pic.getRGB(x, y + 1);
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
                int v1 = x == 0 ? OVER_SIZED_ENERGY : energyTable[prevIndex - 1];
                int v2 = energyTable[prevIndex];
                int v3 = x == width - 1 ? OVER_SIZED_ENERGY : energyTable[prevIndex + 1];

                int minV = Math.min(v1, Math.min(v2, v3));
                int index = width * y + x;
                if (minV == v1) {
                    edgeTo[index] = prevIndex - 1;
                } else if (minV == v2) {
                    edgeTo[index] = prevIndex;
                } else {
                    edgeTo[index] = prevIndex + 1;
                }
                distTo[index] = minV + distTo[edgeTo[index]];
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
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null || width <= MIN_SIZE || height != seam.length) {
            throw new IllegalArgumentException();
        }
    }

    private Picture clonePicture(Picture pic) {
        Picture result = new Picture(pic.width(), pic.height());
        for (int y = 0; y < pic.height(); y++) {
            for (int x = 0; x < pic.width(); x++) {
                result.setRGB(x, y, pic.getRGB(x, y));
            }
        }

        return result;
    }

    private int[] createEnergyTable(Picture pic) {
        int[] res = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                res[x + width * y] = calcEnergySquare(x, y, pic);
            }
        }
        return res;
    }
}
