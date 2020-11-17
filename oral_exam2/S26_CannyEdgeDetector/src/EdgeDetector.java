import java.awt.image.BufferedImage;

public class EdgeDetector {


    public static BufferedImage detectVerticalEdges(int[][] img, int width, int height){
        BufferedImage result = new BufferedImage(width+2, height+2, BufferedImage.TYPE_BYTE_GRAY);
        int sum = 0; //sum of each pixel computed with bitmap

        for(int i = 1; i < width+1; i++){ //using a vertical image bitmap, compute the vertical edge magnitude for each pixel
            for(int j = 1; j < height+1; j++){
                sum += img[j-1][i-1] * -1;
                sum += img[j][i-1] * -1;
                sum += img[j+1][i-1] * -1;
                sum += img[j-1][i+1];
                sum += img[j][i+1];
                sum += img[j+1][i+1];
                result.setRGB(i, j, sum);
                sum = 0;
            }
        }

        return result;
    }
}
