package client.data;

import client.networking.Receiver;
import client.networking.Sender;
import data.Conversation;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Placeholder class to store the conversation.
 * Change to adapt to current screen (using states)
 */
public class Storage {
    private static Storage instance;

    private Socket socket;
    private ObjectOutputStream objectToServer;
    private ObjectInputStream objectFromServer;

    private String username;
    private Conversation conversation;

    private Sender sender;
    private Receiver receiver;


    public static Storage getInstance(){
        if(instance == null)
            instance = new Storage();

        return instance;
    }

    private Storage(){

        conversation = new Conversation();

        try {
            socket = new Socket("localhost", 8313);

            objectToServer = new ObjectOutputStream(socket.getOutputStream());
            objectFromServer = new ObjectInputStream(socket.getInputStream());

            sender = new Sender();
            receiver = new Receiver();


        } catch (Exception e) {
            e.printStackTrace();
        }





    }

    public void startThreads(){
        //   sender.run();
        receiver.run();
    }


    public Socket getSocket() {
        return socket;
    }

    public synchronized ObjectOutputStream getObjectToServer() {
        return objectToServer;
    }

    public synchronized ObjectInputStream getObjectFromServer() {
        return objectFromServer;
    }

    public synchronized Conversation getConversation() {
        return conversation;
    }

    public synchronized void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}