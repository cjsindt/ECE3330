import javax.swing.*;
import java.awt.*;

/**
 * Updated version of SubGrid that leaves all the data to be stored in the SharedData class. Handles the updating of data and drawing to screen.
 */
public class SubCell extends JPanel implements Runnable {

    /**
     * The data to be used to paint and generate from
     */
    private boolean[][] data;

    /**
     * The updated data to be sent back to the shared data class
     */
    private boolean[][] dataBuffer;

    /**
     * The X offset of the cell
     */
    private int offsetX;

    /**
     * The Y offset of the cell
     */
    private int offsetY;

    /**
     * The shared data between threads
     */
    private SharedData share;

    /**
     * Constructor that takes a cell's X and Y offset, and a sharedData class and makes a new blank cell
     * @param x the cell's X offset
     * @param y the cell's Y offset
     * @param s the SharedData class
     */
    public SubCell(int x, int y, SharedData s){
        super();
        offsetX = x;
        offsetY = y;
        share = s;
        data = new boolean[(share.getGameSize()/2)+2][(share.getGameSize()/2)+2];
        dataBuffer = new boolean[(share.getGameSize()/2)+2][(share.getGameSize()/2)+2];

        setSize(share.getGameSize()/2, share.getGameSize()/2);
        setBackground(Color.WHITE);
    }

    /**
     * Runs the thread. This method reads from the SharedData, repaints to the screen, then generates a new dataset and sends it to SharedData
     */
    @Override
    public void run() {

        while(true) {
            data = share.readData(offsetX, offsetY).clone();
            repaint();
            generate();
            share.writeData(offsetX, offsetY, dataBuffer);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Paints to the screen the current cell's state
     * @param g graphics object to draw
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //loop through each square in the squares 2d array except buffer ring
        for(int i = 1; i < data.length-1; i++){
            for(int j = 1; j < data.length-1; j++){
                if(data[i][j]) {
                    g.fillRect((j-1) * 10, (i-1) * 10, 10,  10); //draw squares everywhere there is a 1
                }
            }
        }
    }

    /**
     * Generates a new generation based on the cell's current state
     */
    public void generate(){
        int numNeighbors = 0;

        for(int i = 1; i < data.length-1; i++){
            for(int j = 1; j < data.length-1; j++){
                //count the neighbors that are alive
                numNeighbors = (data[i - 1][j - 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (data[i - 1][j]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (data[i - 1][j + 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (data[i][j - 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (data[i][j + 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (data[i + 1][j - 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (data[i + 1][j]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (data[i + 1][j + 1]) ? numNeighbors + 1 : numNeighbors;

                //rules of the game of life
                if (data[i][j] && (numNeighbors == 2 || numNeighbors == 3)) {
                    dataBuffer[i][j] = true;
                } else dataBuffer[i][j] = !data[i][j] && numNeighbors == 3;

                numNeighbors = 0;
            }
        }
    }
}
