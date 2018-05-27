package server.data;

import server.networking.ClientThreadReceiver;
import server.networking.ClientThreadSender;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserThread extends Thread {

   // private String username;

    private Socket socket;

    private ClientThreadSender sender;
    private ClientThreadReceiver receiver;



    public UserThread(Socket socket) throws Exception {
        this.socket = socket;

        ObjectOutputStream objectToClient = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectFromClient = new ObjectInputStream(socket.getInputStream());

        sender = new ClientThreadSender(objectToClient, socket);
        receiver = new ClientThreadReceiver(objectFromClient, socket);

        sender.start();
        receiver.start();

    }

    public void run() {
        while (true) {
            System.out.println(socket.isConnected());
        }
    }

    public ClientThreadSender getSender() {
        return sender;
    }

    public ClientThreadReceiver getReceiver() {
        return receiver;
    }
}
