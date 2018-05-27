package server.networking;

import data.ApplicationData;
import data.Conversation;
import server.ServerMain;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientThreadSender extends Thread {

    private ObjectOutputStream objectToClient;

    public ClientThreadSender(ObjectOutputStream objectToClient) {

        this.objectToClient = objectToClient;
    }

    @Override
    public void run() {
        while (true) {
            try {
                objectToClient.reset();
                ApplicationData applicationData = ServerMain.getApplicationData();
                synchronized (applicationData ) {
                    objectToClient.writeObject(applicationData);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
