import javax.swing.*;

public class EdgeDetectorDriver {
    public static void main(String[] args){
        JFrame j0 = new JFrame("Original Image");
        j0.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageConverter img = new ImageConverter("img/EASY_sample_image.bmp");
        j0.getContentPane().add(new JLabel(new ImageIcon(img.generateImage())));
        j0.pack();
        j0.setVisible(true);
    }
}
