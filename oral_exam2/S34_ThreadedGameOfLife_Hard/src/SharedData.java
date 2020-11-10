import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

/**
 * A class that stores the data for the game of life in one shared class.
 */
public class SharedData {

    /**
     * The game board
     */
    private boolean[][] data;

    /**
     * The size of the game board in cells
     */
    private final static int size = 40;

    /**
     * The read counter
     */
    private int readCount = 0;

    /**
     * The write counter
     */
    private int writeCount = 0;

    /**
     * Constructor that initializes read and write count to 0 and creates a new game board preloaded with Gosper's Glider Gun.
     */
    public SharedData(){

        readCount = 0;
        writeCount = 0;
        data = new boolean[size][size];

        data[5][1] = true;
        data[6][1] = true;
        data[5][2] = true;
        data[6][2] = true;
        data[3][13] = true;
        data[3][14] = true;
        data[4][12] = true;
        data[5][11] = true;
        data[6][11] = true;
        data[7][11] = true;
        data[8][12] = true;
        data[9][13] = true;
        data[9][14] = true;
        data[6][15] = true;
        data[4][16] = true;
        data[8][16] = true;
        data[5][17] = true;
        data[6][17] = true;
        data[7][17] = true;
        data[6][18] = true;
        data[5][21] = true;
        data[4][21] = true;
        data[3][21] = true;
        data[3][22] = true;
        data[4][22] = true;
        data[5][22] = true;
        data[6][23] = true;
        data[2][23] = true;
        data[2][25] = true;
        data[1][25] = true;
        data[6][25] = true;
        data[7][25] = true;
        data[3][35] = true;
        data[3][36] = true;
        data[4][35] = true;
        data[4][36] = true;
    }

    /**
     * Reads the data and returns a portion of the game board depending on which thread is reading
     * @param offsetX   the thread's X offset on the game board
     * @param offsetY   the threads Y offset on the game board
     * @return  the portion of the game board specific to the thread calling it
     */
    public synchronized boolean[][] readData(int offsetX, int offsetY) {

        //only read if all other threads are done writing
        while(writeCount %4 != 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //create a new temp array
        boolean[][] temp = new boolean[(size/2)+2][(size/2)+2];

        //copy the same middle section for each thread
        for(int i = 1; i < temp.length-1; i++){
            for(int j = 1; j < temp.length-1; j++){
                temp[i][j] = data[i+(offsetX * (size/2))-1][j+(offsetY * (size/2))-1];
            }
            temp[(temp.length - 1 + offsetX) % (temp.length)][i] = data[(size/2) - offsetX][(i-1) + (offsetY * (size/2))];
            temp[i][(temp.length - 1 + offsetY) % (temp.length)] = data[(i-1) + (offsetX * (size/2))][(size/2) - offsetY];
        }

        //temp[((temp.length/2) - 1 + offsetX) % temp.length][i] = data[(size/2) - offsetX][i-1];


        //increment the read counter
        readCount++;

        //if all threads are done reading, notify all threads
        //if(readCount %4 == 0){
            notifyAll();
        //}

        return temp;
    }

    /**
     * Writes the data from a thread to the game board
     * @param offsetX   the thread's X offset on the game board
     * @param offsetY   the thread's Y offset on the game board
     * @param d the data to write
     */
    public synchronized void writeData(int offsetX, int offsetY, boolean[][] d){

        //only write if all threads are done reading
        while(readCount % 4 != 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //copy middle portion of array to data
        for(int i = 1; i < d.length-1; i++){
            for(int j = 1; j < d.length-1; j++){
                data[i+(offsetX * (size/2))-1][j+(offsetY * (size/2))-1] = d[i][j];
            }
        }

        //increment write counter
        writeCount++;

        //if all threads are done writing, notify all threads
        //if(writeCount % 4 == 0){
            notifyAll();
        //}
    }

    /**
     * Returns the size of the game board
     * @return
     */
    public int getGameSize(){
        return size;
    }

}
