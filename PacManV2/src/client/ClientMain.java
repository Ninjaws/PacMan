package client;

import client.data.Storage;
import client.presentation.frames.LauncherFrame;
import client.presentation.dialogs.LogInDialog;

public class ClientMain {

    public static void main(String[] args) throws Exception {

        LogInDialog logInDialog = new LogInDialog("Log In");
        LogInDialog.DialogResult result = logInDialog.getResult();

        Storage.getInstance().setUsername(result.getName());
        Storage.getInstance().getObjectToServer().writeObject(result.getName());
        System.out.println(Storage.getInstance().getObjectFromServer().readObject());
        LauncherFrame.getInstance();

        Storage.getInstance().startThreads();


    }

}