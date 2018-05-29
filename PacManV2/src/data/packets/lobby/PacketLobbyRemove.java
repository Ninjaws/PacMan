package data.packets.lobby;

import data.packets.Packet;

public class PacketLobbyRemove implements Packet {

    private String lobbyName;

    public PacketLobbyRemove(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public String getLobbyName() {
        return lobbyName;
    }
}
