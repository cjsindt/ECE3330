import javax.swing.*;

public class EdgeDetectorDriver {
    public static void main(String[] args){
        JFrame j0 = new JFrame("EASY Original");
        j0.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageConverter imgEasy = new ImageConverter("img/EASY_sample_image.bmp");
        j0.getContentPane().add(new JLabel(new ImageIcon(imgEasy.generateImage())));
        j0.pack();
        j0.setVisible(true);
        j0.setLocation(0,0);

        JFrame j1 = new JFrame("EASY Edge");
        j1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        j1.getContentPane().add(new JLabel(new ImageIcon(EdgeDetector.detectVerticalEdges(imgEasy.getData(), imgEasy.getWidth(), imgEasy.getHeight()))));
        j1.pack();
        j1.setVisible(true);
        j1.setLocation(350, 0);

        JFrame j2 = new JFrame("MEDIUM Original");
        j2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageConverter imgMedium = new ImageConverter("img/MEDIUM_sample_image.bmp");
        j2.getContentPane().add(new JLabel(new ImageIcon(imgMedium.generateImage())));
        j2.pack();
        j2.setVisible(true);
        j2.setLocation(0,350);

        JFrame j3 = new JFrame("MEDIUM Hysteresis");
        j3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        j3.getContentPane().add(new JLabel(new ImageIcon(EdgeDetector.hysteresis(imgMedium.getData(), imgMedium.getWidth(), imgMedium.getHeight()))));
        j3.pack();
        j3.setVisible(true);
        j3.setLocation(350,350);
    }
}
