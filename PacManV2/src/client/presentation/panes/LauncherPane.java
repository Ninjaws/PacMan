package client.presentation.panes;

import javafx.scene.layout.BorderPane;

public class LauncherPane extends BorderPane {

    public LauncherPane() {
        setId("launcher-pane");
        this.setTop(new TopPane());
        this.setCenter(new LoginPane());
    }
}

