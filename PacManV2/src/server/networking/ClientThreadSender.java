package server.networking;

import data.ApplicationData;
import server.ServerMain;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThreadSender extends Thread {

    private ObjectOutputStream objectToClient;
    private Socket socket;

    public ClientThreadSender(ObjectOutputStream objectToClient, Socket socket) {
        this.objectToClient = objectToClient;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            objectToClient.reset();
            ApplicationData applicationData = ServerMain.getApplicationData();
            synchronized (applicationData) {
                objectToClient.writeObject(applicationData);
            }
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
            }

        }
    }
}
