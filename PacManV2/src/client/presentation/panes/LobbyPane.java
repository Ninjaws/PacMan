package client.presentation.panes;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;

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
            frame.setSize(new Dimension(800,800));
            frame.setVisible(true);
        });

        JFXButton leave = new JFXButton("Leave");
        leave.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            LauncherPane.setNewCenter(new LobbiesPane());
        });

        Text title = new Text("Name: " + name);
        title.setId("lobby-title");

        HBox chatField = new HBox();
        chatField.getChildren().addAll(textField,sendButton);

        top.getChildren().addAll(title,launch, leave);
        this.getChildren().addAll(top,textArea,chatField);
    }
}
