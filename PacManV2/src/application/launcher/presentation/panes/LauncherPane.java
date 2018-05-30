package application.launcher.presentation.panes;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class LauncherPane extends BorderPane {

    private static LauncherPane instance;

    public static LauncherPane getInstance(){
        if (instance == null)
            instance = new LauncherPane();

        return instance;
    }

    private LauncherPane() {
        instance = this;

        setId("application.launcher-pane");

        this.setTop(new TopPane());
        this.setCenter(new LoginPane());

    }

    public static void setNewCenter(Node node){
        instance.setCenter(node);
    }
}




