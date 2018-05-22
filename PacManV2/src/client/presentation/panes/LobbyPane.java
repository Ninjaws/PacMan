package client.presentation.panes;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LobbyPane extends VBox {
    private TextArea textArea = new TextArea();
    private TextField textField = new TextField();
    private JFXButton sendButton = new JFXButton("Send");
    public LobbyPane() {
        this.setId("lobby-pane");
        HBox top = new HBox();

        JFXButton launch = new JFXButton("Launch");
        JFXButton leave = new JFXButton("Leave");

        HBox chatField = new HBox();

        chatField.getChildren().addAll(textField,sendButton);

        top.getChildren().addAll(launch, leave);

        this.getChildren().addAll(top,textArea,chatField);
    }
}
