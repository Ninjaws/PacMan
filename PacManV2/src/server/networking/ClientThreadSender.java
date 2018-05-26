package server.networking;

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
                Conversation conversation = ServerMain.getConversation();
                synchronized (conversation ) {
                    objectToClient.writeObject(conversation);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
