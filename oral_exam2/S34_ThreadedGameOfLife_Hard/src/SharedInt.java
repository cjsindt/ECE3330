import java.security.SecureRandom;

/**
 * A shared int class between each subgrid to tell when they are able to continue with their operations.
 */
public class SharedInt {

    /**
     * A random number generator
     */
    private static final SecureRandom generator = new SecureRandom();

    /**
     * The shared integer itself
     */
    private int a = 0;

    /**
     * Adds one to the integer after sleeping the thread
     */
    public synchronized void add(){
        try {
            // put thread to sleep for 0-499 milliseconds
            Thread.sleep(generator.nextInt(500));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt(); // re-interrupt the thread
        }
        a++;
    }

    /**
     * Returns the interger value shared
     * @return  the shared integer's value
     */
    public int getS(){
        return a;
    }
}
