package server;

import data.Conversation;
import server.data.UserThread;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static Conversation conversation;

    public static void main(String[] args) {
        //  ThreadManager threadManager = ThreadManager.getInstance();
        conversation = new Conversation();
        //   new Thread(threadManager).start();

        try {
            ServerSocket serverSocket = new ServerSocket(8313);

            while (true) {
                Socket socket = serverSocket.accept();
                UserThread thread = new UserThread(socket);
                thread.start();
                //  threadManager.addUserThread(thread);
                //       System.out.println("Amount of clients: " + threadManager.getAmountOfThreads());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static Conversation getConversation() {
        return conversation;
    }
}

