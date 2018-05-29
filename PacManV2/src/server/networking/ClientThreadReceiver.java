package server.networking;

import data.packets.Packet;
import server.data.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ClientThreadReceiver extends Thread {

    private ObjectInputStream objectFromClient;
    private Socket socket;
    private User user;

    private boolean alive = true;


    public ClientThreadReceiver(ObjectInputStream objectFromClient, Socket socket, User user) {
        this.objectFromClient = objectFromClient;
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run() {
        while (alive) {
            try {

                Packet packet = (Packet) objectFromClient.readObject();
                user.getPackets().offer(packet);


            } catch (Exception e) {
                try {
                    socket.close();
                } catch (IOException e1) {
                }
                break;
            }
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
