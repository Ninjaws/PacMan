package data.packets.user;

import data.packets.Packet;

import java.io.Serializable;

public class PacketUserAdd implements Packet, Serializable{
    private String userName;

    public PacketUserAdd(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
