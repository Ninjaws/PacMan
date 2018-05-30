package data.packets.user;

import data.packets.Packet;

import java.io.Serializable;

public class PacketUserRemove implements Packet, Serializable {
    private String userName;

    public PacketUserRemove(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
