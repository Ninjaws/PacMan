package data.packets.lobby;

import data.packets.Packet;

public class PacketLobbyCreate implements Packet {

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
