package application.launcher.presentation.panes;

import application.game.Main;
import application.networking.client.data.Storage;
import com.jfoenix.controls.JFXButton;
import application.networking.packets.user.PacketUserAdd;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;


public class LoginPane extends HBox {
    public LoginPane() {

        HBox hBox = new HBox();
        Text userNameText = new Text("UserName: ");
        userNameText.setId("user-name-text");

        TextField userNameTextField = new TextField();
        userNameTextField.setId("user-name-field");

        hBox.getChildren().addAll(userNameText, userNameTextField);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);

        JFXButton connect = new JFXButton("Connect");
        connect.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                if (!Storage.getInstance().getApplicationData().userExists(userNameTextField.getText())) {

                    Storage.getInstance().setUsername(userNameTextField.getText());

                    //Packet packet = new PacketUserAdd(userNameTextField.getText());
                    //System.out.println(packet);
                    Storage.getInstance().getObjectToServer().writeObject(new PacketUserAdd(userNameTextField.getText()));

                    LauncherPane.setNewCenter(new LobbiesPane());

                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JFXButton solo = new JFXButton("Solo Game");
        solo.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Main.launchGame();
        });
        solo.setId("solo-button");

        connect.setId("connect-button");
        vBox.getChildren().addAll(connect, solo);
        this.getChildren().add(vBox);
    }
}
