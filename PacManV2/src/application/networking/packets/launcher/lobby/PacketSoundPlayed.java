package application.networking.packets.launcher.lobby;

import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketSoundPlayed implements Serializable, Packet {
    private String lobbyName;

    public PacketSoundPlayed(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public String getLobbyName() {
        return lobbyName;
    }
}
