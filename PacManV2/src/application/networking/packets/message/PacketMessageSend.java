package application.networking.packets.message;

import application.launcher.data.Message;
import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketMessageSend implements Packet, Serializable {

    private String lobbyName;
    private Message message;

    public PacketMessageSend(String lobbyName, Message message) {
        this.lobbyName = lobbyName;
        this.message = message;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public Message getMessage() {
        return message;
    }
}
