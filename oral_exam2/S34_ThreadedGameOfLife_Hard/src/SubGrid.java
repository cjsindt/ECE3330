import javax.swing.*;
import java.awt.*;

/**
 * The SubGrids of the Threaded Game of Life board. Each subGrid is a variable length and will run on it's own thread.
 */
public class SubGrid extends JPanel implements Runnable{

    /**
     * The current (previous) state of the game.
     */
    private boolean[][] prevState;

    /**
     * The Next state of the game (used as a buffer)
     */
    private boolean[][] nextState;

    /**
     * The size of a subgrid
     */
    private static final int size = 20;

    /**
     * The relative position of a subgrid
     */
    private final int position;

    /**
     * A subgrid's first neighbor
     */
    private SubGrid neighborOne;

    /**
     * A subgrid's second neighbor
     */
    private SubGrid neighborTwo;

    /**
     * The shared int between the different subgrids
     */
    private final SharedInt share;


    /**
     * Runs the actual game in a thread
     */
    @Override
    public void run() {
        //while(true){
            generate();
            while(share.getS()%4 != 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            updateGeneration();
            repaint();
        }
    //}

    /**
     * Constructor that takes the subgrid's relative position as well as the shared int class for it.
     * @param pos   the relative position
     * @param s the shared int between all subgrids
     */
    public SubGrid(int pos, SharedInt s){
        super();
        position = pos;
        prevState = new boolean[size+2][size+2]; //add a buffer ring to make comparisons easier
        nextState = new boolean[size+2][size+2];

        share = s;

        if(pos == 0){
            prevState[5][1] = true;
            prevState[6][1] = true;
            prevState[5][2] = true;
            prevState[6][2] = true;
            prevState[3][13] = true;
            prevState[3][14] = true;
            prevState[4][12] = true;
            prevState[5][11] = true;
            prevState[6][11] = true;
            prevState[7][11] = true;
            prevState[8][12] = true;
            prevState[9][13] = true;
            prevState[9][14] = true;
            prevState[6][15] = true;
            prevState[4][16] = true;
            prevState[8][16] = true;
            prevState[5][17] = true;
            prevState[6][17] = true;
            prevState[7][17] = true;
            prevState[6][18] = true;
        }

        if(pos == 1){
            prevState[5][1] = true;
            prevState[4][1] = true;
            prevState[3][1] = true;
            prevState[3][2] = true;
            prevState[4][2] = true;
            prevState[5][2] = true;
            prevState[6][3] = true;
            prevState[2][3] = true;
            prevState[2][5] = true;
            prevState[1][5] = true;
            prevState[6][5] = true;
            prevState[7][5] = true;
            prevState[3][15] = true;
            prevState[3][16] = true;
            prevState[4][15] = true;
            prevState[4][16] = true;
        }
//        for(int i = 1; i < size+1; i++){
//            for(int j = (i%2)+1; j < size+1; j+=2){
//                //if(i == j){
//                    prevState[i][j] = true;
//                //}
//            }
//        }

        setSize(size*10, size*10);
        setBackground(Color.WHITE);
    }

    /**
     * Paints cells on each subgrid depending on it's state
     * @param g graphics object used to draw
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //loop through each square in the squares 2d array except buffer ring
        for(int i = 1; i < size+1; i++){
            for(int j = 1; j < size+1; j++){
                if(prevState[i][j]) {
                    g.fillRect((j-1) * 10, (i-1) * 10, 10,  10); //draw squares everywhere there is a 1
                }
            }
        }
    }

    /**
     * Returns the dimension (size) of the subgrid
     * @return  the size of the subgrid
     */
    public static int getDimension(){
        return size;
    }

    /**
     * Returns the relative position of the subgrid
     * @return  the position of the subgrid
     */
    public int getPosition(){
        return position;
    }

    /**
     * Sets the first neighbor of the subgrid
     * @param s the first neighbor
     */
    public void setNeighborOne(SubGrid s){
        neighborOne = s;
    }

    /**
     * Sets the second neighbor of the subgrid
     * @param s the second neighbor
     */
    public void setNeighborTwo(SubGrid s){
        neighborTwo = s;
    }

    /**
     * Returns the previous state of the board
     * @return  the previous state of the board
     */
    public boolean[][] getPrevState(){
        return prevState;
    }

    /**
     * Generates the next generation/next board state for a subgrid depending on it's current cells as well as it's neighboring cells
     */
    public void generate() {
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

        share.add();

    }

    /**
     * Updates the buffer for the subgrid
     */
    public void updateGeneration(){
        prevState = nextState;
    }

    /**
     * Returns the size of the subgrid
     * @return  the size of the subgrid
     */
    public static int getSizeOfSubgrid(){
        return size;
    }

}

