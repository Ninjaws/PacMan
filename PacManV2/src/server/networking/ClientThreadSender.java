package server.networking;

import server.data.UserThread;

import java.io.IOException;

public class ClientThreadSender extends Thread {

    private UserThread thread;

    public ClientThreadSender(UserThread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        while (true) {
            try {
                thread.getObjectToClient().reset();
                thread.getObjectToClient().writeObject(ThreadManager.getInstance().getConversation());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}