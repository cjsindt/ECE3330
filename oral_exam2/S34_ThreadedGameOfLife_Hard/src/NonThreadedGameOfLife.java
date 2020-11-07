import javax.swing.*;
import java.awt.*;

/**
 * A nonthreaded version of the Game of Life pre-programmed to show gosper's glider gun.
 */
public class NonThreadedGameOfLife extends JPanel{

    public static void main(String[] args){
        NonThreadedGameOfLife g = new NonThreadedGameOfLife();
        JFrame j = new JFrame();
        j.getContentPane().add(g);
        j.setVisible(true);
        j.pack();
        while(true) {
            g.generate();
            g.repaint();
        }
    }

    /**
     * The size of the panel in cells
     */
    private final int size = 60;

    /**
     * Boolean representation of the game
     */
    private boolean[][] p;

    /**
     * The next state of the game
     */
    private boolean[][] nextState;

    /**
     * Constructor that programs Gosper's glider gun into the JPanel and makes a new JPanel
     */
    public NonThreadedGameOfLife(){
        super();
        p = new boolean[size+2][size+2];
        nextState = new boolean[size+2][size+2];

        p[5][1] = true;
        p[6][1] = true;
        p[5][2] = true;
        p[6][2] = true;
        p[3][13] = true;
        p[3][14] = true;
        p[4][12] = true;
        p[5][11] = true;
        p[6][11] = true;
        p[7][11] = true;
        p[8][12] = true;
        p[9][13] = true;
        p[9][14] = true;
        p[6][15] = true;
        p[4][16] = true;
        p[8][16] = true;
        p[5][17] = true;
        p[6][17] = true;
        p[7][17] = true;
        p[6][18] = true;
        p[5][21] = true;
        p[4][21] = true;
        p[3][21] = true;
        p[3][22] = true;
        p[4][22] = true;
        p[5][22] = true;
        p[6][23] = true;
        p[2][23] = true;
        p[2][25] = true;
        p[1][25] = true;
        p[6][25] = true;
        p[7][25] = true;
        p[3][35] = true;
        p[3][36] = true;
        p[4][35] = true;
        p[4][36] = true;

        setPreferredSize(new Dimension(size, size));
        setBackground(Color.WHITE);
    }

    /**
     * Paints cells onto the JPanel depending on what cells are alive
     * @param g graphics object to draw with
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //loop through each square in the squares 2d array except buffer ring
        for(int i = 1; i < size+1; i++){
            for(int j = 1; j < size+1; j++){
                if(p[i][j]) {
                    g.fillRect(j * 10, i * 10, 10,  10); //draw squares everywhere there is a 1
                }
            }
        }
        //generate();
    }

    /**
     * Generates the next iteration of the game depending on the current cell states.
     */
    public void generate() {
        int numNeighbors = 0;
        //compare neighbors
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                numNeighbors = (p[i - 1][j - 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (p[i - 1][j]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (p[i - 1][j + 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (p[i][j - 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (p[i][j + 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (p[i + 1][j - 1]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (p[i + 1][j]) ? numNeighbors + 1 : numNeighbors;
                numNeighbors = (p[i + 1][j + 1]) ? numNeighbors + 1 : numNeighbors;

                //rules of the game of life
                if (p[i][j] && (numNeighbors == 2 || numNeighbors == 3)) {
                    nextState[i][j] = true;
                } else nextState[i][j] = !p[i][j] && numNeighbors == 3;

                numNeighbors = 0;
            }
        }
        //System.out.println("Done!");

        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                p[i][j] = nextState[i][j];
                nextState[i][j] = false;
            }
        }
    }
}
