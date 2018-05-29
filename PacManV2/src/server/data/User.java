package server.data;

import data.packets.Packet;
import server.ServerMain;
import server.networking.ClientThreadReceiver;
import server.networking.ClientThreadSender;

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


    public User(Socket socket) throws Exception {
        this.socket = socket;

        objectToClient = new ObjectOutputStream(socket.getOutputStream());
        objectFromClient = new ObjectInputStream(socket.getInputStream());

        receiver = new ClientThreadReceiver(objectFromClient, socket);

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

    public ClientThreadReceiver getReceiver() {
        return receiver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
