import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * A client that sends a specified text file name to the server and the server displays the text in that file if found
 */
public class Client extends JFrame {
    /**
     * The JTextField that the user types in
     */
    private JTextField enterField;
    /**
     * The display area for the server to display messages
     */
    private JTextArea displayArea;
    /**
     * The output from the client
     */
    private ObjectOutputStream output;
    /**
     * The input from the server
     */
    private ObjectInputStream input;
    /**
     * The message sent between client and server
     */
    private String msg = "";
    /**
     * The IP of the server
     */
    private String server;
    /**
     * The client socket
     */
    private Socket client;

    /**
     * Generates a GUI for the client to display it's messages to the server
     * @param host  the IP of the server
     */
    public Client(String host){
        super("Client");

        server = host; //set the server to the IP given

        //generate GUI components and add listeners
        enterField = new JTextField();
        enterField.setEditable(false);
        enterField.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        sendData(ae.getActionCommand());
                        enterField.setText("");
                    }
                }
        );

        //add it to the JFrame and set visible to true
        add(enterField, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(300, 150);
        setVisible(true);
    }

    /**
     * Runs the client side of the program
     */
    public void runClient(){
        try{
            connectToServer(); //try to connect to server
            getStreams();   //get streams from server
            processConnection();    //process connection between server and client
        }catch(EOFException eof){
            displayMessage("\nClient terminated connection.");
        }catch(IOException io){
            io.printStackTrace();
        }finally{
            closeConnection();
        }
    }

    /**
     * Establishes a connection to the server
     */
    private void connectToServer() throws IOException{
        displayMessage("Attempting Connection\n");

        client = new Socket(InetAddress.getByName(server), 23685);

        displayMessage("Connected to: " + client.getInetAddress().getHostName());
    }

    /**
     * Gets the streams from the server
     * @throws IOException
     */
    private void getStreams() throws IOException{
        output = new ObjectOutputStream(client.getOutputStream()); //get output stream from server
        output.flush(); //flush buffer

        input = new ObjectInputStream(client.getInputStream());

        displayMessage("\nGot I/O streams\n");
    }

    /**
     * Processes the connection between server and client
     * @throws IOException
     */
    private void processConnection() throws IOException{
        setTextFieldEditable(true); //make text field editable

        do{
            try{
                msg= (String) input.readObject(); //try to read from the input from user
                displayMessage("\n" + msg);
            }catch(ClassNotFoundException cnfe){
                displayMessage("\nUnknown object type received");
            }
        }while(true);
    }

    /**
     * Closes the connection to the server
     */
    private void closeConnection(){
        displayMessage("\nClosing connection");
        setTextFieldEditable(false);

        //attempt to close streams and socket
        try{
            output.close();
            input.close();
            client.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * Sends the message from the user to the server and displays it to the client
     * @param msg   the message to send to the server
     */
    private void sendData(String msg){
        try{
            output.writeObject(msg); //write to the stream the user input
            output.flush(); //flush the buffer
            displayMessage("\nCLIENT>>> " + msg); //display message on client screen
        }catch(IOException ioe){
            displayArea.append("\nError writing object.");
        }
    }

    /**
     * Displays message on client's screen
     * @param displayMsg    the message to display
     */
    private void displayMessage(final String displayMsg){
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run(){
                        displayArea.append(displayMsg);
                    }
                }
        );
    }

    /**
     * Sets the input text field to be editable or non-editable
     * @param edit  true if editable, false otherwise
     */
    private void setTextFieldEditable(final boolean edit){
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run(){
                        enterField.setEditable(edit);
                    }
                }
        );
    }
}
