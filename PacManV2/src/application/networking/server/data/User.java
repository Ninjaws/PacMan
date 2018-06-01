package application.networking.server.data;

import application.game.data.Game;
import application.networking.packets.Packet;
import application.networking.packets.game.player.PacketPlayerUpdate;
import application.networking.packets.launcher.PacketLauncher;
import application.networking.server.ServerMain;
import application.networking.server.listeners.ClientThreadReceiver;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class User {

    private Socket socket;
    private String userName;

    private boolean inGame = false;


    private ClientThreadReceiver receiver;

    ObjectOutputStream objectToClient;
    ObjectInputStream objectFromClient;


    private Queue<Packet> packets = new LinkedList<>();

    private boolean markedForDeletion = false;

    public User(Socket socket) throws Exception {
        this.socket = socket;

        objectToClient = new ObjectOutputStream(socket.getOutputStream());
        objectFromClient = new ObjectInputStream(socket.getInputStream());

        receiver = new ClientThreadReceiver(objectFromClient, socket, this);

        receiver.start();
    }

    public void sendLauncher() {
        try {


            objectToClient.reset();
            objectToClient.writeObject(new PacketLauncher(ServerMain.getApplicationData()));
          //  objectToClient.writeObject(ServerMain.getApplicationData());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGame() {
        try {
            objectToClient.reset();
            objectToClient.writeObject(new PacketPlayerUpdate(ServerMain.getAppDataTest().getGameObjects().get(0).getPlayerName(),ServerMain.getAppDataTest().getGameObjects().get(0).getPosition()));
        //    objectToClient.reset();
      //      objectToClient.writeObject(ServerMain.getAppDataTest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientThreadReceiver getReceiver() {
        return receiver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }


    public Queue<Packet> getPackets() {
        return packets;
    }

    public boolean isMarkedForDeletion() {
        return markedForDeletion;
    }

    public void setMarkedForDeletion(boolean markedForDeletion) {
        this.markedForDeletion = markedForDeletion;
    }
}
