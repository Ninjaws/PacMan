package server.networking;

import data.ApplicationData;
import data.LobbyData;
import data.Message;
import data.User;
import server.ServerMain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThreadReceiver extends Thread {

    private ObjectInputStream objectFromClient;
    private Socket socket;

    public ClientThreadReceiver(ObjectInputStream objectFromClient, Socket socket) {
        this.objectFromClient = objectFromClient;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String command = (String) objectFromClient.readObject();
                if (command.equals("message")) {

                    String string = (String) objectFromClient.readObject();
                    System.out.println("Message: " + string);
                //    Message message = (Message) objectFromClient.readObject();

                  //  ServerMain.getConversation().addMessage(message);
                }
                else if(command.equals("user")){
                    String string = (String) objectFromClient.readObject();
                    ServerMain.getApplicationData().addUser(new User(string));
                    System.out.println(ServerMain.getApplicationData().getUsers());
                    System.out.println("User: " + string);
                }
                else if(command.equals("lobby")){
                    String string = (String) objectFromClient.readObject();
                    ServerMain.getApplicationData().getLauncherData().addLobby(new LobbyData(string));
                    System.out.println("Lobby: " + string);
                  //  System.out.println(ServerMain.getApplicationData().getLauncherData().getLobbies().size());
                }
                else if(command.equals("test")){
                    String string = (String) objectFromClient.readObject();
                    System.out.println(string);
                }

            } catch (Exception e) {
                //e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException e1) {
                    //e1.printStackTrace();
                }
                break;
            }
        }
    }
}
