import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class to play the classic game Hangman. Provides a JFrame in which the user can play the game.
 * @author cjsint
 * @version 1.0.0 22 OCT 2020
 */
public class Hangman extends JFrame {

    /**
     * The word to be guessed by the user
     */
    private String word = "";

    /**
     * The sequence of letters to be guessed. Essentially a character array of the word.
     */
    private char[] guessSequence;

    /**
     * A list of incorrect guesses.
     */
    private ArrayList<String> incorrectGuesses = new ArrayList<String>();

    /**
     * The area in which the user inputs their guess.
     */
    private JTextField guessArea;

    /**
     * The area in which the word is displayed.
     */
    private JTextField wordArea;

    /**
     * The area in which the incorrect guesses are displayed.
     */
    private JTextArea incorrectGuessArea;

    /**
     * The guess button.
     */
    private JButton guessButton;

    /**
     * The number of guesses left.
     */
    private int guessesLeft = 6;

    /**
     * The constructor makes a window in which the user can play the classic game Hangman.
     */
    public Hangman(){
        //call superclass constructor and set layout
        super("Hangman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        //JPanel to hold the UI
        JPanel guessPanel = new JPanel();
        guessPanel.setLayout(new BoxLayout(guessPanel, BoxLayout.PAGE_AXIS));

        //list of random words
        String[] words = {"SHRIMP", "LOBSTER", "AXOLOTL", "URCHIN", "BARNACLE"};

        //choose a random word to use for puzzle
        word = words[(int)(Math.random()*words.length)];

        //instantiate each private variable
        guessArea = new JTextField();
        incorrectGuessArea = new JTextArea();
        incorrectGuessArea.setEditable(false);
        incorrectGuessArea.setLineWrap(true);
        guessButton = new JButton("Guess");
        wordArea = new JTextField();
        wordArea.setEditable(false);
        JLabel guesses = new JLabel("Guesses left: " + guessesLeft);

        //add each UI element to the JPanel
        guessPanel.add(wordArea);
        guessPanel.add(new JLabel("Guess here:"));
        guessPanel.add(guessArea);
        guessPanel.add(guessButton);
        guessPanel.add(incorrectGuessArea);
        guessPanel.add(guesses);

        //add action listener to guessButton as an anonymous class
        guessButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //only do something if the user inputs valid string
                if(guessArea.getText().length() > 0){
                    guess(guessArea.getText());
                    guesses.setText("Guesses left: " + guessesLeft);
                    guessArea.setText("");
                }
                if(guessesLeft == 0){
                    guessButton.setEnabled(false);
                    guess(word);
                    guesses.setText("GAME OVER!");
                    guessArea.setEditable(false);
                } else if(isSolved()){
                    guessButton.setEnabled(false);
                    guesses.setText("CONGRATULATIONS!");
                    guessArea.setEditable(false);
                }
            }
        });

        //add the JPanel to the JFrame
        add(guessPanel);

        //make the guessSequence array the length of the word and set each value to _
        guessSequence = new char[word.length()];
        for(int i = 0; i < guessSequence.length; i++){
            guessSequence[i] = '_';
        }
        updateWordText();
        pack();

    }

    /**
     * Updates the word text field with the user's guesses, if any are correct.
     */
    private void updateWordText(){
        wordArea.setText("");
        for(int i = 0; i < guessSequence.length; i++){
            wordArea.setText(wordArea.getText() + guessSequence[i] + " ");
        }
    }

    /**
     * Updates the incorrect guesses field with the user's incorrect guesses, if there are any.
     */
    private void updateIncorrectGuesses(){
        incorrectGuessArea.setText("");
        for(int i = 0; i < incorrectGuesses.size(); i++){
            incorrectGuessArea.setText(incorrectGuessArea.getText() + incorrectGuesses.get(i) + " ");
        }
    }

    /**
     * Checks whether the user solved the puzle or not.
     * @return  true if the puzzle is solved, false otherwise
     */
    private boolean isSolved(){
        //check each char in the guess sequence and if there are no _ then the puzzle is solved
        for(char c : guessSequence){
            if(c == '_'){
                return false;
            }
        }
        return true;
    }

    /**
     * The main logic of the program. Handles the guessing logic. Takes any string from the user and analyzes if it
     * is a valid guess or not and updates any respective fields as well as the guesses remaining.
     * @param s the guess from the user
     */
    private void guess(String s){
        s = s.toUpperCase(); //case insensitive
        if(s.length() == 1){ //if the user inputs a single char
            if(!word.contains(s)){ //if the char is not in the word, add it to incorrect guesses if not already there
                if(!incorrectGuesses.contains(s)) {
                    incorrectGuesses.add(s);
                    guessesLeft--;
                }
            } else { //if the words contains the guess, add the letter to the guess sequence
                guessSequence[word.indexOf(s)] = s.charAt(0);
            }
        } else { //the user guessed a word
            if(word.equals(s)){ //if the word and the guess are equal, update guess sequence
                for(int i = 0; i < guessSequence.length; i++){
                    guessSequence[i] = word.charAt(i);
                }
            } else { //the words don't match, so add the guess to the incorrect guesses
                if(!incorrectGuesses.contains(s)) {
                    incorrectGuesses.add(s);
                    guessesLeft--;
                }
            }
        }
        //update word and incorrect guesses area
        updateIncorrectGuesses();
        updateWordText();
    }
}
