package server.networking;

import data.launcher.LobbyData;
import data.launcher.Message;
import data.launcher.User;
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

                        //In case the user quits the program while still in a lobby
                        //This will make sure the player that left is not occupying a spot
                        for (LobbyData lobbyData : ServerMain.getApplicationData().getLauncherData().getLobbies()) {
                            synchronized (lobbyData) {
                                if (lobbyData.getPlayers().contains(userToRemove))
                                    lobbyData.removePlayer(userToRemove);

                            }}

                        ServerMain.getApplicationData().removeUser(userToRemove);
                        break;

                    case "lobby_create":
                        String creatingUser = (String) objectFromClient.readObject();
                        String lobbyToCreate = (String) objectFromClient.readObject();
                        ServerMain.getApplicationData().getLauncherData().addLobby(new LobbyData(lobbyToCreate));
                        ServerMain.getApplicationData().getLauncherData().getLobby(lobbyToCreate).addPlayer(creatingUser);
                        break;

                    case "lobby_remove":
                        String lobbyToRemove = (String) objectFromClient.readObject();
                        ServerMain.getApplicationData().getLauncherData().removeLobby(lobbyToRemove);
                        break;

                    case "lobby_join":
                        String joiningUser = (String) objectFromClient.readObject();
                        String lobbyToJoin = (String) objectFromClient.readObject();
                        ServerMain.getApplicationData().getLauncherData().getLobby(lobbyToJoin).addPlayer(joiningUser);
                        break;

                    case "lobby_leave":
                        String leavingUser = (String) objectFromClient.readObject();
                        String lobbyToLeave = (String) objectFromClient.readObject();
                        ServerMain.getApplicationData().getLauncherData().getLobby(lobbyToLeave).removePlayer(leavingUser);
                        break;

                    case "message_send":
                        Message messageToAdd = (Message) objectFromClient.readObject();
                        String lobbyName = (String) objectFromClient.readObject();
                        ServerMain.getApplicationData().getLauncherData().getLobby(lobbyName).addMessage(messageToAdd);
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
