package client.presentation.launcher.panes;

import client.data.Storage;
import client.presentation.launcher.listview.LobbyListViewItem;
import com.jfoenix.controls.JFXButton;
import data.launcher.Conversation;
import data.packets.Packet;
import data.packets.lobby.PacketLobbyCreate;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class LobbiesPane extends HBox {
    // private ArrayList<LobbyListViewItem> lobbies = new ArrayList<>();
    private ListView sessions;
    private boolean createIsPressed = false;

    // private LobbyListViewItem selectedLobby = null;
    public LobbiesPane() {
        sessions = new ListView();

        Storage.getInstance().getApplicationData().getLauncherData().getLobbies().forEach(lobbyData -> {
            sessions.getItems().add(new LobbyListViewItem(lobbyData.getLobbyName()));
        });

        VBox buttons = new VBox();
/*
        JFXButton refresh = new JFXButton("Refresh");
        refresh.getStyleClass().add("lobby-button");

        //   refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println());
        refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {


        });*/
        //Refreshes the list of games
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    sessions.getItems().clear();
                    Storage.getInstance().getApplicationData().getLauncherData().getLobbies().forEach(lobbyData -> {
                        sessions.getItems().add(new LobbyListViewItem(lobbyData.getLobbyName()));
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000, 1000);

        JFXButton create = new JFXButton("Create");
        create.getStyleClass().add("lobby-button");

        create.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {


            if (!createIsPressed) {
                Stage dialog = new Stage();
                dialog.initStyle(StageStyle.UTILITY);
                TextField lobbyName = new TextField();
                lobbyName.setId("lobby-field");
                Button confirmCreate = new Button("create");
                confirmCreate.setId("create-button");
                confirmCreate.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                    try {

                        Storage.getInstance().getObjectToServer().writeObject(new PacketLobbyCreate(lobbyName.getText(), Storage.getInstance().getUsername()));

                        LauncherPane.setNewCenter(new LobbyPane(lobbyName.getText()));
                        dialog.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                });
                //adds styles
                HBox hBox = new HBox(lobbyName, confirmCreate);
                hBox.getStylesheets().add(getClass().getResource("/css_files/launcher.css").toExternalForm());

                Scene scene = new Scene(hBox);
                dialog.setWidth(300);
                dialog.setHeight(90);
                dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        createIsPressed = false;
                    }
                });
                dialog.setScene(scene);
                dialog.show();
                createIsPressed = true;
            }

        });
        buttons.getChildren().addAll(create);
        this.getChildren().addAll(sessions, buttons);

    }
}
