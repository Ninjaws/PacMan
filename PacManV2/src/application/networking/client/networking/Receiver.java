package application.networking.client.networking;

import application.game.data.Game;
import application.networking.client.data.Storage;
import application.launcher.data.ApplicationData;
import application.networking.packets.Packet;
import application.networking.packets.game.player.PacketPlayerUpdate;
import application.networking.packets.launcher.PacketLauncher;

import java.awt.geom.Point2D;

public class Receiver extends Thread {
    @Override
    public void run() {
        while (true) {
            try {


                Packet packet = (Packet)Storage.getInstance().getObjectFromServer().readObject();

                //---- Launcher ---//

                if(packet instanceof PacketLauncher){
                    ApplicationData tempApp = ((PacketLauncher) packet).getApplicationData();
                    Storage.getInstance().setApplicationData(tempApp);
                }

                //---- Game ----//

                else if (packet instanceof PacketPlayerUpdate){
                    String playerName = ((PacketPlayerUpdate) packet).getUserName();
                    Point2D pos = ((PacketPlayerUpdate) packet).getPosition();

                    Storage.getInstance().getAppDataTest().getGameObject(playerName).setPosition(pos);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processData() {

    }

}