package application.networking.packets.player;

import application.networking.packets.Packet;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class PacketPlayerUpdate implements Packet, Serializable {

    private String userName;
    private Point2D position;

    public PacketPlayerUpdate(String userName, Point2D position) {
        this.userName = userName;
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public Point2D getPosition() {
        return position;
    }

}
