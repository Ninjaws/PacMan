package data.packets.message;

import data.launcher.Message;
import data.packets.Packet;

public class PacketMessageSend implements Packet {

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
