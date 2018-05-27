package client.presentation.listview;

import client.presentation.panes.LauncherPane;
import client.presentation.panes.LobbyPane;
import com.jfoenix.controls.JFXButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class LobbyListViewItem extends VBox {
    private String name;
    private int playerCount;

    public LobbyListViewItem(String name) {
        this.name = name;
        this.playerCount = 0;

        Text lobbyName = new Text(name);
        lobbyName.getStyleClass().add("lobby-text");

        Text playerCountText = new Text("Playercount: " + playerCount +  "/5");
        playerCountText.getStyleClass().add("lobby-text");

        JFXButton join = new JFXButton("Join");
        join.setId("join-button");
        join.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> LauncherPane.setNewCenter(new LobbyPane(name)));
        
        this.getChildren().addAll(lobbyName, playerCountText, join);
    }

    public String getName() {
        return name;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}




