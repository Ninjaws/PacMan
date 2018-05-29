package data.launcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LauncherData implements Serializable {

    private List<LobbyData> lobbies = new ArrayList<>();

    public LauncherData() {

    }

    public List<LobbyData> getLobbies() {
        return lobbies;
    }

    public void addLobby(LobbyData lobby) {
        lobbies.add(lobby);
    }

    public void removeLobby (String lobbyName){
        lobbies.removeIf(lobby -> lobby.getLobbyName().equals(lobbyName));
    }

    public LobbyData getLobby(String lobbyName) {
        return lobbies.stream()
                .filter(lobby -> lobby.getLobbyName().equals(lobbyName))
                .findFirst()
                .orElse(null);

    }

}
