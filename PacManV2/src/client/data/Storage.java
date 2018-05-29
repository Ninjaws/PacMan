package client.data;

import client.networking.Receiver;
import data.ApplicationData;

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
    private ApplicationData applicationData;

    private Receiver receiver;


    public static Storage getInstance() {
        if (instance == null)
            instance = new Storage();

        return instance;
    }

    private Storage() {

        applicationData = new ApplicationData();

        try {
            socket = new Socket("localhost", 666);

            objectToServer = new ObjectOutputStream(socket.getOutputStream());
            objectFromServer = new ObjectInputStream(socket.getInputStream());

            receiver = new Receiver();


            startThreads();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void startThreads() {
        receiver.start();
    }


    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getObjectToServer() {
        return objectToServer;
    }

    public ObjectInputStream getObjectFromServer() {
        return objectFromServer;
    }


    public ApplicationData getApplicationData() {
        return applicationData;
    }

    public void setApplicationData(ApplicationData applicationData) {

        this.applicationData = applicationData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
