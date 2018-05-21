package server.data;

import server.networking.ClientThreadReceiver;
import server.networking.ClientThreadSender;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserThread extends Thread {

    private String username = null;

    private Socket socket;
    private DataInputStream dataFromClient;
    private DataOutputStream dataToClient;

    private ObjectOutputStream objectToClient;
    private ObjectInputStream objectFromClient;

    private ClientThreadSender sender;
    private ClientThreadReceiver receiver;


    private String currentLine = null;

    public UserThread(Socket socket) throws Exception {
        this.socket = socket;

        this.objectToClient = new ObjectOutputStream(socket.getOutputStream());
        this.objectFromClient = new ObjectInputStream(socket.getInputStream());


        this.username = (String) objectFromClient.readObject();
        objectToClient.writeObject("Connected to server");

        sender = new ClientThreadSender(this);
        receiver = new ClientThreadReceiver(this);


    }

    public void run() {
        sender.start();
        receiver.start();
        while (true) {
            try {


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public String getUserName() {
        return username;
    }

    public synchronized ObjectOutputStream getObjectToClient() {
        return objectToClient;
    }

    public synchronized ObjectInputStream getObjectFromClient() {
        return objectFromClient;
    }

    public ClientThreadSender getSender() {
        return sender;
    }

    public ClientThreadReceiver getReceiver() {
        return receiver;
    }
}
