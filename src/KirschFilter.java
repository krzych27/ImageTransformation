import java.awt.color.ColorSpace;
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
                    {-3, 5, 5}},};

    private static int[][] appalyKirschMask(BufferedImage image, int mask[][]) {
        int height = image.getHeight();
        int width = image.getWidth();

        int[][] out = new int[height - 2][width - 2];

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                int red = 0;
                int blue = 0;
                int green = 0;

                for (int ki = -1; ki < 2; ki++) {
                    for (int kj = -1; kj < 2; kj++) {
                        red += (image.getRGB(j + kj,i + ki) >> 16 & 0xff) * mask[ki + 1][kj + 1];
                        green += (image.getRGB(j + kj,i + ki) >> 8 & 0xff) * mask[ki + 1][kj + 1];
                        blue += (image.getRGB(j + kj,i + ki) & 0xff) * mask[ki + 1][kj + 1];
                    }
                }
                out[i - 1][j - 1] = convertFromRGBtoInt(red, green, blue);
            }
        }

        return out;
    }

    public int[][] convertTo2DArray(BufferedImage image) {
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        int width = image.getWidth();
        int height = image.getHeight();

        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];

        if (isMonochrome(image)) {
            final int pixelLength = 1;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int grey = ((int) pixels[pixel] & 0xff);
                result[row][col] = grey;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
            return result;
        }

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

    public static boolean isMonochrome(BufferedImage image) {
        final int type = image.getColorModel().getColorSpace().getType();
        final boolean isMonochrome = (type == ColorSpace.TYPE_GRAY || type == ColorSpace.CS_GRAY);

        return isMonochrome;
    }

    public static int convertFromRGBtoInt(int R, int G, int B) {
        int rgb = R;
        rgb = (rgb << 8) + G;
        rgb = (rgb << 8) + B;

        return rgb;
    }

    public static BufferedImage kirschFilter(BufferedImage image) {

        int[][][] res = new int[8][image.getWidth()][image.getHeight()];
        for(int i=0; i<8; i++)
            res[i] = appalyKirschMask(image, kirschMask[i]);

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
