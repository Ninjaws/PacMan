package server.networking;

import data.Message;
import server.data.UserThread;

public class ClientThreadReceiver extends Thread {

    private UserThread thread;

    public ClientThreadReceiver(UserThread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = (Message) thread.getObjectFromClient().readObject();
                System.out.println(message);
                ThreadManager.getInstance().getConversation().addMessage(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}