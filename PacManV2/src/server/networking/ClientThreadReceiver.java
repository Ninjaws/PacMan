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

                switch (command) {
                    case "user_add":
                        String userToAdd = (String) objectFromClient.readObject();
                        ServerMain.getApplicationData().addUser(new User(userToAdd));
                        break;

                    case "user_remove":
                        String userToRemove = (String) objectFromClient.readObject();
                        //TODO: Remove user
                        break;

                    case "lobby_create":
                        String lobbyToCreate = (String) objectFromClient.readObject();
                        ServerMain.getApplicationData().getLauncherData().addLobby(new LobbyData(lobbyToCreate));
                        break;

                    case "lobby_remove":
                        String lobbyToRemove = (String) objectFromClient.readObject();
                        //TODO: Remove lobby
                        break;

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
