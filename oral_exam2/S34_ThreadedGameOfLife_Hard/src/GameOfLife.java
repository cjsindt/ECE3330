import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    public GameOfLife(){
        super("Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,2));
        setPreferredSize(new Dimension(SubGrid.getDimension()*20, (SubGrid.getDimension()*20)+35));
        setResizable(false);

        SubGrid g = new SubGrid();
        SubGrid g2 = new SubGrid();
        SubGrid g3 = new SubGrid();
        SubGrid g4 = new SubGrid();
        getContentPane().add(g);
        getContentPane().add(g2);
        getContentPane().add(g3);
        getContentPane().add(g4);

        setVisible(true);
        pack();
    }
}
