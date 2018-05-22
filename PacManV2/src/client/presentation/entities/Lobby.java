package client.presentation.entities;

import com.jfoenix.controls.JFXButton;
import data.StateManager;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Lobby extends VBox {
    private String name;
    private int playerCount;

    public Lobby(String name) {
        this.name = name;
        this.playerCount = 0;

        Text nameText = new Text(name);
        nameText.getStyleClass().add("lobby-text");
        Text playerCountText = new Text("Playercount:'" + playerCount +  "/5");
        playerCountText.getStyleClass().add("lobby-text");
        JFXButton join = new JFXButton("Join");
        join.setId("join-button");
        join.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(name);
                StateManager.getInstance().setCurrentState(StateManager.State.REQUESTINGJOININGLOBBY);
            }
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




