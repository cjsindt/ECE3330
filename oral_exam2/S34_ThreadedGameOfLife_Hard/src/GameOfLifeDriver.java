import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class GameOfLifeDriver implements Runnable{
    public static void main(String[] args){
        //GameOfLife g = new GameOfLife();
        NonThreadedGameOfLife g = new NonThreadedGameOfLife();
        JFrame j = new JFrame("Game");
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        j.getContentPane().add(g);
        j.setSize(300,300);
        j.setVisible(true);
        while(true) {
            g.generate();
            g.repaint();
//            try {
//                Thread.sleep(50);
//            } catch(Exception e){
//                Thread.currentThread().interrupt();
//            }
        }

        
    }

    @Override
    public void run() {

    }
}
