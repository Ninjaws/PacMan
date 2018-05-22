package client.networking;

import client.data.Storage;
import data.Conversation;

public class Receiver implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Conversation tempConv = (Conversation) Storage.getInstance().getObjectFromServer().readObject();
                Storage.getInstance().setConversation(tempConv);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}