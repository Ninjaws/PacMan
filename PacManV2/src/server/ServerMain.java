package server;

import data.ApplicationData;
import server.data.UserThread;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    private static ApplicationData applicationData;

    public static void main(String[] args) {
        applicationData = new ApplicationData();
        try {
            ServerSocket serverSocket = new ServerSocket(666);
            int count = 0;
            while (true) {
                Socket socket = serverSocket.accept();
                UserThread thread = new UserThread(socket);

                count++;
                System.out.println("Count: " +count);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static ApplicationData getApplicationData() {
        return applicationData;
    }
}

