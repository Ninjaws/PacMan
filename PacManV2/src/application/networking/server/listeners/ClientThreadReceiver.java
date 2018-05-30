package application.networking.server.listeners;

import application.networking.packets.Packet;
import application.networking.server.data.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThreadReceiver extends Thread {

    private ObjectInputStream objectFromClient;
    private Socket socket;
    private User user;

    private boolean alive = true;


    public ClientThreadReceiver(ObjectInputStream objectFromClient, Socket socket, User user) {
        this.objectFromClient = objectFromClient;
        this.socket = socket;
        this.user = user;
        System.out.println("Receiver " + user + " created");
    }

    @Override
    public void run() {
        System.out.println("Receiver " + user + " running");
        while (alive) {
            try {
                System.out.println("waiting");
                Packet packet = (Packet) objectFromClient.readObject();
                System.out.println("Packet received: " + packet);
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
