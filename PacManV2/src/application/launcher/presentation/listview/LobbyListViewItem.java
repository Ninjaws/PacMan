package application.launcher.presentation.listview;

import application.networking.client.data.Storage;
import application.launcher.presentation.panes.LauncherPane;
import application.launcher.presentation.panes.LobbyPane;
import com.jfoenix.controls.JFXButton;
import application.networking.packets.launcher.lobby.PacketLobbyJoin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;


public class LobbyListViewItem extends VBox {
    private String name;
    private int playerCount;

    public LobbyListViewItem(String name) {
        this.name = name;
        this.playerCount = 0;

        Text lobbyName = new Text(name);
        lobbyName.getStyleClass().add("lobby-text");

        Text playerCountText = new Text("Playercount: " + Storage.getInstance().getApplicationData().getLauncherData().getLobby(name).getPlayers().size() + "/5");
        playerCountText.getStyleClass().add("lobby-text");

        JFXButton join = new JFXButton("Join");
        join.setId("join-button");
        join.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                if(Storage.getInstance().getApplicationData().getLauncherData().getLobby(name).getPlayers().size() >= 5)
                    return;

                Storage.getInstance().getObjectToServer().writeObject(new PacketLobbyJoin(name, Storage.getInstance().getUsername()));

            } catch (IOException e) {
                e.printStackTrace();
            }
            LauncherPane.setNewCenter(new LobbyPane(name));
        });


        this.getChildren().addAll(lobbyName, playerCountText, join);
    }

    public String getName() {
        return name;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}




