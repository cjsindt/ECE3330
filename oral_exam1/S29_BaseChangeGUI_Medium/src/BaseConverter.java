import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseConverter extends JFrame {

    /**
     * Conversion string where the index of a character is its value in base 10.
     */
    private static final String CONVERSION = "0123456789ABCDEFGHIJKLMNOPQRSTUV";

    /**
     * Button that when pressed converts given number to different base.
     */
    private JButton convertButton;

    /**
     * Text field to hold the input number from the user.
     */
    private JTextField inputTextField;

    /**
     * Text field to display the resulting number.
     */
    private JTextField outputTextField;

    /**
     * Combo box to list the base to convert from.
     */
    private JComboBox currentBaseComboBox;

    /**
     * Combo box to list the base to convert to.
     */
    private JComboBox newBaseComboBox;

    /**
     * ImageIcon for the convert button when the input is valid.
     */
    private ImageIcon tomatoFrog;

    /**
     * ImageIcon for the convert button when the input is invalid.
     */
    private ImageIcon tomatoFrogAngry;

    /**
     * Constructor that creates a JFrame that handles user inputs to convert numbers.
     */
    public BaseConverter(){
        super("Base Converter"); //super class constructor
        setDefaultCloseOperation(EXIT_ON_CLOSE); //exit program on close of window
        JPanel mainPanel = new JPanel(); //main jpanel to hold buttons and textfields
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)); //set JPanel layout to a box layout
        setLayout(new FlowLayout()); //make the JFrame layout to a flow layout

        //input number
        JLabel input = new JLabel("Number");
        inputTextField = new JTextField();

        //list of bases
        String[] bases = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14" ,"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};
        JLabel fromBase = new JLabel("From base");
        currentBaseComboBox = new JComboBox(bases);

        JLabel toBase = new JLabel("To base");
        newBaseComboBox = new JComboBox(bases);

        //resulting number
        JLabel output = new JLabel("Result");
        outputTextField = new JTextField();
        outputTextField.setEditable(false);

        //read in images
        tomatoFrog = new ImageIcon(getClass().getResource("tomatofrog.jpeg"));
        tomatoFrogAngry = new ImageIcon(getClass().getResource("tomatofrogangry.jpeg"));

        //make convert button and set its icon
        convertButton = new JButton();
        convertButton.setIcon(tomatoFrog);
        convertButton.addActionListener(new converterActionListener());

        //add components to the jpanel
        mainPanel.add(input);
        mainPanel.add(inputTextField);
        mainPanel.add(fromBase);
        mainPanel.add(currentBaseComboBox);
        mainPanel.add(toBase);
        mainPanel.add(newBaseComboBox);
        mainPanel.add(output);
        mainPanel.add(outputTextField);
        mainPanel.add(convertButton);

        //add the jpanel to the jframe
        add(mainPanel);

        //pack and make the frame visible
        pack();
        setVisible(true);
    }

    /**
     * Takes a number and a base and returns whether that number can be written in that base.
     * @param number    the number to validate
     * @param base  the base the number is assumed to be in
     * @return  true if the number can be written in that base, false otherwise
     */
    private boolean validate(String number, int base){
        String conversionRadix = CONVERSION.substring(0, base); //the valid digits for the current base
        for(int i = 0; i < number.length(); i++){ //loop through each char in the number
            if(conversionRadix.indexOf(number.charAt(i)) == -1){ //return false if the character isn't a valid digit for the base
                return false;
            }
        }
        return true;
    }

    /**
     * Takes a number and its corresponding base, and converts it to the new base.
     * @param number    the number to convert
     * @param currentBase  the current base of the number
     * @param newBase  the new base of the number
     * @return  the converted number
     */
    private String convert(String number, int currentBase, int newBase){
        String convertedNumber = ""; //return value
        int baseTen = 0; //the number represented in base 10

        for(int i = 0; i < number.length(); i++){ //get the number in base 10
            //take the current digit, it's value in base ten, and multiply it by the radix^(length - (i+1))
            baseTen += CONVERSION.indexOf(number.charAt(i)) * Math.pow(currentBase, (number.length() - (i+1)));
        }

        //convert the number to the requested base
        while(baseTen > 0){
            //calculate the remainder and use that to get the character from CONVERSION
            convertedNumber = CONVERSION.charAt(baseTen % newBase) + convertedNumber;
            //divide the baseTen number by the new base
            baseTen /= newBase;
        }

        return convertedNumber;
    }

    /**
     * Private class to handle the button action listener.
     */
    private class converterActionListener implements ActionListener {
        /**
         * Checks if the input is valid, if it is, convert the input. If it is not valid, make the frog angry and set the output to blank.
         * @param actionEvent   default action event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //validate the input
            if(validate(inputTextField.getText(), currentBaseComboBox.getSelectedIndex()+2)){
                convertButton.setIcon(tomatoFrog);
                outputTextField.setText(convert(inputTextField.getText(), currentBaseComboBox.getSelectedIndex()+2, newBaseComboBox.getSelectedIndex()+2));
            } else {
                convertButton.setIcon(tomatoFrogAngry);
                outputTextField.setText("");
            }
        }
    }
}
