package application.launcher.presentation.panes;

import application.launcher.data.LobbyData;
import application.launcher.data.UserData;
import application.launcher.presentation.listview.LobbyUserItem;
import application.networking.client.data.Storage;
import com.jfoenix.controls.JFXButton;
import application.launcher.data.Conversation;
import application.launcher.data.Message;
import application.networking.packets.lobby.PacketLobbyLeave;
import application.networking.packets.message.PacketMessageSend;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LobbyPane extends VBox {
    private String name;
    private TextArea textArea = new TextArea();
    private TextField textField = new TextField();
    private ListView<LobbyUserItem> usersView = new ListView();
    private JFXButton sendButton = new JFXButton("Send");

    public LobbyPane(String name) {
        this.name = name;
        this.setId("lobby-pane");
        HBox top = new HBox();

        JFXButton leave = new JFXButton("Leave");
        leave.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                Storage.getInstance().getObjectToServer().writeObject(new PacketLobbyLeave(name, Storage.getInstance().getUsername()));
                LauncherPane.setNewCenter(new LobbiesPane());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        //Click button
        sendButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                if (textField.getText().equals(""))
                    return;

                Storage.getInstance().getObjectToServer().writeObject(new PacketMessageSend(name, new Message(Storage.getInstance().getUsername(), textField.getText())));
                textField.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //Enter key
        textField.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (KeyCode.ENTER.equals(event.getCode())) {
                try {
                    if (textField.getText().equals(""))
                        return;

                    Storage.getInstance().getObjectToServer().writeObject(new PacketMessageSend(name, new Message(Storage.getInstance().getUsername(), textField.getText())));
                    textField.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Text title = new Text("Name: " + name);
        title.setId("lobby-title");

        HBox chatBox = new HBox();
        chatBox.getChildren().addAll(textField, sendButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(textArea, chatBox);

        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(leave, usersView);

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(vBox,vBox2);

        usersView.setId("user-listview");

        textArea.setEditable(false);
        textArea.setWrapText(true);

        top.getChildren().addAll(title);
        this.getChildren().addAll(top, hbox2);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            try {

                double top1 = textArea.getScrollTop();
                StringBuilder stringBuilder = new StringBuilder();
                LobbyData lobbyData = Storage.getInstance().getApplicationData().getLauncherData().getLobby(name);

                Conversation conversation = lobbyData.getConversation();
                //   textArea.setText(conversation.toString());

                conversation.getMessages().forEach(message -> {
                    stringBuilder.append("[" + message.getDateTime().getHour() + ":" + message.getDateTime().getMinute() + "] " +
                            message.getAuthor() + ": " + message.getText() + "\n");
                });

                textArea.setText(stringBuilder.toString());
                textArea.setScrollTop(top1 + textArea.getHeight());

                usersView.getItems().clear();
                for(String userName : lobbyData.getPlayers()){
                    UserData userData = Storage.getInstance().getApplicationData().getUser(userName);
                    usersView.getItems().addAll(new LobbyUserItem(userData.getUserName(), name, userData.isPacMan(), userData.isReady()));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }




}
