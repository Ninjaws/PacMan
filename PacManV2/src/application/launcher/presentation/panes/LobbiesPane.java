package application.launcher.presentation.panes;

import application.networking.client.data.Storage;
import application.launcher.presentation.listview.LobbyListViewItem;
import com.jfoenix.controls.JFXButton;
import application.networking.packets.lobby.PacketLobbyCreate;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.IOException;

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



        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            try {

                sessions.getItems().clear();
                Storage.getInstance().getApplicationData().getLauncherData().getLobbies().forEach(lobbyData -> {
                    sessions.getItems().add(new LobbyListViewItem(lobbyData.getLobbyName()));
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
/*
        JFXButton refresh = new JFXButton("Refresh");
        refresh.getStyleClass().add("lobby-button");

        //   refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println());
        refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {


        });
        */



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
                        System.out.println(lobbyName.getText());
                        Storage.getInstance().getObjectToServer().writeObject(new PacketLobbyCreate(lobbyName.getText(), Storage.getInstance().getUsername()));

                        while(Storage.getInstance().getApplicationData().getLauncherData().getLobby(lobbyName.getText()) == null){
                            //Wait
                           // System.out.println("Waiting");
                        }
                        System.out.println("Lobby received");


                        LauncherPane.setNewCenter(new LobbyPane(lobbyName.getText()));
                        dialog.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                //adds styles
                HBox hBox = new HBox(lobbyName, confirmCreate);
                hBox.getStylesheets().add(getClass().getResource("/launcher/css_files/launcher.css").toExternalForm());

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
