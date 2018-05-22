package server.networking;

import server.data.UserThread;

import java.util.ArrayList;

public class Lobby {
    private ArrayList<UserThread> players = new ArrayList<>();
    private String name;

    public Lobby(String name) {
        this.name = name;
    }

    public void join(UserThread player){
        if(players.size() <= 5){
            players.add(player);
        }
    }
}
