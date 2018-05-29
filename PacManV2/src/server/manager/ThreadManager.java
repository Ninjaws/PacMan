package server.manager;

import data.packets.Packet;
import server.data.User;
import server.networking.ClientThreadReceiver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ThreadManager extends Thread {

    private List<User> users = new ArrayList<>();
    private List<User> tempUsers = new ArrayList<>();


    public ThreadManager() {
    }

    public void run() {
        try {
            while (true) {

                processPackets();
                sendApplication();

                addNewUsers();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void processPackets() {

        for (Packet packet : getPackets()) {
            chooseAction(packet);
        }
    }

    private void sendApplication() {
        users.forEach(user -> user.sendApplication());
    }


    public void addNewUsers(){
        tempUsers.forEach(user->users.add(user));

        tempUsers.clear();
    }

    public void addUser(User thread) {
        tempUsers.add(thread);
    }

    private void chooseAction(Packet packet) {
           /*
            if(packet instanceof PacketUserAdd) {

                String userToAdd = packet.
                ServerMain.getApplicationData().addUser(new UserData(userToAdd));
            }

            case "user_remove":
                String userToRemove = (String) objectFromClient.readObject();

                //In case the user quits the program while still in a lobby
                //This will make sure the player that left is not occupying a spot
                for (LobbyData lobbyData : ServerMain.getApplicationData().getLauncherData().getLobbies()) {
                    synchronized (lobbyData) {
                        if (lobbyData.getPlayers().contains(userToRemove))
                            lobbyData.removePlayer(userToRemove);

                    }
                }

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
*/
    }

    public List<Packet> getPackets() {
        return users.stream()
                .map(User::getReceiver)
                .map(ClientThreadReceiver::getPackets)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


}
