import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;

public class ImageConverter {
    private int[][] data;
    private int height;
    private int width;

    public ImageConverter(String filePath){
        //File f = new File(this.getResource(filePath).toString()); //generate a new file from the specified path

        try{
            BufferedImage img = ImageIO.read(getClass().getResource(filePath)); //generate a new buffered image
            height = img.getHeight();
            width = img.getWidth();
            data = new int[height+2][width+2]; //instantiate the data array with a buffer row of zeroes around the outside

            for(int i = 1; i < height+1; i++){ //sample each pixel and store it in the data array
                for(int j = 1; j < width+1; j++){
                    data[i][j] = img.getRGB(i-1, j-1);
                }
            }

        } catch(Exception e){ //an error occurred in generating the buffered image, so print an error
            System.err.println("Could not load image");
            e.printStackTrace();
        }
    }

    public int[][] getData(){
        return data;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public BufferedImage generateImage(){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                img.setRGB(i, j, data[i][j]);
            }
        }
        return img;
    }
}
