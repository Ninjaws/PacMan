package client.presentation.panes;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LobbiesPane extends HBox {
    public LobbiesPane() {
        ListView sessions = new ListView();
        sessions.getItems().addAll();

        VBox buttons = new VBox();
        JFXButton refresh = new JFXButton("Refresh");
        JFXButton join = new JFXButton("Join");

        refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println(true));
        join.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println(true));

        join.setId("join-button");
        refresh.setId("refresh-button");

        buttons.getChildren().addAll(refresh,join);
        this.getChildren().addAll(sessions, buttons);
    }
}
