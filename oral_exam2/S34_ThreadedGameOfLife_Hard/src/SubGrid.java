import javax.swing.*;
import java.awt.*;

public class SubGrid extends JPanel implements Runnable{

    private boolean[][] prevState;

    private boolean[][] nextState;

    private static final int size = 16;

    private final int position;

    private SubGrid neighborOne;

    private SubGrid neighborTwo;

    private boolean isDone = false;

    @Override
    public void run() {
        this.generate();
        while(!areNeighborsDone()){

        }
        this.updateGeneration();
        this.repaint();
    }

    public SubGrid(int pos){
        super();
        position = pos;
        prevState = new boolean[size+2][size+2]; //add a buffer ring to make comparisons easier
        nextState = new boolean[size+2][size+2];

        for(int i = 1; i < size; i++){
            for(int j = (i%2)+1; j < size; j+=2){
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

        //loop through each square in the squares 2d array except buffer ring
        for(int i = 1; i < size+1; i++){
            for(int j = 1; j < size+1; j++){
                if(prevState[i][j]) {
                    g.fillRect(i * 10, j * 10, 10,  10); //draw squares everywhere there is a 1
                }
            }
        }
    }

    public static int getDimension(){
        return size;
    }

    public int getPosition(){
        return position;
    }

    public void setNeighborOne(SubGrid s){
        neighborOne = s;
    }

    public void setNeighborTwo(SubGrid s){
        neighborTwo = s;
    }

    public boolean[][] getPrevState(){
        return prevState;
    }

    public synchronized void generate() {
        int numNeighbors = 0; //number of neighbors around a cell
        switch (position) {
            case 0:
                for (int i = 1; i < size + 1; i++) {
                    prevState[size + 1][i] = neighborTwo.getPrevState()[1][i]; //the bottom row equals the top row of the SubGrid below it
                    prevState[i][size + 1] = neighborOne.getPrevState()[i][1]; //column on right equals the column on the left of it's left neighbor1
                }
                break;
            case 1:
                for (int i = 1; i < size + 1; i++) {
                    prevState[i][0] = neighborOne.getPrevState()[i][size + 1];
                    prevState[size + 1][i] = neighborTwo.getPrevState()[1][i];
                }
                break;
            case 2:
                for (int i = 1; i < size + 1; i++) {
                    prevState[0][i] = neighborOne.getPrevState()[size + 1][i];
                    prevState[i][size + 1] = neighborTwo.getPrevState()[i][1];
                }
                break;
            case 3:
                for (int i = 1; i < size + 1; i++) {
                    prevState[0][i] = neighborOne.getPrevState()[size + 1][i];
                    prevState[i][0] = neighborTwo.getPrevState()[i][size + 1];
                }
        }

        //compare neighbors
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                numNeighbors = (prevState[i - 1][j - 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (prevState[i - 1][j]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (prevState[i - 1][j + 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (prevState[i][j - 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (prevState[i][j + 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (prevState[i + 1][j - 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (prevState[i + 1][j]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (prevState[i + 1][j + 1]) ? numNeighbors + 1 : numNeighbors;

                //rules of the game of life
                if (prevState[i][j] && (numNeighbors == 2 || numNeighbors == 3)) {
                    nextState[i][j] = true;
                } else nextState[i][j] = !prevState[i][j] && numNeighbors == 3;

                numNeighbors = 0;
            }
        }

        isDone = true;
    }

    public void updateGeneration(){
        prevState = nextState;
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean areNeighborsDone(){
        return isDone() && neighborOne.isDone() && neighborTwo.isDone();
    }

}

