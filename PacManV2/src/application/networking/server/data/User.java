package application.networking.server.data;

import application.game.data.Game;
import application.networking.packets.Packet;
import application.networking.server.ServerMain;
import application.networking.server.listeners.ClientThreadReceiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class User {

    private Socket socket;
    private String userName;

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

    public void sendApplication() {
        try {
            objectToClient.reset();
            objectToClient.writeObject(ServerMain.getApplicationData());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAppDatTest() {
        try {
            objectToClient.reset();

            objectToClient.writeObject(ServerMain.);
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
