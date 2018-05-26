package server.data;

import server.networking.ClientThreadReceiver;
import server.networking.ClientThreadSender;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserThread extends Thread {

    private String username;

    private Socket socket;

    private ClientThreadSender sender;
    private ClientThreadReceiver receiver;



    public UserThread(Socket socket) throws Exception {
        this.socket = socket;

        ObjectOutputStream objectToClient = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectFromClient = new ObjectInputStream(socket.getInputStream());


        this.username = (String) objectFromClient.readObject();

        sender = new ClientThreadSender(objectToClient);
        receiver = new ClientThreadReceiver(objectFromClient);
        sender.start();
        receiver.start();

    }

    public void run() {
        while (true) {

        }
    }


    public String getUserName() {
        return username;
    }

    public ClientThreadSender getSender() {
        return sender;
    }

    public ClientThreadReceiver getReceiver() {
        return receiver;
    }
}
