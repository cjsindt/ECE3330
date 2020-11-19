import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * This class converts a bmp file to an array of integers with each pixel's RGB value for edge detection.
 */
public class ImageConverter {
    /**
     * Each pixel's RGB data
     */
    private int[][] data;

    /**
     * The height of the image
     */
    private int height;

    /**
     * The width of the image
     */
    private int width;

    /**
     * Takes a file path, opens the file and reads each pixel's RGB data into the data array
     * @param filePath  path to the desired image
     */
    public ImageConverter(String filePath){
        //File f = new File(this.getResource(filePath).toString()); //generate a new file from the specified path

        try{
            BufferedImage img = ImageIO.read(getClass().getResource(filePath)); //generate a new buffered image
            height = img.getHeight();
            width = img.getWidth();
            data = new int[height+2][width+2]; //instantiate the data array with a buffer row of zeroes around the outside

            for(int i = 1; i < width+1; i++){ //sample each pixel and store it in the data array
                for(int j = 1; j < height+1; j++){
                    data[i][j] = img.getRGB(i-1, j-1);
                    //System.out.println(Integer.toBinaryString(r) + Integer.toBinaryString(g) + Integer.toBinaryString(b) + " " + Integer.toBinaryString(img.getRGB(i-1, j-1)));
                }
            }

        } catch(Exception e){ //an error occurred in generating the buffered image, so print an error
            System.err.println("Could not load image");
            e.printStackTrace();
        }
    }

    /**
     * Returns the pixel data array
     * @return
     */
    public int[][] getData(){
        return data;
    }

    /**
     * Returns the height of the image
     * @return
     */
    public int getHeight(){
        return height;
    }

    /**
     * Returns the width of the image
     * @return
     */
    public int getWidth(){
        return width;
    }

    /**
     * Generates a BufferedImage of the data in the pixel array
     * @return  a BufferedImage representation of the data in the pixel array
     */
    public BufferedImage generateImage(){
        BufferedImage img = new BufferedImage(width+2, height+2, BufferedImage.TYPE_BYTE_GRAY);
        for(int i = 1; i < height+1; i++) {
            for(int j = 1; j < width+1; j++) {

                img.setRGB(i, j, data[i][j]);
                //System.out.println(Integer.toBinaryString(img.getRGB(i, j)) + " <- Generate Image");
            }
        }
        return img;
    }
}
