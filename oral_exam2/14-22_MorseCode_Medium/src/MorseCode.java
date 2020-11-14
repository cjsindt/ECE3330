import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

/**
 * Class that displays 2 JTextFields and converts english to morse code and vice versa.
 */
public class MorseCode extends JFrame {

    /**
     * First text field
     */
    private JTextField textField1;

    /**
     * Second text field
     */
    private JTextField textField2;

    /**
     * Table for conversion between morse and english
     */
    Hashtable<Character, String> t = new Hashtable<Character, String>();

    /**
     * Creates a new JFrame for the user to interact with
     */
    public MorseCode(){
        super("Morse Code.");
        textField1 = new JTextField();
        textField2 = new JTextField();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textField1.addKeyListener(new textFieldOneListener());
        textField2.addKeyListener(new textFieldTwoListener());

        setLayout(new GridLayout(2,1));

        setPreferredSize(new Dimension(100,100));

        add(textField1);
        add(textField2);

        pack();

        t.put('A', ".- ");
        t.put('B', "-... ");
        t.put('C', "-.-. ");
        t.put('D', "-.. ");
        t.put('E', ". ");
        t.put('F', "..-. ");
        t.put('G', "--. ");
        t.put('H', ".... ");
        t.put('I', ".. ");
        t.put('J', ".--- ");
        t.put('K', "-.- ");
        t.put('L', ".-.. ");
        t.put('M', "-- ");
        t.put('N', "-. ");
        t.put('O', "--- ");
        t.put('P', ".--. ");
        t.put('Q', "--.- ");
        t.put('R', ".-. ");
        t.put('S', "... ");
        t.put('T', "- ");
        t.put('U', "..- ");
        t.put('V', "...- ");
        t.put('W', ".-- ");
        t.put('X', "-..- ");
        t.put('Y', "-.-- ");
        t.put('Z', "--.. ");
        t.put('1', ".---- ");
        t.put('2', "..--- ");
        t.put('3', "...-- ");
        t.put('4', "....- ");
        t.put('5', "..... ");
        t.put('6', "-.... ");
        t.put('7', "--... ");
        t.put('8', "---.. ");
        t.put('9', "----. ");
        t.put('0', "----- ");
        t.put(' ', "   ");

    }

    /**
     * Converts the given english string to Morse code
     * @param s english string to convert
     * @return  the morse conversion of the english string
     */
    private String convertEnglishToMorse(String s){
        s = s.toUpperCase(); //make string uppercase for ease of use
        StringBuilder result = new StringBuilder(); //result stringbuilder

        for(int i = 0; i < s.length(); i++){ //validate each character is a digit or letter
            if(Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i)) || s.charAt(i) == ' '){
                result.append(t.get(s.charAt(i)));
            } else{
                return " ";
            }
        }

        return result.toString();
    }

    /**
     * Converts the given morse string to English
     * @param s morse string to convert
     * @return  converted morse string in english
     */
    private String convertMorseToEnglish(String s){
        s = s.toUpperCase();
        StringBuilder temp = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < s.length(); i++) { //loop through input string

            for (int j = i; s.charAt(j) != ' ' && j < s.length(); j++) { //read until a space is reached
                temp.append(s.charAt(j));
                i++;
            }

            temp.append(" ");

            for(Character c : t.keySet()){ //check if morse letter is valid
                if(t.get(c).equals(temp.toString())){
                    result.append(c);
                    temp.delete(0, temp.length());
                }
            }

            //temp is only deleted if there is a valid letter found
            if(temp.length() > 0){
                return " ";
            }

            if(i + 3 < s.length()){
                if(s.substring(i, i+3).equals("   ")){
                    result.append(' ');
                    i+=3;
                }
            }
        }

        return result.toString();

    }

    /**
     * Key listener for the first text field
     */
    private class textFieldOneListener implements KeyListener {


        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        /**
         * Converts the given string to morse or english depending on which it is
         * @param keyEvent  key event
         */
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if(Character.isDigit(textField1.getText().charAt(0)) || Character.isLetter(textField1.getText().charAt(0))){
                textField2.setText(convertEnglishToMorse(textField1.getText()));
            }
            if(textField1.getText().charAt(0) == '.' || textField1.getText().charAt(0) == '-'){
                textField2.setText(convertMorseToEnglish(textField1.getText()));
            }
        }
    }

    /**
     * Key listener for text field 2
     */
    private class textFieldTwoListener implements KeyListener {


        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        /**
         * Converts the given string to morse or english depending on which it is
         * @param keyEvent  key event
         */
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if(Character.isDigit(textField2.getText().charAt(0)) || Character.isLetter(textField2.getText().charAt(0))){
                textField1.setText(convertEnglishToMorse(textField2.getText()));
            }
            if(textField2.getText().charAt(0) == '.' || textField2.getText().charAt(0) == '-'){
                textField1.setText(convertMorseToEnglish(textField1.getText()));
            }
        }
    }
}
