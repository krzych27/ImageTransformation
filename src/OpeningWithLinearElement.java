import java.awt.image.BufferedImage;

public class OpeningWithLinearElement extends KirschFilter {

    public static BufferedImage dilate(int[][] data, int radius) {
        int width = data[0].length;
        int height = data.length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int y = radius; y < height - radius; y++) {
            for (int x = radius; x < width - radius; x++) {
                int rgb = (255 << 16) | (255 << 8) | 255;

                for (int i = -radius; i < radius; i++) {
                    for (int j = -radius; j < radius; j++) {
                        if (i * i + j * j <= radius * radius) {
                            int pixel = data[j + y][i + x];
                            int value = (pixel & 0xff);
                            int currentRGB = (value << 16) | (value << 8) | value;

                            if (value < (rgb & 0xff))
                                rgb = currentRGB;
                        }
                    }
                }
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }

    public static BufferedImage erode(int[][] data, int radius) {
        int width = data[0].length;
        int height = data.length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int y = radius; y < height - radius; y++)
        {
            for (int x = radius; x < width - radius; x++)
            {
                int rgb = (0 << 16) | (0 << 8) | 0;

                for (int i = -radius; i < radius; i++)
                {
                    for (int j = -radius; j < radius; j++)
                    {
                        if (i * i + j * j <= radius * radius)
                        {
                            int pixel = data[j + y][i + x];
                            int value = (pixel & 0xff);
                            int currentRGB = (value << 16) | (value << 8) | value;

                            if (value > (rgb & 0xff))
                                rgb = currentRGB;
                        }
                    }

                }
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }

    public BufferedImage opening(int[][] data, int radius) {
        if(radius <=0) {
            return null;
        }
        //KirschFilter k = new KirschFilter();
        int[][] tmpData = convertTo2DArray(erode(data, radius));
        tmpData = convertTo2DArray(dilate(tmpData, radius));
        BufferedImage img = new BufferedImage(tmpData[0].length - 2*radius, tmpData.length - 2*radius, BufferedImage.TYPE_3BYTE_BGR);
        int rgb;

        for (int y = 0; y < tmpData.length - 2*radius; y++) {
            for (int x = 0; x < tmpData[y].length - 2*radius; x++) {
                int pixel;

                if (y < 2*radius || x < 2*radius)
                    pixel = data[y][x];
                else
                    pixel = tmpData[y][x];

                int value = (pixel & 0xff);
                rgb = (value << 16) | (value << 8) | value;
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }

}
