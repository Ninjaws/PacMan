package application.networking.packets.game;

import application.networking.packets.Packet;
import application.testgame.data.ApplicationData;

import java.io.Serializable;

public class PacketGame implements Packet,Serializable {

    private ApplicationData applicationData;

    public PacketGame(ApplicationData applicationData) {
        this.applicationData = applicationData;
    }

    public ApplicationData getApplicationData() {
        return applicationData;
    }
}
