import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageRotator extends JFrame{

    /**
     * JButton to rotate the image when pressed
     */
    private JButton rotateButton = new JButton("Rotate Image!");

    /**
     * JTextField that takes from the user the amount of degrees to rotate by
     */
    private JTextField angleTextField = new JTextField("");

    /**
     * When checked, the image will keep rotating when the rotate button is pressed
     */
    private JCheckBox continuous = new JCheckBox("Continuous");

    /**
     * JLabel that shows text for any errors encountered from user
     */
    private JLabel errorLabel = new JLabel();

    /**
     * JLabel with a frog image to handle the rotating and drawing of the picture
     */
    private FrogLabel frog;

    /**
     * Timer for controlling the speed of the continuous rotation
     */
    private Timer timer = new Timer(0, new rotateButtonActionListener());

    /**
     * Shows the current speed of the rotations. Changed by the slider
     */
    private JLabel speedLabel = new JLabel("");

    /**
     * A slider that changes the speed of the rotation
     */
    private JSlider KKSlider = new JSlider(1, 100);

    public ImageRotator(){
        //call the super constructor naming the window Image Rotator
        super("Image Rotator!");
        //set the layout of the main JFrame with a flowlayout
        setLayout(new FlowLayout());
        //add the action listener to rotateButton
        rotateButton.addActionListener(new rotateButtonActionListener());
        //JPanel to hold the buttons on the right side of the JFrame
        JPanel buttonArea = new JPanel();
        //set the layout for the button area to a box layout, allowing the buttons to be arranged top to bottom
        buttonArea.setLayout(new BoxLayout(buttonArea, BoxLayout.PAGE_AXIS)); //layout buttons/textfields from top to bottom
        //set the text of speedlabel to the value of the slider
        speedLabel.setText("Speed: " + KKSlider.getValue());
        //add a changeListener to KKSlider
        KKSlider.addChangeListener(new sliderChangeListener());
        //add the buttons
        buttonArea.add(angleTextField);
        buttonArea.add(rotateButton);
        buttonArea.add(continuous);
        buttonArea.add(speedLabel);
        buttonArea.add(KKSlider);
        buttonArea.add(errorLabel);

        //attempt to read from the file and retrieve a buffered image of tomatofrog.jpeg
        try{
            BufferedImage image = ImageIO.read(new File("/iahome/c/cj/cjsindt/Desktop/cjsindt_swd/oral_exam1/S22_ImageRotator/tomatofrog.jpeg"));
            //assign frog to a new FrogLabel with the image read from the file
            frog = new FrogLabel(image);
            add(frog);
        } catch (Exception e){
            System.out.println("Cannot read file");
        }

        //add the button area panel to the JFrame
        add(buttonArea);

        //exit program on close of window
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //make the window visible and pack the containers/elements into the JFrame
        setVisible(true);
        pack();
    }

    private class rotateButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(continuous.isSelected()){
                timer.setDelay(1000-KKSlider.getValue()*10);
                timer.setRepeats(true);
                timer.start();
            } else {
                timer.stop();
                timer.setRepeats(false);
            }

            try {
                double theta = Double.parseDouble(angleTextField.getText());
                frog.setImage(frog.rotateImage(theta));
                errorLabel.setText("");
            } catch (Exception e) {
                errorLabel.setText("Invalid Angle!");
                timer.stop();
            }
        }
    }

    private class sliderChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e){
            speedLabel.setText("Speed: " + KKSlider.getValue());
        }
    }

    private class FrogLabel extends JLabel{

        /**
         * The current state of the BufferedImage
         */
        BufferedImage image;

        //private final int width = image.getWidth();

        //private final int height = image.getHeight();

        /**
         * Constructor that sets the image to the given BufferedImage
         * @param img   image to set
         */
        public FrogLabel(BufferedImage img){
            image = img;
            setIcon(new ImageIcon(rotateImage(0)));
        }

        /**
         * Sets the image on the JLabel to the provided BufferedImage
         * @param imageToSet    the new BufferedImage
         */
        public void setImage(BufferedImage imageToSet){
            setIcon(new ImageIcon(imageToSet));
            image = imageToSet;
        }

        /**
         * Rotates the image about it's center by the specified angle
         * @param angle the angle to rotate it by
         * @return  the rotated BufferedImage
         */
        public BufferedImage rotateImage(double angle){
            int height = image.getHeight();
            int width = image.getWidth();
            double theta = Math.toRadians(angle);

            //TODO: Fix Circle Problem (Converges to Circle, problem with Graphics bounds. Don't know what the math is)

            //resulting image should be same as original in terms of type and size
            BufferedImage result = new BufferedImage(width, height, image.getType());

            //turn the BufferedImage into a Graphics2D which can do rotations
            Graphics2D g = result.createGraphics();

            //rotate the newly made Graphics2D about the center of the image
            //rotate with translation otherwise it will rotate about the top-left corner
            g.rotate(theta, width/2.0,height/2.0);

            //g.setClip(0, 0, width*2, height*2);

            //draw the new image, don't care about any filters
            g.drawImage(image, null, 0, 0);

            //done with the graphics so free up memory
            g.dispose();

            //return the resulting image
            return result;
        }
    }

}
