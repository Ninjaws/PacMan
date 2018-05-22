package client.presentation.entities;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Lobby extends VBox {
    private String name;
    private int playerCount;

    public Lobby(String name) {
        this.name = name;
        this.playerCount = 0;

        Text nameText = new Text(name);
        nameText.getStyleClass().add("lobby-text");
        Text playerCountText = new Text("Playercount: " + playerCount +  "/5");
        playerCountText.getStyleClass().add("lobby-text");
        JFXButton join = new JFXButton("Join");
        join.setId("join-button");
        join.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
        });

        this.getChildren().addAll(nameText, playerCountText, join);
    }

    public String getName() {
        return name;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}




