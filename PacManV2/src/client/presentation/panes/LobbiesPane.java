package client.presentation.panes;

import client.presentation.entities.Lobby;
import com.jfoenix.controls.JFXButton;
import data.StateManager;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class LobbiesPane extends HBox {
    private ArrayList<Lobby> lobbies = new ArrayList<>();
    private ListView sessions;
    private Lobby selectedLobby = null;
    public LobbiesPane() {
        sessions = new ListView();

        //test values
        lobbies.add(new Lobby("IAN"));
        lobbies.add(new Lobby("JORDY"));

        sessions.getItems().addAll(lobbies);

        VBox buttons = new VBox();

        JFXButton refresh = new JFXButton("Refresh");
        refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> StateManager.getInstance().setCurrentState(StateManager.State.REQUESTINGREFRESH));
        refresh.setId("refresh-button");

        buttons.getChildren().addAll(refresh);
        this.getChildren().addAll(sessions, buttons);
    }
}
