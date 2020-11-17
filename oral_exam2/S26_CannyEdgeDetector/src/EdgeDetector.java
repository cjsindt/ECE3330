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

    public static BufferedImage hysteresis(int[][] img, int width, int height){
        BufferedImage result = new BufferedImage(width+2, height+2, BufferedImage.TYPE_BYTE_GRAY);
        boolean[][] trueEdges = new boolean[width+2][height+2]; //state of each pixel, either is or isn't a true edge
        boolean delta = false; //becomes true if a pixel changes state
        int low = img[0][0]; //lowest magnitude
        int high = img[1][1]; //highest magnitude

        for(int[] a : img){ //compute the low and high magnitudes
            for(int i : a){
                if(i < high){
                    high = i;
                } else if(i > low){
                    low = i;
                }
                System.out.println(i*-1);
            }
        }

        System.out.println("High: " + high + " Low: " + low);
        low = (high*-1)-100;

        System.out.println("High: " + high + " Low: " + low);

        for(int i = 1; i < width+1; i++){
            for(int j = 1; j < height+1; j++){
                if(img[i][j]*-1 >= low){
                    trueEdges[i][j] = true;
                }
            }
        }

        do{
            delta = false;
            for(int i = 1; i < width+1; i++){
                for(int j = 1; j < height+1; j++){
                    if(img[i][j]*-1 > low && !trueEdges[i][j] && (trueEdges[i-1][j] || trueEdges[i+1][j] || trueEdges[i][j-1] || trueEdges[i][j+1])){
                        trueEdges[i][j] = true;
                        delta = true;
                    }
                }
            }
        } while(delta);

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
