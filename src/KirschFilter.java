import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class KirschFilter {

    private static int[][][] kirschMask = {
            {{-3, -3, 5},
                    {-3, 0, 5},
                    {-3, -3, 5}},
            {{-3, 5, 5},
                    {-3, 0, 5},
                    {-3, -3, -3}},
            {{5, 5, 5},
                    {-3, 0, -3},
                    {-3, -3, -3}},
            {{5, 5, -3},
                    {5, 0, -3},
                    {-3, -3, -3}},
            {{5, -3, -3},
                    {5, 0, -3},
                    {5, -3, -3}},
            {{-3, -3, -3},
                    {5, 0, -3},
                    {5, 5, -3}},
            {{-3, -3, -3},
                    {-3, 0, -3},
                    {5, 5, 5}},
            {{-3, -3, -3},
                    {-3, 0, 5},
                    {-3, 5, 5}},
    };

    private static int[][] applyKirschMask(int[][] data, int mask[][]) {
        int height = data.length;
        int width = data[0].length;

        int[][] out = new int[height - 2][width - 2];

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                int sum = 0;

                for (int ki = -1; ki < 2; ki++) {
                    for (int kj = -1; kj < 2; kj++) {
                        sum += (data[i + ki][j + kj] * mask[ki + 1][kj + 1]);
                    }
                }

                out[i - 1][j - 1] = sum;
            }
        }

        return out;
    }

    private static int[][] convertTo2DArray(BufferedImage image) {
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        int width = image.getWidth();
        int height = image.getHeight();

        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];

        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
        return result;
    }

    public BufferedImage kirschFilter(BufferedImage image) {

        int[][] data = convertTo2DArray(image);
        int[][][] res = new int[8][image.getWidth()][image.getHeight()];
        for(int i=0; i<8; i++)
            res[i] = applyKirschMask(data, kirschMask[i]);

        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int i =0; i<8; i++) {
            for (int x = 0; x < image.getWidth() - 2; x++)
                for (int y = 0; y < image.getHeight() - 2; y++) {
                    if (result.getRGB(x, y) > res[i][y][x])
                        result.setRGB(x, y, res[i][y][x]);
                }
        }
        return result;
    }
}
