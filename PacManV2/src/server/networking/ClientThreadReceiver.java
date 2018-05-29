package server.networking;

import data.packets.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ClientThreadReceiver extends Thread {

    private ObjectInputStream objectFromClient;
    private Socket socket;
    private Queue<Packet> packets = new LinkedList<>();

    public ClientThreadReceiver(ObjectInputStream objectFromClient, Socket socket) {
        this.objectFromClient = objectFromClient;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {

                Packet packet = (Packet) objectFromClient.readObject();
                packets.offer(packet);


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

    public Queue<Packet> getPackets() {
        return packets;
    }
}
