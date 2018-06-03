package application.networking.server;

import application.launcher.data.ApplicationData;
import application.networking.server.data.User;
import application.networking.server.manager.ThreadManager;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
    private static ApplicationData applicationData;
    private static application.testgame.data.ApplicationData appDataTest;
    private static ArrayList<application.testgame.data.ApplicationData> games;
    private static ThreadManager threadManager;

    public static void main(String[] args) {
        applicationData = new ApplicationData();
       // appDataTest = new application.testgame.data.ApplicationData();
        games = new ArrayList<>();
        threadManager = new ThreadManager();
        threadManager.start();

        try {
            ServerSocket serverSocket = new ServerSocket(9595);
            int count = 0;
            while (true) {
                Socket socket = serverSocket.accept();

                threadManager.addUser(new User(socket));

                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static ApplicationData getApplicationData() {
        return applicationData;
    }

    public synchronized static application.testgame.data.ApplicationData getAppDataTest() {
        return appDataTest;
    }

    public static ArrayList<application.testgame.data.ApplicationData> getGames() {
        return games;
    }

    public static application.testgame.data.ApplicationData getGame(String lobbyName){
       return games.stream()
               .filter(game->game.getGameName().equals(lobbyName))
               .findFirst()
               .orElse(null);
    }


}

