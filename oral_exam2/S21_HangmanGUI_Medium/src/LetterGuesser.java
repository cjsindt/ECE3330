import javax.swing.*;
import java.awt.*;

public class LetterGuesser extends JPanel {

    private JLabel[] letters;

    private String word;

    public LetterGuesser(String w){
        super(); //super constructor
        word = w;

        setLayout(new GridLayout(1, word.length())); //make the layout a 1 by length grid

        letters = new JLabel[word.length()]; //a new array of JLabels to show letters to guess

        //set the text of each JLabel to a blank and add it to the JPanel
        for(int i = 0; i < word.length(); i++){
            letters[i] = new JLabel("_ ");
            add(letters[i]);
        }
    }

    public String guess(String s){
        s = s.toUpperCase();
        if(s.length() == 1){
            char c = s.charAt(0);
            try{
                letters[word.indexOf(c)].setText(c + "");
                return "";
            } catch (Exception e) {
                return String.valueOf(c);
            }
        } else if(s.equals(word)){
            for(int i = 0; i < word.length(); i++){
                letters[i].setText(word.charAt(i) + "");
            }
            return "";
        } else {
            return "";
        }
    }
}
