import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executor;

public class GameOfLife extends JFrame{

    public GameOfLife(){
        super("Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,2));
        setPreferredSize(new Dimension(SubGrid.getDimension()*20, (SubGrid.getDimension()*20)+35));
        setResizable(false);

        SubGrid g0 = new SubGrid(0);
        SubGrid g1 = new SubGrid(1);
        SubGrid g2 = new SubGrid(2);
        SubGrid g3 = new SubGrid(3);

        //assign neighbors
        g0.setNeighborOne(g1);
        g0.setNeighborTwo(g2);
        g1.setNeighborOne(g0);
        g1.setNeighborTwo(g3);
        g2.setNeighborOne(g0);
        g2.setNeighborTwo(g3);
        g3.setNeighborOne(g1);
        g3.setNeighborTwo(g2);

        getContentPane().add(g0);
        getContentPane().add(g1);
        getContentPane().add(g2);
        getContentPane().add(g3);

        setVisible(true);
        pack();
    }

}
