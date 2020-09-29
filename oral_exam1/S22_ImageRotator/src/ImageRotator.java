import javax.imageio.ImageIO;
import javax.swing.*;
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
    private JTextField angleTextField = new JTextField("Angle to rotate");

    /**
     * When checked, the image will keep rotating when the rotate button is pressed
     */
    private JCheckBox continuous = new JCheckBox("Continuous");

    /**
     * JLabel that shows text for any errors encountered from user
     */
    private JLabel errorLabel = new JLabel();

    /**
     * ImageLabel to handle the rotating and drawing of the picture
     */
    private ImageLabel frog = new ImageLabel();

    public ImageRotator(){
        //set the layout of the main JFrame with a flowlayout
        setLayout(new FlowLayout());
        //add the action listener to rotateButton
        rotateButton.addActionListener(new rotateButtonActionListener());
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new BoxLayout(buttonArea, BoxLayout.PAGE_AXIS)); //layout buttons/textfields from top to bottom
        buttonArea.add(angleTextField);
        buttonArea.add(rotateButton);
        buttonArea.add(continuous);
        buttonArea.add(errorLabel);

        try{
            BufferedImage image = ImageIO.read(new File("/iahome/c/cj/cjsindt/Desktop/cjsindt_swd/oral_exam1/S22_ImageRotator/tomatofrog.jpeg"));
            frog.setImage(image);
            add(frog);
        } catch (Exception e){
            System.out.println("Cannot read file");
        }

        add(buttonArea);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
        pack();
    }

    private class rotateButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae){
            try{
                double theta = Double.parseDouble(angleTextField.getText());
                frog.setImage(frog.rotateImage(theta));
                errorLabel.setText("");
            } catch (Exception e){
                errorLabel.setText("Invalid Angle!");
            }

        }
    }

    private class ImageLabel extends JLabel{

        /**
         * The current state of the BufferedImage
         */
        BufferedImage image;

        /**
         * Sets the image on the JLabel to the provided BufferedImage
         * @param imageToSet    the new BufferedImage
         */
        public void setImage(BufferedImage imageToSet){
            setIcon(new ImageIcon(imageToSet));
            image = imageToSet;
        }

        public BufferedImage getImage(){
            return image;
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

            //draw the new image, don't care about any filters; at 0,0
            g.drawImage(image, null, 0, 0);

            //done with the graphics so free up memory
            g.dispose();

            //return the resulting image
            return result;
        }
    }

}
