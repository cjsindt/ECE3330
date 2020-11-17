import javax.swing.*;

public class EdgeDetectorDriver {
    public static void main(String[] args){
        JFrame j0 = new JFrame("Original Image");
        j0.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageConverter img = new ImageConverter("img/EASY_sample_image.bmp");
        j0.getContentPane().add(new JLabel(new ImageIcon(img.generateImage())));
        j0.pack();
        j0.setVisible(true);

        JFrame j1 = new JFrame("Vertical Edge Detection");
        j1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        j1.getContentPane().add(new JLabel(new ImageIcon(EdgeDetector.detectVerticalEdges(img.getData(), img.getWidth(), img.getHeight()))));
        j1.pack();
        j1.setVisible(true);
    }
}
