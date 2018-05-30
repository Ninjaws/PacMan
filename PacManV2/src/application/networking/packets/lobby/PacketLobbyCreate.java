package application.networking.packets.lobby;

import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketLobbyCreate implements Packet, Serializable {

    private String lobbyName;
    private String userName;

    public PacketLobbyCreate(String lobbyName, String userName) {
        this.lobbyName = lobbyName;
        this.userName = userName;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public String getUserName() {
        return userName;
    }
}
