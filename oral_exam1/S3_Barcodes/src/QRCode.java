import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.net.URL;

/**
 * Class that is a GUI for creating QR Codes. One created a window pops up asking user for a URL to convert to a QR Code.
 * @author  cjsindt
 * @version 1.0.0 11 SEPT 2020
 */
public class QRCode extends JFrame{

    /**
     * JLabel to display the QR code or an error message if the URL given is invalid
     */
    private JLabel qr;

    /**
     * Text Field to get user input as a URL
     */
    private JTextField urlField; //text field to enter url

    /**
     * Constructor that makes a window and displays QR Code generator
     */
    public QRCode(){

        setLayout(new BorderLayout()); //using a border layout; text field on top, button on bottom, QR code in middle
        JButton generate = new JButton("Generate!"); //the generate button
        generate.addActionListener(new generateActionListener()); //add the action listener to handle button presses
        urlField = new JTextField("http://icon.uiowa.edu");
        qr = new JLabel();

        //add elements to JFrame
        add(generate, BorderLayout.SOUTH);
        add(urlField, BorderLayout.NORTH);
        add(qr, BorderLayout.CENTER);

        //set size of JFrame to 500x500
        setSize(500, 500);
        setVisible(true);
    }

    /**
     * Handles Generate button action
     */
    private class generateActionListener implements ActionListener {

        /**
         * Method that handles the button action by reading the user inputted URL and generating a QR code based on it.
         * @param ae
         */
        @Override
        public void actionPerformed(ActionEvent ae){
            //will throw exception if malformed URL
            try{
                //use google's QR API to generate a QR code based on a user inputted URL
                URL input = new URL("https://chart.googleapis.com/chart?cht=qr&chs=400x400&chl=" + urlField.getText());
                qr.setIcon(new ImageIcon(ImageIO.read(input))); //set the image in the JLabel to generated QR code
            } catch (Exception e){
                //invalid URL so state that to the user
                qr.setText("Invalid URL!");
            }
        }
    }
}


