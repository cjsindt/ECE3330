import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main driver class for the game of life. Creates and runs the threads and generates a JFrame to show the operations.
 */
public class GameOfLifeDriver{
    public static void main(String[] args) throws InterruptedException{
        //GameOfLife g = new GameOfLife();
        //NonThreadedGameOfLife g = new NonThreadedGameOfLife();
        JFrame j = new JFrame("Game");
        j.setLayout(new GridLayout(2,2));
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        j.getContentPane().setPreferredSize(new Dimension(400, 400));
        j.setResizable(false);
        j.setVisible(true);

        //make a new thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();

        //new shared int class for the subgrids
        SharedData s = new SharedData();

        SubCell g0 = new SubCell(0, 0, s);
        SubCell g1 = new SubCell(0, 1, s);
        SubCell g2 = new SubCell(1, 0, s);
        SubCell g3 = new SubCell(1, 1, s);

        //make the new subgrids
//        SubGrid g0 = new SubGrid(0, s);
//        SubGrid g1 = new SubGrid(1, s);
//        SubGrid g2 = new SubGrid(2, s);
//        SubGrid g3 = new SubGrid(3, s);

        //assign neighbors
//        g0.setNeighborOne(g1);
//        g0.setNeighborTwo(g2);
//
//
//        g1.setNeighborOne(g0);
//        g1.setNeighborTwo(g3);
//
//
//        g2.setNeighborOne(g0);
//        g2.setNeighborTwo(g3);
//
//
//        g3.setNeighborOne(g1);
//        g3.setNeighborTwo(g2);


        //add everything to the JFrame
        j.getContentPane().add(g0);
        j.getContentPane().add(g1);
        j.getContentPane().add(g2);
        j.getContentPane().add(g3);

        j.pack();

        //run the threads
        executorService.execute(g0);
        executorService.execute(g1);
        executorService.execute(g2);
        executorService.execute(g3);

        executorService.shutdown();

    }

}
