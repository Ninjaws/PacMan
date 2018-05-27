package client.presentation.panes;

import client.data.Storage;
import client.presentation.listview.LobbyListViewItem;
import com.jfoenix.controls.JFXButton;
import data.ApplicationData;
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

import java.io.IOException;

public class LobbiesPane extends HBox {
    // private ArrayList<LobbyListViewItem> lobbies = new ArrayList<>();
    private ListView sessions;

    // private LobbyListViewItem selectedLobby = null;
    public LobbiesPane() {
        sessions = new ListView();

        Storage.getInstance().getApplicationData().getLauncherData().getLobbies().forEach(lobbyData -> {
            sessions.getItems().add(new LobbyListViewItem(lobbyData.getLobbyName()));
        });

        VBox buttons = new VBox();
        JFXButton refresh = new JFXButton("Refresh");

        //   refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println());
        refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            sessions.getItems().clear();
            Storage.getInstance().getApplicationData().getLauncherData().getLobbies().forEach(lobbyData -> {
                sessions.getItems().add(new LobbyListViewItem(lobbyData.getLobbyName()));
            });

        });

        refresh.getStyleClass().add("lobby-button");

        JFXButton create = new JFXButton("Create");

        create.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            TextField lobbyName = new TextField();
            Button confirmCreate = new Button("create");
            confirmCreate.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {

                try {
                    Storage.getInstance().getObjectToServer().writeObject("lobby");
                    Storage.getInstance().getObjectToServer().writeObject(lobbyName.getText());
                    LauncherPane.setNewCenter(new LobbyPane(lobbyName.getText()));
                    dialog.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // ApplicationData.getInstance().getLauncherData().addLobby(new LobbyData(lobbyName.getText()));


            });
            Scene scene = new Scene(new Group(lobbyName, confirmCreate));
            dialog.setWidth(200);
            dialog.setScene(scene);
            dialog.show();
        });
        create.getStyleClass().add("lobby-button");

        buttons.getChildren().addAll(refresh, create);
        this.getChildren().addAll(sessions, buttons);

//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.millis(1000),
//                ae -> sessions.getItems().addAll(LauncherData.getInstance().getLobbies())));//sessions.refresh()));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }
}
