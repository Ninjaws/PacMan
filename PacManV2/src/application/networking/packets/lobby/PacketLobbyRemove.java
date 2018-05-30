package application.networking.packets.lobby;

import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketLobbyRemove implements Packet, Serializable {

    private String lobbyName;

    public PacketLobbyRemove(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public String getLobbyName() {
        return lobbyName;
    }
}
