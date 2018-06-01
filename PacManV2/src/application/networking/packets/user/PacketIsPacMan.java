package application.networking.packets.user;

import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketIsPacMan implements Packet, Serializable {
    private String user;
    private boolean isPacMan;

    public PacketIsPacMan(String user, boolean isPacMan) {
        this.user = user;
        this.isPacMan = isPacMan;
    }

    public String getUser() {
        return user;
    }

    public boolean isPacMan() {
        return isPacMan;
    }
}
