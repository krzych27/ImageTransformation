import java.awt.Color;
import java.awt.image.BufferedImage;

public class AutoThreshold{

    public AutoThreshold() {
    }

    public int[][] convertTo2DArray(BufferedImage image){
        int twoDimensions[][] = new int[image.getWidth()][image.getHeight()];
        for(int x=0; x<image.getWidth(); x++){
            for(int y=0; y<image.getHeight(); y++){
                int c = image.getRGB(x, y);
                twoDimensions[x][y] = c;
            }
        }
        return twoDimensions;
    }

    public int[] convertTo1D(int[][] input, int width, int height){
        int[] pixels=new int[width*height];
        int i = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[i++] = input[x][y];
            }
        }
        return pixels;
    }

    public void makeGray(BufferedImage img)
    {
        for (int x = 0; x < img.getWidth(); ++x)
            for (int y = 0; y < img.getHeight(); ++y)
            {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);

                // Normalize and gamma correct:
                double rr = Math.pow(r / 255.0, 2.2);
                double gg = Math.pow(g / 255.0, 2.2);
                double bb = Math.pow(b / 255.0, 2.2);

                // Calculate luminance:
                double lum = 0.2126 * rr + 0.7152 * gg + 0.0722 * bb;

                // Gamma compand and rescale to byte range:
                int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
                img.setRGB(x, y, gray);
            }
    }

    public int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

    public BufferedImage binarize(BufferedImage original, int threshold) {

        int red;
        int newPixel;

        BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {

                // Get pixels
                red = new Color(original.getRGB(i, j)).getRed();
                int alpha = new Color(original.getRGB(i, j)).getAlpha();
                if(red > threshold) {
                    newPixel = 255;
                }
                else {
                    newPixel = 0;
                }
                newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                binarized.setRGB(i, j, newPixel);

            }
        }

        return binarized;

    }

    int maxEntropy(int [] data ) {

        int threshold=-1;
        int ih, it;
        int first_bin;
        int last_bin;
        double tot_ent;  /* total entropy */
        double max_ent;  /* max entropy */
        double ent_back; /* entropy of the background pixels at a given threshold */
        double ent_obj;  /* entropy of the object pixels at a given threshold */
        double [] norm_histo = new double[256]; /* normalized histogram */
        double [] P1 = new double[256]; /* cumulative normalized histogram */
        double [] P2 = new double[256];

        double total =0;
        for (ih = 0; ih < 256; ih++ )
            total+=data[ih];

        for (ih = 0; ih < 256; ih++ )
            norm_histo[ih] = data[ih]/total;

        P1[0]=norm_histo[0];
        P2[0]=1.0-P1[0];
        for (ih = 1; ih < 256; ih++ ){
            P1[ih]= P1[ih-1] + norm_histo[ih];
            P2[ih]= 1.0 - P1[ih];
        }

        /* Determine the first non-zero bin */
        first_bin=0;
        for (ih = 0; ih < 256; ih++ ) {
            if ( !(Math.abs(P1[ih])<2.220446049250313E-16)) {
                first_bin = ih;
                break;
            }
        }

        /* Determine the last non-zero bin */
        last_bin=255;
        for (ih = 255; ih >= first_bin; ih-- ) {
            if ( !(Math.abs(P2[ih])<2.220446049250313E-16)) {
                last_bin = ih;
                break;
            }
        }

        // Calculate the total entropy each gray-level
        // and find the threshold that maximizes it
        max_ent = Double.MIN_VALUE;

        for ( it = first_bin; it <= last_bin; it++ ) {
            /* Entropy of the background pixels */
            ent_back = 0.0;
            for ( ih = 0; ih <= it; ih++ )  {
                if ( data[ih] !=0 ) {
                    ent_back -= ( norm_histo[ih] / P1[it] ) * Math.log ( norm_histo[ih] / P1[it] );
                }
            }

            /* Entropy of the object pixels */
            ent_obj = 0.0;
            for ( ih = it + 1; ih < 256; ih++ ){
                if (data[ih]!=0){
                    ent_obj -= ( norm_histo[ih] / P2[it] ) * Math.log ( norm_histo[ih] / P2[it] );
                }
            }

            /* Total entropy */
            tot_ent = ent_back + ent_obj;

            // IJ.log(""+max_ent+"  "+tot_ent);
            if ( max_ent < tot_ent ) {
                max_ent = tot_ent;
                threshold = it;
            }
        }
        return threshold;
    }
}
