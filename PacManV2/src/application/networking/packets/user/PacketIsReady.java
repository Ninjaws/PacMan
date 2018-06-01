package application.networking.packets.user;

import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketIsReady implements Packet, Serializable {
    private String userName;
    private boolean isReady;

    public PacketIsReady(String userName, boolean isReady) {
        this.userName = userName;
        this.isReady = isReady;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isReady() {
        return isReady;
    }
}
