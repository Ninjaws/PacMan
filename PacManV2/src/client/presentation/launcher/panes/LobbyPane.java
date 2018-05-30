package client.presentation.launcher.panes;

import client.data.Storage;
import client.presentation.launcher.listview.LobbyListViewItem;
import com.jfoenix.controls.JFXButton;
import data.packets.Packet;
import data.launcher.Conversation;
import data.launcher.Message;
import data.packets.lobby.PacketLobbyLeave;
import data.packets.message.PacketMessageSend;
import enums.Command;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class LobbyPane extends VBox {
    private String name;
    private TextArea textArea = new TextArea();
    private TextField textField = new TextField();
    private JFXButton sendButton = new JFXButton("Send");

    public LobbyPane(String name) {
        this.name = name;
        this.setId("lobby-pane");
        HBox top = new HBox();

        JFXButton launch = new JFXButton("Launch");
        launch.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            JFrame frame = new JFrame("PacMan");
            frame.setSize(new Dimension(800, 800));
            frame.setVisible(true);
        });

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

        textArea.setEditable(false);

        top.getChildren().addAll(title, launch, leave);
        this.getChildren().addAll(top, textArea, chatBox);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            try {

                double top1 = textArea.getScrollTop();
                StringBuilder stringBuilder = new StringBuilder();

                Conversation conversation = Storage.getInstance().getApplicationData().getLauncherData().getLobby(name).getConversation();
                //   textArea.setText(conversation.toString());

                conversation.getMessages().forEach(message -> {
                    stringBuilder.append("[" + message.getDateTime().getHour() + ":" + message.getDateTime().getMinute() + "] " +
                            message.getAuthor() + ": " + message.getText() + "\n");
                });
                textArea.setText(stringBuilder.toString());
                textArea.setScrollTop(top1 + textArea.getHeight());



            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }


}
