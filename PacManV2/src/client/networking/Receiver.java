package client.networking;

import client.data.Storage;
import data.ApplicationData;
import data.Conversation;

public class Receiver extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
             //   System.out.println("Checking");
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