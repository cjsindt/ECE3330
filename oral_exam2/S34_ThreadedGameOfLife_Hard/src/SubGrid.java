import javax.swing.*;
import java.awt.*;

public class SubGrid extends JPanel implements Runnable{

    private boolean[][] prevState;

    private boolean[][] nextState;

    private static final int size = 16;

    private final int position;

    @Override
    public void run() {

    }

    public SubGrid(int pos){
        super();
        position = pos;
        prevState = new boolean[size][size];

        for(int i = 0; i < size; i++){
            for(int j = i%2; j < size; j+=2){
                //if(i == j){
                    prevState[i][j] = true;
                //}
            }
        }

        setSize(size, size);
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //loop through each square in the squares 2d array
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(prevState[i][j]) {
                    g.fillRect(i * 10, j * 10, 10,  10); //draw squares everywhere there is a 1
                }
            }
        }
    }

    public static int getDimension(){
        return size;
    }
}
