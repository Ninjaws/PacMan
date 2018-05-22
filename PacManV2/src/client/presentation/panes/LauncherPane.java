package client.presentation.panes;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class LauncherPane extends BorderPane {

    private static LauncherPane instace;
    public LauncherPane() {
        instace = this;
        setId("launcher-pane");
        this.setTop(new TopPane());
        this.setCenter(new LoginPane());
    }

    public static void setNewCenter(Node node){
        instace.setCenter(node);
    }
}




