import javax.swing.*;
import java.awt.*;

public class SubCell extends JPanel implements Runnable {

    private boolean[][] data;

    private boolean[][] dataBuffer;

    private int offsetX;

    private int offsetY;

    private SharedData share;

    public SubCell(int x, int y, SharedData s){
        super();
        offsetX = x;
        offsetY = y;
        share = s;
        data = new boolean[(share.getSize()/2)+2][(share.getSize()/2)+2];
        dataBuffer = new boolean[(share.getSize()/2)+2][(share.getSize()/2)+2];

        setSize(share.getSize()/2, share.getSize()/2);
        setBackground(Color.WHITE);
    }

    public int getOffsetX(){
        return offsetX;
    }

    public int getOffsetY(){
        return offsetY;
    }

    @Override
    public void run() {

        while(true) {
            data = share.readData(offsetX, offsetY).clone();
            repaint();
            generate();
            share.writeData(offsetX, offsetY, dataBuffer);
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }

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

    public void generate(){
        int numNeighbors = 0;
        for(int i = 1; i < data.length-1; i++){
            for(int j = 1; j < data.length-1; j++){
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
