package data.packets.lobby;

import data.packets.Packet;

public class PacketLobbyLeave implements Packet {

    private String lobbyName;
    private String userName;

    public PacketLobbyLeave(String lobbyName, String userName) {
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
