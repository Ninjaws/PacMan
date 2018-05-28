package client.presentation.panes;

import client.data.Storage;
import com.jfoenix.controls.JFXButton;
import data.Message;
import managers.StateManager;
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
                if(!Storage.getInstance().getApplicationData().userExists(userNameTextField.getText())){
                    Storage.getInstance().getObjectToServer().writeObject((String)userNameTextField.getText());
                    LauncherPane.setNewCenter(new LobbiesPane());
                }
                else{

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        connect.setId("connect-button");
        vBox.getChildren().add(connect);
        this.getChildren().add(vBox);
    }
}
