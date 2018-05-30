package application.networking.client.networking;

import application.game.data.Game;
import application.networking.client.data.Storage;
import application.launcher.data.ApplicationData;

public class Receiver extends Thread {
    @Override
    public void run() {
        while (true) {
            try {

                ApplicationData tempApp = (ApplicationData) Storage.getInstance().getObjectFromServer().readObject();
                Storage.getInstance().setApplicationData(tempApp);

                application.testgame.data.ApplicationData tempAppTest = (application.testgame.data.ApplicationData) Storage.getInstance().getObjectFromServer().readObject();
             //   Storage.getInstance().setAppDataTest(tempAppTest);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}