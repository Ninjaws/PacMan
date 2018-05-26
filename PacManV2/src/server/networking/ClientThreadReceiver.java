package server.networking;

import data.ApplicationData;
import data.Message;
import data.User;
import server.ServerMain;

import java.io.ObjectInputStream;

public class ClientThreadReceiver extends Thread {

    private ObjectInputStream objectFromClient;

    public ClientThreadReceiver(ObjectInputStream objectFromClient) {

        this.objectFromClient = objectFromClient;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String command = (String) objectFromClient.readObject();

                if (command.equals("message")) {
                    Message message = (Message) objectFromClient.readObject();
                    //  System.out.println(message);
                    // ThreadManager.getInstance().getConversation().addMessage(message);
                    ServerMain.getConversation().addMessage(message);
                }
                else if(command.equals("user")){
                    String string = (String) objectFromClient.readObject();
                    ApplicationData.getInstance().getUsers().add(new User(string));
                }
                else if(command.equals("test")){
                    String string = (String) objectFromClient.readObject();
                    System.out.println(string);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
