package data.packets.user;

import data.packets.Packet;

public class PacketUserRemove implements Packet {
    private String userName;

    public PacketUserRemove(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
