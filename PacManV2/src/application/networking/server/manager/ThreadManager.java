package application.networking.server.manager;

import application.launcher.data.LobbyData;
import application.launcher.data.Message;
import application.launcher.data.UserData;
import application.networking.packets.Packet;
import application.networking.packets.lobby.PacketLobbyCreate;
import application.networking.packets.lobby.PacketLobbyJoin;
import application.networking.packets.lobby.PacketLobbyLeave;
import application.networking.packets.lobby.PacketLobbyRemove;
import application.networking.packets.message.PacketMessageSend;
import application.networking.packets.user.PacketUserAdd;
import application.networking.packets.user.PacketUserRemove;
import application.networking.server.ServerMain;
import application.networking.server.data.User;

import java.util.*;

public class ThreadManager extends Thread {

    private List<User> users = new ArrayList<>();
    private List<User> tempUsers = new ArrayList<>();


    public ThreadManager() {
    }

    public void run() {
        try {
            while (true) {

                processPackets();
                sendApplicationToClients();

                removeMarkedUsers();

                addNewUsers();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addUser(User user) {
        tempUsers.add(user);
        System.out.println("User added to temp: " + user);
    }

    private void processPackets() {
        for (User user : users) {
            Queue<Packet> packets = user.getPackets();

            while (!packets.isEmpty()) {
                System.out.println("Processing packet: " + packets.peek());
                chooseAction(user,packets.poll());
            }
        }
    }

    private void sendApplicationToClients() {
        users.forEach(user -> user.sendApplication());
    }


    private void addNewUsers() {
      //  tempUsers.forEach(user -> users.add(user));
      //  System.out.println(tempUsers.size());
        /*
        for (User tempUser : tempUsers) {
            System.out.println(tempUsers.size());
            System.out.println("tempUser: " + tempUser);
            users.add(tempUser);
            System.out.println("New user: " + tempUser);
        }
        */

        Iterator<User> it = tempUsers.iterator();
        while (it.hasNext()) {
            User user = it.next();

            users.add(user);
            System.out.println("New user: " + user);
            it.remove();
        }


      //  tempUsers.clear();
    }

    private void removeMarkedUsers() {
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.isMarkedForDeletion()) {
                System.out.println("Users: " + users.size());
                removeUser(user);
                it.remove();
                System.out.println("Users: " + users.size());
            }
        }
    }

    private void removeUser(User user){

        //In case the user quits the program while still in a lobby
        //This will make sure the player that left is not occupying a spot
        for (LobbyData lobbyData : ServerMain.getApplicationData().getLauncherData().getLobbies()) {
            if (lobbyData.getPlayers().contains(user.getUserName()))
                lobbyData.removePlayer(user.getUserName());

        }

        ServerMain.getApplicationData().removeUser(user.getUserName());

        user.getReceiver().setAlive(false);

        while(user.getReceiver().isAlive());

        System.out.println("Dead");

    }

    private void chooseAction(User user, Packet packet) {

        //User Added
        if (packet instanceof PacketUserAdd) {
            String userToAdd = ((PacketUserAdd) packet).getUserName();
            user.setUserName(userToAdd);
            ServerMain.getApplicationData().addUser(new UserData(userToAdd));
        }
        //User Removed
        else if (packet instanceof PacketUserRemove) {

            //Useless now
            String userToRemove = ((PacketUserRemove) packet).getUserName();



            //User user = users.stream().filter(user1 -> user1.getUserName().equals(userToRemove)).findFirst().orElse(null);
            user.setMarkedForDeletion(true);
        }
        //Lobby Created
        else if (packet instanceof PacketLobbyCreate) {
            String lobbyToCreate = ((PacketLobbyCreate) packet).getLobbyName();
            String creatingUser = ((PacketLobbyCreate) packet).getUserName();

            ServerMain.getApplicationData().getLauncherData().addLobby(new LobbyData(lobbyToCreate));
            ServerMain.getApplicationData().getLauncherData().getLobby(lobbyToCreate).addPlayer(creatingUser);
        }
        //Lobby Removed
        else if (packet instanceof PacketLobbyRemove) {
            String lobbyToRemove = ((PacketLobbyRemove) packet).getLobbyName();
            ServerMain.getApplicationData().getLauncherData().removeLobby(lobbyToRemove);
        }
        //Lobby Joined
        else if (packet instanceof PacketLobbyJoin) {
            String lobbyToJoin = ((PacketLobbyJoin) packet).getLobbyName();
            String userName = ((PacketLobbyJoin) packet).getUserName();
            ServerMain.getApplicationData().getLauncherData().getLobby(lobbyToJoin).addPlayer(userName);
        }
        //Lobby Left
        else if (packet instanceof PacketLobbyLeave) {
            String lobbyToLeave = ((PacketLobbyLeave) packet).getLobbyName();
            String userName = ((PacketLobbyLeave) packet).getUserName();
            ServerMain.getApplicationData().getLauncherData().getLobby(lobbyToLeave).removePlayer(userName);
        }
        //Message Sent
        else if (packet instanceof PacketMessageSend) {
            String lobby = ((PacketMessageSend) packet).getLobbyName();
            Message message = ((PacketMessageSend) packet).getMessage();
            ServerMain.getApplicationData().getLauncherData().getLobby(lobby).addMessage(message);
        }
    }


}