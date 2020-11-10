import java.security.SecureRandom;

/**
 * A shared int class between each subgrid to tell when they are able to continue with their operations.
 */
public class SharedData {

    private boolean[][] data;

    private final static int size = 40;
    /**
     * A random number generator
     */
    private static final SecureRandom generator = new SecureRandom();

    /**
     * The shared integer itself
     */
    private int readCount = 0;

    /**
     *
     */
    private int writeCount = 0;

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

    public synchronized boolean[][] readData(int offsetX, int offsetY) {

        //only read if all other threads are done writing
        if(writeCount %4 != 0){
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
        }

        temp[((temp.length/2) - 1 + offsetX) % temp.length] = data[(size/2) - offsetX].clone();


        //increment the read counter
        readCount++;

        //if all threads are done reading, notify all threads
        if(readCount %4 == 0){
            notifyAll();
        }

        return temp;
    }

    public synchronized void writeData(int offsetX, int offsetY, boolean[][] d){

        //only write if all threads are done reading
        if(readCount % 4 != 0){
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
        if(writeCount % 4 == 0){
            notifyAll();
        }
    }

    public int getSize(){
        return size;
    }

    /**
     * Adds one to the integer after sleeping the thread
     */
    public synchronized void addA(){
        try {
            // put thread to sleep for 0-499 milliseconds
            Thread.sleep(generator.nextInt(500));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt(); // re-interrupt the thread
        }
        readCount++;
    }

    public synchronized void addB(){
        try {
            // put thread to sleep for 0-499 milliseconds
            Thread.sleep(generator.nextInt(500));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt(); // re-interrupt the thread
        }
        writeCount++;
    }

    /**
     * Returns the interger value shared
     * @return  the shared integer's value
     */
    public int getA(){
        return readCount;
    }

    public int getB() { return writeCount; }
}
