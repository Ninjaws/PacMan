package data.packets.user;

import data.packets.Packet;

public class PacketUserAdd implements Packet{
    private String userName;

    public PacketUserAdd(String userName) {
        this.userName = userName;
    }

    public String getUderName() {
        return userName;
    }
}
