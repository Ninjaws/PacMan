package client.networking;

import client.data.Storage;

public class Sender implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Storage.getInstance().getObjectToServer().reset();
                Storage.getInstance().getObjectToServer().writeObject(Storage.getInstance().getConversation());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}