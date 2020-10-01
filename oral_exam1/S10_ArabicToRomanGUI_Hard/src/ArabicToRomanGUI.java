import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import java.awt.event.KeyAdapter;

/**
 * Class to make a window for the user to interact with. The window has 2 text fields and converts numbers given to it
 * to either Roman Numerals or to Arabic Numerals
 * @author cjsindt
 * @version 1.0.0 30 SEPT 2020
 */
public class ArabicToRomanGUI extends JFrame {

    /**
     * Text field that takes input from the user as Arabic Numerals. Also displays Arabic conversion if input is given
     * to the other text field.
     */
    private JTextField upperTextField;

    /**
     * Text field that takes input from the user as Roman Numerals. Also displays Roman conversion if input is given
     * to the other text field
     */
    private JTextField lowerTextField;

    /**
     * Constructor that makes a new Arabic-Roman Numeral converter
     */
    public ArabicToRomanGUI(){
        super("Arabic to Roman Numeral Converter");

        //instantiate the text fields
        upperTextField = new JTextField();
        lowerTextField = new JTextField();

        //add key listeners to the text fields
        upperTextField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){
                //make sure the string isnt empty
                if(upperTextField.getText().length() != 0) {
                    //if first character is a letter, convert roman to arabic and vice versa if first char is digit
                    if (Character.isLetter(upperTextField.getText().charAt(0))) {
                        lowerTextField.setText(convertRomanToArabic(upperTextField.getText()));
                    } else {
                        lowerTextField.setText(convertArabicToRoman(upperTextField.getText()));
                    }
                } else {
                    lowerTextField.setText("");
                }
            }
        });

        lowerTextField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){
                //make sure the string isnt empty
                if(lowerTextField.getText().length() != 0) {
                    //if first character is a letter, convert roman to arabic and vice versa if first char is digit
                    if (Character.isLetter(lowerTextField.getText().charAt(0))) {
                        upperTextField.setText(convertRomanToArabic(lowerTextField.getText()));
                    } else {
                        upperTextField.setText(convertArabicToRoman(lowerTextField.getText()));
                    }
                } else {
                    upperTextField.setText("");
                }
            }
        });

        //set the layout to a box layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));


        //add the components
        add(upperTextField);
        add(lowerTextField);

        //set the default close operation
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //make it visible and pack
        setVisible(true);
        pack();
    }

    /**
     * Takes a string of numbers and converts it to Roman Numerals
     * @param input a string of numbers
     * @return  roman numeral representation of given string. Error message if unable to convert
     */
    public static String convertArabicToRoman(String input){
        //integer representation of the input string
        int inputInt = 0;
        //resulting string (Roman numeral conversion)
        String result = "";

            //try to parse an int from the input string
            try {
                inputInt = Integer.parseInt(input);
            } catch (Exception e) {
                result += "Invalid Number";
            }

            //check bounds on number given
            if (inputInt > 0 && inputInt < 4000) {
                //add M for each 1000
                while (inputInt >= 1000) {
                    inputInt -= 1000;
                    result += "M";
                }

                //convert hundreds place
                //subtractive notation for 900 and 400. Regular for the rest
                if (inputInt / 100 == 9) {
                    result += "CM";
                    inputInt -= 900;
                } else if (inputInt / 100 == 4) {
                    result += "CD";
                    inputInt -= 400;
                } else if (inputInt / 100 >= 5) {
                    result += "D";
                    inputInt -= 500;
                }
                //add the rest of the Cs for the remaining hundreds
                while (inputInt >= 100) {
                    inputInt -= 100;
                    result += "C";
                }

                //rinse and repeat for the rest of the powers of 10.
                if (inputInt / 10 == 9) {
                    result += "XC";
                    inputInt -= 90;
                } else if (inputInt / 10 == 4) {
                    result += "XL";
                    inputInt -= 40;
                } else if (inputInt / 10 >= 5) {
                    result += "L";
                    inputInt -= 50;
                }
                while (inputInt >= 10) {
                    inputInt -= 10;
                    result += "X";
                }

                if (inputInt == 9) {
                    result += "IX";
                    inputInt = 0;
                } else if (inputInt == 4) {
                    result += "IV";
                    inputInt = 0;
                } else if (inputInt >= 5) {
                    result += "V";
                    inputInt -= 5;
                }
                while (inputInt >= 1) {
                    inputInt -= 1;
                    result += "I";
                }
            }

        return result;
    }

    /**
     * Converts Roman Numerals to Arabic Numerals
     * @param input roman numerals to convert to arabic numerals
     * @return  a string of arabic numerals. Error message if unable to convert
     */
    public static String convertRomanToArabic(String input){
        //the running total
        int arabic = 0;

        //make sure the textfield isn't empty
        if(input.length() != 0) {
            //convert input to uppercase to make easier checking
            input = input.toUpperCase();

            //roman numerals follow a pattern; can use regular expression to represent that pattern
            String validNumerals = "^(M{0,3}((CM)|(CD)|(D?)C{0,3})((XC)|(XL)|(L?)X{0,3})((IX)|(IV)|(V?)I{0,3}))$";

            //if the input matches the regular expression
            if (Pattern.matches(validNumerals, input)) {
                for (int i = 0; i < input.length(); i++) {
                    switch (input.charAt(i)) {
                        case 'M':
                            arabic += 1000;
                            break;
                        case 'D':
                            arabic += 500;
                            break;
                        case 'C':
                            try {
                                if (input.charAt(i + 1) == 'M') { //check following character for subraction notation and advance 1 more place in the string
                                    arabic += 900;
                                    i++;
                                } else if (input.charAt(i + 1) == 'D') {
                                    arabic += 400;
                                    i++;
                                } else { //no subtraction notation means add the actual value of the letter
                                    arabic += 100;
                                }
                            } catch (Exception e) {
                                //if charAt(i+1) throws an exception, must mean at end of string, add the value of the letter
                                arabic += 100;
                            }
                            break;
                        case 'L':
                            arabic += 50;
                            break;
                        case 'X':
                            try {
                                if (input.charAt(i + 1) == 'C') {
                                    arabic += 90;
                                    i++;
                                } else if (input.charAt(i + 1) == 'L') {
                                    arabic += 40;
                                    i++;
                                } else {
                                    arabic += 10;
                                }
                            } catch (Exception e) {
                                arabic += 10;
                            }
                            break;
                        case 'V':
                            arabic += 5;
                            break;
                        case 'I':
                            try {
                                if (input.charAt(i + 1) == 'X') {
                                    arabic += 9;
                                    i++;
                                } else if (input.charAt(i + 1) == 'V') {
                                    arabic += 4;
                                    i++;
                                } else {
                                    arabic += 1;
                                }
                            } catch (Exception e) {
                                arabic += 1;
                            }
                            break;
                    }

                }
            } else {
                return "Invalid Roman Numerals";
            }
        } else { //textfield is empty
            return "";
        }

        return Integer.toString(arabic);
    }
}
