import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameOfLifeDriver implements Runnable{
    public static void main(String[] args) throws InterruptedException{
        //GameOfLife g = new GameOfLife();
        //NonThreadedGameOfLife g = new NonThreadedGameOfLife();
        JFrame j = new JFrame("Game");
        j.setLayout(new GridLayout(2,2));
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int size = SubGrid.getSizeOfSubgrid();
        j.getContentPane().setPreferredSize(new Dimension(size*20, size*20));
        j.setResizable(false);
        j.setVisible(true);

        ExecutorService executorService = Executors.newCachedThreadPool();

        SubGrid g0 = new SubGrid(0);
        SubGrid g1 = new SubGrid(1);
        SubGrid g2 = new SubGrid(2);
        SubGrid g3 = new SubGrid(3);

        //assign neighbors
        g0.setNeighborOne(g1);
        g0.setNeighborTwo(g2);
        g0.setOtherNeighbor(g3);

        g1.setNeighborOne(g0);
        g1.setNeighborTwo(g3);
        g1.setOtherNeighbor(g2);

        g2.setNeighborOne(g0);
        g2.setNeighborTwo(g3);
        g2.setOtherNeighbor(g1);

        g3.setNeighborOne(g1);
        g3.setNeighborTwo(g2);
        g3.setOtherNeighbor(g0);

        j.getContentPane().add(g0);
        j.getContentPane().add(g1);
        j.getContentPane().add(g2);
        j.getContentPane().add(g3);

        j.pack();

        executorService.execute(g0);
        executorService.execute(g1);
        executorService.execute(g2);
        executorService.execute(g3);
//


        
    }

    @Override
    public void run() {

    }
}
