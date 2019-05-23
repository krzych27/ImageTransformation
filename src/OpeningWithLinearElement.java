import java.awt.*;
import java.awt.image.BufferedImage;

public class OpeningWithLinearElement extends AutoThreshold {

    //public Color[][] structElem;
    /*
    public BufferedImage dilation(BufferedImage img,int shapeSize, int[][]mask){
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage dilationImage = new BufferedImage(img.getWidth(),img.getHeight(), img.getType());

        int sSize = 2 * shapeSize + 1;

        int filterWidth = width - sSize;
        int filterHeight = height - sSize;
        int lowerSide = height - shapeSize;
        int rightSide = width - shapeSize;

        int dilation = 0;

        //center
        for (int x = 0; x <= filterWidth; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                dilation = max(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x+shapeSize, y+shapeSize, newColor.getRGB());
            }
        }

        //leftborder
        for (int x = 0; x < shapeSize; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                dilation = max(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y + shapeSize, newColor.getRGB());
            }
        }

        //leftlowerside
        for (int x = 0 ; x <shapeSize;x++){
            for (int y = lowerSide ; y < height;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //leftupperside
        dilation = max(mask,sSize);
        for (int x = 0 ; x <shapeSize ; x++){
            for (int y = 0 ; y < shapeSize ; y++){
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //rightborder
        for (int x = rightSide; x < width; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                dilation = max(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y + shapeSize, newColor.getRGB());
            }
        }

        //rightlowerside
        for (int x = rightSide ; x <width;x++){
            for (int y = lowerSide ; y < height;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //lowerborder
        for (int y = lowerSide - 1; y < height; y++) {
            for (int x = 0; x <= filterWidth; x++) {
                dilation = max(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x + shapeSize, y, newColor.getRGB());
            }
        }

        //upperborder
        for (int y = 0; y < shapeSize; y++) {
            for (int x = 0; x <= filterWidth; x++) {
                dilation = max(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x + shapeSize, y, newColor.getRGB());
            }
        }

        for (int x = rightSide ; x <width;x++){
            for (int y = 0 ; y < shapeSize;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return dilationImage;
    }

    public int max(int[][] val,int sSize) {

        int[] R = new int[sSize];

        int count = 0;
        for(int i = 0; i < val.length ; i++){
            for(int j = 0 ; j < val[i].length ; j++){
                if(val[i][j] == 0){
                    continue;
                }
                R[count] =  val[i][j];
                count++;
            }
        }

        int max = R[0];
        int end = R.length;
        int v = 0;
        for (int i = 1; i < end; i++) {
            if (R[i] < 0)
                v = 256 + R[i];
            else
                v = R[i];
            if (v > max)
                max = v;
        }
        System.out.println(max);
        return max;
    }

    public BufferedImage erosion(BufferedImage img,int shapeSize, int[][] mask){
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage erosionImage = new BufferedImage(img.getWidth(),img.getHeight(), img.getType());

        int sSize = 2 * shapeSize + 1;

        int filterWidth = width - sSize;
        int filterHeight = height - sSize;
        int lowerSide = height - shapeSize;
        int rightSide = width - shapeSize;

        int dilation = 0;

        //center
        for (int x = 0; x <= filterWidth; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                dilation = min(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x+shapeSize, y+shapeSize, newColor.getRGB());
            }
        }

        //leftborder
        for (int x = 0; x < shapeSize; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                dilation = min(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y + shapeSize, newColor.getRGB());
            }
        }

        //leftlowerside
        for (int x = 0 ; x <shapeSize;x++){
            for (int y = lowerSide ; y < height;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //leftupperside
        dilation = min(mask,sSize);
        for (int x = 0 ; x <shapeSize ; x++){
            for (int y = 0 ; y < shapeSize ; y++){
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //rightborder
        for (int x = rightSide; x < width; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                dilation = min(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y + shapeSize, newColor.getRGB());
            }
        }

        //rightlowerside
        for (int x = rightSide ; x <width;x++){
            for (int y = lowerSide ; y < height;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //lowerborder
        for (int y = lowerSide - 1; y < height; y++) {
            for (int x = 0; x <= filterWidth; x++) {
                dilation = min(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x + shapeSize, y, newColor.getRGB());
            }
        }

        //upperborder
        for (int y = 0; y < shapeSize; y++) {
            for (int x = 0; x <= filterWidth; x++) {
                dilation = min(mask,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x + shapeSize, y, newColor.getRGB());
            }
        }

        for (int x = rightSide ; x <width;x++){
            for (int y = 0 ; y < shapeSize;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return erosionImage;
    }

    public int min(int[][] val,int sSize) {

        int[] R = new int[sSize];

        int count = 0;
        for(int i = 0; i < val.length ; i++){
            for(int j = 0 ; j < val[i].length ; j++){
                if(val[i][j] == 0){
                    continue;
                }
                R[count] =  val[i][j];
                count++;
            }
        }

        int min = 256;
        int end = val.length;
        int v = 0;
        for (int i = 0; i < end; i++) {
            if(R[i] < 0)
                v = 256+R[i];
            else
                v = R[i];
            if (v < min)
                min = v;
        }
        System.out.println(min);
        return min;
    }

    public BufferedImage opening(BufferedImage img,int shapeSize,int[][] mask){

        img = erosion(img,shapeSize,mask);
        img = dilation(img,shapeSize,mask);
        return img;
    }
    */
    //version 2

    /*
    public BufferedImage dilation(BufferedImage img,int shapeSize){
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage dilationImage = new BufferedImage(img.getWidth(),img.getHeight(), img.getType());

        int sSize = 2 * shapeSize + 1;

        int filterWidth = width - sSize;
        int filterHeight = height - sSize;
        int lowerSide = height - shapeSize;
        int rightSide = width - shapeSize;

        int dilation = 0;

        //center
        for (int x = 0; x <= filterWidth; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                this.structElem = constructShape(img, shapeSize,x,y);
                dilation = max(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x+shapeSize, y+shapeSize, newColor.getRGB());
            }
        }

        //leftborder
        for (int x = 0; x < shapeSize; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                this.structElem = constructShape(img, shapeSize,0,y);
                dilation = max(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y + shapeSize, newColor.getRGB());
            }
        }

        //leftlowerside
        for (int x = 0 ; x <shapeSize;x++){
            for (int y = lowerSide ; y < height;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //leftupperside
        this.structElem = constructShape(img, shapeSize,0,0);
        dilation = max(structElem,sSize);
        for (int x = 0 ; x <shapeSize ; x++){
            for (int y = 0 ; y < shapeSize ; y++){
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //rightborder
        for (int x = rightSide; x < width; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                this.structElem = constructShape(img, shapeSize,filterWidth,y);
                dilation = max(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y + shapeSize, newColor.getRGB());
            }
        }

        //rightlowerside
        for (int x = rightSide ; x <width;x++){
            for (int y = lowerSide ; y < height;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //lowerborder
        for (int y = lowerSide - 1; y < height; y++) {
            for (int x = 0; x <= filterWidth; x++) {
                this.structElem = constructShape(img,shapeSize,x,filterHeight);
                dilation = max(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x + shapeSize, y, newColor.getRGB());
            }
        }

        //upperborder
        for (int y = 0; y < shapeSize; y++) {
            for (int x = 0; x <= filterWidth; x++) {
                this.structElem = constructShape(img, shapeSize,x,0);
                dilation = max(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x + shapeSize, y, newColor.getRGB());
            }
        }

        for (int x = rightSide ; x <width;x++){
            for (int y = 0 ; y < shapeSize;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                dilationImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return dilationImage;
    }

    public int max(Color[][] val,int sSize) {

        int[] R = new int[sSize];
        int[] G = new int[sSize];
        int[] B = new int[sSize];
        int count = 0;
        for(int i = 0; i < val.length ; i++){
            for(int j = 0 ; j < val[i].length ; j++){
                if(val[i][j] == null){
                    continue;
                }
                R[count] =  val[i][j].getRed();
                count++;
            }
        }

        int max = R[0];
        int end = R.length;
        int v = 0;
        for (int i = 1; i < end; i++) {
            if (R[i] < 0)
                v = 256 + R[i];
            else
                v = R[i];
            if (v > max)
                max = v;
        }
        System.out.println(max);
        return max;
    }

    public BufferedImage erosion(BufferedImage img,int shapeSize){
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage erosionImage = new BufferedImage(img.getWidth(),img.getHeight(), img.getType());

        int sSize = 2 * shapeSize + 1;

        int filterWidth = width - sSize;
        int filterHeight = height - sSize;
        int lowerSide = height - shapeSize;
        int rightSide = width - shapeSize;

        int dilation = 0;

        //center
        for (int x = 0; x <= filterWidth; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                this.structElem = constructShape(img, shapeSize,x,y);
                dilation = min(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x+shapeSize, y+shapeSize, newColor.getRGB());
            }
        }

        //leftborder
        for (int x = 0; x < shapeSize; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                this.structElem = constructShape(img,  shapeSize,0,y);
                dilation = min(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y + shapeSize, newColor.getRGB());
            }
        }

        //leftlowerside
        for (int x = 0 ; x <shapeSize;x++){
            for (int y = lowerSide ; y < height;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //leftupperside
        this.structElem = constructShape(img,shapeSize,0,0);
        dilation = min(structElem,sSize);
        for (int x = 0 ; x <shapeSize ; x++){
            for (int y = 0 ; y < shapeSize ; y++){
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //rightborder
        for (int x = rightSide; x < width; x++) {
            for (int y = 0; y <= filterHeight; y++) {
                this.structElem = constructShape(img, shapeSize,filterWidth,y);
                dilation = min(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y + shapeSize, newColor.getRGB());
            }
        }

        //rightlowerside
        for (int x = rightSide ; x <width;x++){
            for (int y = lowerSide ; y < height;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y, newColor.getRGB());
            }
        }

        //lowerborder
        for (int y = lowerSide - 1; y < height; y++) {
            for (int x = 0; x <= filterWidth; x++) {
                this.structElem = constructShape(img, shapeSize,x,filterHeight);
                dilation = min(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x + shapeSize, y, newColor.getRGB());
            }
        }

        //upperborder
        for (int y = 0; y < shapeSize; y++) {
            for (int x = 0; x <= filterWidth; x++) {
                this.structElem = constructShape(img, shapeSize,x,0);
                dilation = min(structElem,sSize);
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x + shapeSize, y, newColor.getRGB());
            }
        }

        for (int x = rightSide ; x <width;x++){
            for (int y = 0 ; y < shapeSize;y++){
                Color newColor = new Color(dilation,dilation,dilation);
                erosionImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return erosionImage;
    }

    public int min(Color[][] val,int sSize) {

        int[] R = new int[sSize];
        int[] G = new int[sSize];
        int[] B = new int[sSize];
        int count = 0;
        for(int i = 0; i < val.length ; i++){
            for(int j = 0 ; j < val[i].length ; j++){
                if(val[i][j] == null){
                    continue;
                }
                R[count] =  val[i][j].getRed();
                count++;
            }
        }

        int min = 256;
        int end = val.length;
        int v = 0;
        for (int i = 0; i < end; i++) {
            if(R[i] < 0)
                v = 256+R[i];
            else
                v = R[i];
            if (v < min)
                min = v;
        }

        System.out.println(min);
        return min;
    }

    public Color[][] constructShape(BufferedImage img,int shapeSize, int x, int y) {
        int size = 2 * shapeSize + 1;
        Color[][] structElem = new Color[size][size];
        for (int i = 0; i < size; i++) {
                structElem[shapeSize][i] = new Color(img.getRGB(x,i+y));
        }

        return structElem;
    }

    public BufferedImage opening(BufferedImage img,int shapeSize){

        img = erosion(img,shapeSize);
        img = dilation(img,shapeSize);
        return img;
    }

     */

    //version 3

    public BufferedImage dilation(BufferedImage img,int[] mask,int maskSize)
    {
        int maxr, maxg, maxb,width,height,pointOffset;

        width = img.getWidth();
        height = img.getHeight();

        int [][] pTab =convertTo2DArray(img);
        int [] pixels = convertTo1D(pTab,width,height);

        int indent = (int) Math.floor(maskSize/2);
        int [] newPixels = new int[height*width];

        int radius=((maskSize*maskSize)-1)/2;
        BufferedImage newImage = new BufferedImage(width,height,img.getType());

        //move through input array pixel by pixel
        for (int y=indent;y<height-indent;y++)
        {
            for (int x=indent;x<width-indent;x++)
            {
                //calculate centre of mask
                pointOffset = y*width+x;

                maxr = 0;
                maxg = 0;
                maxb = 0;

                //find max of RGB values covered by mask
                for (int m=-indent;m<=indent;m++)
                {
                    for (int n=-indent;n<=indent;n++)
                    {
                        maxr = Math.max(maxr,((pixels[pointOffset+n+m*width]
                                >>16)&0xff) * mask[radius+n+m*maskSize]);
                        maxg = Math.max(maxg,((pixels[pointOffset+n+m*width]
                                >>8)&0xff) * mask[radius+n+m*maskSize]);
                        maxb = Math.max(maxb,(pixels[pointOffset+n+m*width]
                                &0xff) * mask[radius+n+m*maskSize]);
                    }
                }
                newPixels[pointOffset] = (255<<24) | maxr<<16 | maxg<<8 | maxb; //calculate pixel value
            }
        }

        pixels = newPixels;
        newImage.setRGB(0, 0, width, height, pixels, 0, width);
        return newImage;
    }

    public BufferedImage erosion(BufferedImage img,int[] mask,int maskSize)
    {
        int minr, ming, minb,width,height,pointOffset;

        width = img.getWidth();
        height = img.getHeight();

        BufferedImage newImage = new BufferedImage(width,height,img.getType());
        int [][] pTab =convertTo2DArray(img);
        int [] pixels = convertTo1D(pTab,width,height);

        int indent = (int) Math.floor(maskSize/2);
        int [] newPixels = new int[height*width];

        int radius=((maskSize*maskSize)-1)/2;

        //move through input array pixel by pixel
        for (int y=indent;y<height-indent;y++)
        {
            for (int x=indent;x<width-indent;x++)
            {
                //calculate centre of mask
                pointOffset = y*width+x;

                minr = 255;
                ming = 255;
                minb = 255;

                //find min of RGB values covered by mask
                for (int m=-indent;m<=indent;m++)
                {
                    for (int n=-indent;n<=indent;n++)
                    {

                        if (mask[radius+n+m*maskSize] !=0)
                        {
                            minr = Math.min(minr,((pixels[pointOffset+n+m*width]
                                    >>16)&0xff)*mask[radius+n+m*maskSize]);
                            ming = Math.min(ming,((pixels[pointOffset+n+m*width]
                                    >>8)&0xff)*mask[radius+n+m*maskSize]);
                            minb = Math.min(minb,(pixels[pointOffset+n+m*width]
                                    &0xff)*mask[radius+n+m*maskSize]);
                        }
                    }
                }

                newPixels[pointOffset] = (255<<24) | minr<<16 | ming<<8 | minb; //calculate pixel value
            }
        }
        pixels = newPixels;
        newImage.setRGB(0, 0, width, height, pixels, 0, width);
        return newImage;

    }
    public BufferedImage opening(BufferedImage img,int[] mask,int maskSize){

        img = erosion(img,mask,maskSize);
        img = dilation(img,mask,maskSize);
        return img;
    }

}

