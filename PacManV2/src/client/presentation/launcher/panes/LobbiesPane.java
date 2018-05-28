package client.presentation.launcher.panes;

import client.data.Storage;
import client.presentation.launcher.listview.LobbyListViewItem;
import com.jfoenix.controls.JFXButton;
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
        refresh.getStyleClass().add("lobby-button");

        //   refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println());
        refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            sessions.getItems().clear();
            Storage.getInstance().getApplicationData().getLauncherData().getLobbies().forEach(lobbyData -> {
                sessions.getItems().add(new LobbyListViewItem(lobbyData.getLobbyName()));
            });

        });



        JFXButton create = new JFXButton("Create");
        create.getStyleClass().add("lobby-button");

        create.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            TextField lobbyName = new TextField();
            lobbyName.setId("lobby-field");
            Button confirmCreate = new Button("create");
            confirmCreate.setId("create-button");
            confirmCreate.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                try {
                    Storage.getInstance().getObjectToServer().writeObject("lobby_create");
                    Storage.getInstance().getObjectToServer().writeObject(Storage.getInstance().getUsername());
                    Storage.getInstance().getObjectToServer().writeObject(lobbyName.getText());
                    LauncherPane.setNewCenter(new LobbyPane(lobbyName.getText()));
                    dialog.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // ApplicationData.getInstance().getLauncherData().addLobby(new LobbyData(lobbyName.getText()));


            });
            //adds styles
            HBox hBox = new HBox(lobbyName, confirmCreate);
            hBox.getStylesheets().add(getClass().getResource("/css_files/launcher.css").toExternalForm());

            Scene scene = new Scene(hBox);
            dialog.setWidth(300);
            dialog.setHeight(90);
            dialog.setScene(scene);
            dialog.show();
        });

        buttons.getChildren().addAll(refresh, create);
        this.getChildren().addAll(sessions, buttons);

//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.millis(1000),
//                ae -> sessions.getItems().addAll(LauncherData.getInstance().getLobbies())));//sessions.refresh()));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }
}
