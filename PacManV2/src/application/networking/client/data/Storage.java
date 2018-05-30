package application.networking.client.data;

import application.game.data.Game;
import application.networking.client.networking.Receiver;
import application.launcher.data.ApplicationData;

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
    private application.testgame.data.ApplicationData appDataTest;

    private Receiver receiver;


    public static Storage getInstance() {
        if (instance == null)
            instance = new Storage();

        return instance;
    }

    private Storage() {

        applicationData = new ApplicationData();
        appDataTest = new application.testgame.data.ApplicationData();

        try {
            socket = new Socket("localhost", 9595); //145.49.52.133

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

    public application.testgame.data.ApplicationData getAppDataTest() {
        return appDataTest;
    }

    public void setAppDataTest(application.testgame.data.ApplicationData appDataTest) {
        this.appDataTest = appDataTest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
