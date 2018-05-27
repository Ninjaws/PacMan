package client;

import client.data.Storage;
import client.presentation.panes.LauncherPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientMain extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private static Scene mainScene;
    private static ClientMain instance;
    public ClientMain() {
        instance = this;
        mainScene = new Scene(LauncherPane.getInstance(),0,0);
        addStyle("css_files/launcher.css");

        Storage.getInstance();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PacMan");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void clearStyles() {
        mainScene.getStylesheets().clear();
    }

    public static void addStyle(String fileName) {
        mainScene.getStylesheets().add(getInstance().getClass().getResource("/" + fileName).toExternalForm());
    }

    public static ClientMain getInstance() {
        if(instance == null)
            instance = new ClientMain();
        return instance;
    }
}