package application.networking.packets.launcher;

import application.launcher.data.ApplicationData;
import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketLauncher implements Packet, Serializable {

    private ApplicationData applicationData;

    public PacketLauncher(ApplicationData applicationData) {
        this.applicationData = applicationData;
    }

    public ApplicationData getApplicationData() {
        return applicationData;
    }
}
