package application.networking.packets.launcher.lobby;

import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketLobbySound implements Packet, Serializable{
    private String user;
    private String lobbyName;
    private byte audioBytes[];


    public PacketLobbySound(String user,String lobbyName, byte[] audioBytes) {
        this.user = user;
        this.lobbyName = lobbyName;
        this.audioBytes = audioBytes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public byte[] getAudioBytes() {
        return audioBytes;
    }

    public void setAudioBytes(byte[] audioBytes) {
        this.audioBytes = audioBytes;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }
}
