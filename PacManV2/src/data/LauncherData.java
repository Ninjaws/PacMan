package data;

import client.presentation.entities.Lobby;

import java.util.ArrayList;
import java.util.List;

public class LauncherData {

    private List<LobbyData> lobbies = new ArrayList<>();

    public LauncherData() {

    }


    public List<LobbyData> getLobbies() {
        return lobbies;
    }

    public void addLobby(LobbyData lobby) {
        lobbies.add(lobby);
    }


    public LobbyData getLobby(String lobbyName) {
        return lobbies.stream()
                .filter(lobby -> lobby.getLobbyName().equals(lobbyName))
                .findFirst()
                .orElse(null);

    }

}
