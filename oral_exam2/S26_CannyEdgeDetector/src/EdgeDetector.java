import java.awt.image.BufferedImage;

/**
 * This class contains methods for edge detection on an image stored as a 2d array of integers
 */
public class EdgeDetector {

    /**
     * Detects vertical edges using a 3x3 bitmap.
     * @param img   the desired img to detect edges on, stored as a int[][]
     * @param width the width of the image
     * @param height    the height of the image
     * @return  a BufferedImage showing the vertical edges of the image
     */
    public static BufferedImage detectVerticalEdges(int[][] img, int width, int height){
        BufferedImage result = new BufferedImage(width+2, height+2, BufferedImage.TYPE_BYTE_GRAY);
        int sum = 0; //sum of each pixel computed with bitmap

        for(int i = 1; i < width+1; i++){ //using a vertical image bitmap, compute the vertical edge magnitude for each pixel
            for(int j = 1; j < height+1; j++){
                sum += Integer.toUnsignedLong(img[j-1][i-1]) * -1;
                sum += Integer.toUnsignedLong(img[j][i-1]) * -1;
                sum += Integer.toUnsignedLong(img[j+1][i-1]) * -1;
                sum += Integer.toUnsignedLong(img[j-1][i+1]);
                sum += Integer.toUnsignedLong(img[j][i+1]);
                sum += Integer.toUnsignedLong(img[j+1][i+1]);
                result.setRGB(i, j, sum);
                sum = 0;
            }
        }

        return result;
    }

    /**
     * Performs hysteresis on an image
     * @param img   the image to perform hysteresis on
     * @param width the width of the image
     * @param height    the height of the image
     * @return  a BufferedImage showing the edges of the image after hysteresis
     */
    public static BufferedImage hysteresis(int[][] img, int width, int height){
        BufferedImage result = new BufferedImage(width+2, height+2, BufferedImage.TYPE_BYTE_GRAY);
        boolean[][] trueEdges = new boolean[width+2][height+2]; //state of each pixel, either is or isn't a true edge
        boolean delta = false; //becomes true if a pixel changes state
        int r = (img[0][0]>>16)&0xFF;
        int g = (img[0][0]>>8)&0xFF;
        int b = (img[0][0])&0xFF;
        int low = (r+g+b)/3; //lowest magnitude
        int high = (r+g+b)/3; //highest magnitude

        for(int[] a : img){ //compute the low and high magnitudes
            for(int i : a){
                r = (i>>16)&0xFF;
                g = (i>>8)&0xFF;
                b = i&0xFF;
                if((r+g+b)/3 > high){
                    high = (r+g+b)/3;
                } else if((r+g+b)/3 < low){
                    low = (r+g+b)/3;
                }
                //System.out.println((byte)i);
            }
        }

        //System.out.println("High: " + high + " Low: " + low);

        high = high-80; //set high to a lower value to encompass more points
        low = high/2; //set low to half of high to not include points that are obviously not edges

        //System.out.println("High: " + high + " Low: " + low);

        for(int i = 1; i < width+1; i++){ //mark edges as true if they are greater than the high value
            for(int j = 1; j < height+1; j++){
                r = (img[i][j]>>16)&0xFF;
                g = (img[i][j]>>8)&0xFF;
                b = img[i][j]&0xFF;
                if((r+g+b)/3 >= high){
                    trueEdges[i][j] = true;
                }
            }
        }

        //loop until no pixels change;
        //mark a pixel as a true edge if:
        //1. it has a value greater than low
        //2. it has a neighbor that is a true edge
        do{
            delta = false;
            for(int i = 1; i < width+1; i++){
                for(int j = 1; j < height+1; j++){
                    r = (img[i][j]>>16)&0xFF;
                    g = (img[i][j]>>8)&0xFF;
                    b = img[i][j]&0xFF;
                    if((r+g+b)/3 > low && !trueEdges[i][j] && (trueEdges[i-1][j] || trueEdges[i+1][j] || trueEdges[i][j-1] || trueEdges[i][j+1])){
                        trueEdges[i][j] = true;
                        delta = true;
                    }
                }
            }
        } while(delta);

        //Finally, set each true edge to be white and each non-edge to be black
        for(int i = 0; i < width+2; i++){
            for(int j = 0; j < height+2; j++){
                if(trueEdges[i][j]){
                    result.setRGB(i, j, Integer.MAX_VALUE);
                }
            }
        }

        return result;
    }
}
