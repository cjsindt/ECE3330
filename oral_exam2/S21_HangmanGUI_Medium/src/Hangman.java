import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hangman extends JFrame {

    private final String[] WORDS = {"SOFTWARE", "JAVA", "COMPUTER", "FROG", "LOBSTER", "HAWKEYE", "IOWA"};

    private JTextField guessTextField;

    private LetterGuesser letters;

    private JButton guessButton;

    private JTextArea incorrectLetters;

    public Hangman(){
        super("Hangman");
        setLayout(new FlowLayout()); //set layout of JFrame to a flow layout

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel guessingZone = new JPanel(); //guessing zone is where the player inputs their guesses
        guessingZone.setLayout(new BoxLayout(guessingZone, BoxLayout.PAGE_AXIS)); //layout things from top to bottom in guessing zone

        letters = new LetterGuesser(WORDS[(int)(Math.random()*WORDS.length)]); //make a new LetterGuesser from a random word from WORDS

        guessTextField = new JTextField();
        guessButton = new JButton("Guess");
        guessButton.addActionListener(new guessActionListener());
        incorrectLetters = new JTextArea();
        incorrectLetters.setEditable(false);
        incorrectLetters.setLineWrap(true);

        guessingZone.add(incorrectLetters);
        guessingZone.add(letters); //add the LetterGuesser to guessingZone
        guessingZone.add(guessTextField);
        guessingZone.add(guessButton);

        add(guessingZone); //add guessingZone to the JFrame

        pack();
        setVisible(true);
    }

    private class guessActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(!guessTextField.getText().equals("")) {
                String tempString = letters.guess(guessTextField.getText());
                if(tempString.length() != 0) {
                    if(!incorrectLetters.getText().contains(tempString)){
                        incorrectLetters.append(tempString);
                    }
                    //TODO: draw stick figure
                }
                guessTextField.setText("");
                update(getGraphics());
            }
        }
    }
}
