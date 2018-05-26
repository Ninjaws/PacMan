package client.presentation.panes;

import client.presentation.entities.Lobby;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        sessions.refresh();

        sessions.getItems().addAll(lobbies);

        VBox buttons = new VBox();

        JFXButton refresh = new JFXButton("Refresh");
        refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println());
        refresh.getStyleClass().add("lobby-button");

        JFXButton create = new JFXButton("Create");

        create.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            TextField lobbyName = new TextField();
            Button confirmCreate = new Button("create");
            confirmCreate.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                LauncherPane.setNewCenter(new LobbyPane(lobbyName.getText()));
                dialog.close();
            });
            Scene scene = new Scene(new Group(lobbyName,confirmCreate));
            dialog.setWidth(200);
            dialog.setScene(scene);
            dialog.show();
        });
        create.getStyleClass().add("lobby-button");

        buttons.getChildren().addAll(refresh, create);
        this.getChildren().addAll(sessions, buttons);
    }
}
