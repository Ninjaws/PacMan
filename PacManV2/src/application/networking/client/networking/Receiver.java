package application.networking.client.networking;

import application.networking.client.data.Storage;
import application.launcher.data.ApplicationData;

public class Receiver extends Thread {
    @Override
    public void run() {
        while (true) {
            try {

                ApplicationData tempApp = (ApplicationData) Storage.getInstance().getObjectFromServer().readObject();
                Storage.getInstance().setApplicationData(tempApp);
              //  System.out.println("New");
                //System.out.println(Storage.getInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}