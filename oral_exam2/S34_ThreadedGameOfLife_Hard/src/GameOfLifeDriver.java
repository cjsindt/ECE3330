import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameOfLifeDriver implements Runnable{
    public static void main(String[] args) throws InterruptedException{
        //GameOfLife g = new GameOfLife();
        NonThreadedGameOfLife g = new NonThreadedGameOfLife();
        JFrame j = new JFrame("Game");
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        j.getContentPane().add(g);
        j.setSize(300,300);
        j.setVisible(true);

        ExecutorService executorService = Executors.newCachedThreadPool();

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

        j.getContentPane().add(g0);
        j.getContentPane().add(g1);
        j.getContentPane().add(g2);
        j.getContentPane().add(g3);

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
