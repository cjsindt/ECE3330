import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server that displays text files based on the client's request.
 * @author cjsindt
 */
public class Server {
    /**
     * The output from the server
     */
    private ObjectOutputStream output;
    /**
     * The input from the client
     */
    private ObjectInputStream input;
    /**
     * The server socket
     */
    private ServerSocket server;
    /**
     * The client connection
     */
    private Socket connection;

    /**
     * Runs the server by attempting a connection to a client and waiting for messages from it
     */
    public void runServer(){
        try{
            server = new ServerSocket(23685, 100); //make a new server socket

            while(true){
                try{
                    waitForConnection(); //wait for a connection from a client
                    getStreams(); //get streams from client
                    processConnection(); //process connection between client and server
                }catch(EOFException e){
                    e.printStackTrace();
                }finally{
                    closeConnection();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Connects to the server if available
     * @throws IOException
     */
    private void waitForConnection() throws IOException{
        connection = server.accept();
    }

    /**
     * Gets the streams from the client
     * @throws IOException
     */
    private void getStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
    }

    /**
     * Processes the connection between server and client
     * @throws IOException
     */
    private void processConnection() throws IOException{
        String msg = ""; //message from client

        do{
            try{
                msg = (String) input.readObject(); //read a new message
                try{
                    BufferedReader file = new BufferedReader(new FileReader(new File(getClass().getResource(msg).toURI()))); //try to open a file with the name from client
                    StringBuilder output = new StringBuilder(); //lots of appending so use stringbuilder
                    String s = ""; //temp String
                    while((s = file.readLine()) != null){ //read from file while there is text to read
                        output.append(s);
                        s = "";
                    }
                    sendData(output.toString()); //send the text from the file to the client
                } catch(Exception e){
                    sendData("File not found.");
                }
            }catch(ClassNotFoundException e){
                sendData("File not found.");
            }
        } while(!msg.equals("TERMINATE")); //do this while the client doesn't say TERMINATE
    }

    /**
     * Closes the connection between server and client
     */
    private void closeConnection(){
        try{
            output.close(); //closes each stream and the connection
            input.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Sends the data to the client
     * @param msg   the String to send to the client
     */
    private void sendData(String msg){
        try{
            output.writeObject("SERVER>>> " + msg); //add SERVER>>> to the beginning of messages from it
            output.flush(); //flush the buffer
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
