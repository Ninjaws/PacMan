package application.launcher.presentation.listview;

import com.jfoenix.controls.JFXButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class LobbyUserItem extends VBox {
    private String name;
    private boolean isPacMan;
    private boolean isReady = false;

    public LobbyUserItem(String name, boolean isPacMan) {
        this.name = name;
        this.isPacMan = isPacMan;

        Text nameText = new Text(name);
        nameText.setId("lobby-user-name");

        /*Text isPacManText = new Text();
        isPacManText.setId("is-pac-man-text");
        if(isPacMan)
            isPacManText.setText("Is PacMan!");
        else
            isPacManText.setText("Is a Ghost!");*/


        JFXButton selectButton = new JFXButton("Ghost");
        selectButton.setId("select-button");
        selectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            if(isReady){

            }
            else if(selectButton.getText().equals("Ghost")){
                selectButton.setText("PacMan");
                this.isPacMan = true;
            }
            else {
                selectButton.setText("Ghost");
                this.isPacMan = false;
            }
        });


        //ready button
        JFXButton readyButton = new JFXButton();
        readyButton.getStyleClass().clear();
        readyButton.getStyleClass().add("ready-button-red");
        readyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(readyButton.getStyleClass().get(0).equals("ready-button-red")){
                isReady = true;
                readyButton.getStyleClass().removeAll("ready-button-red");
                readyButton.getStyleClass().add("ready-button-green");
            }
            else{
                isReady = false;
                readyButton.getStyleClass().removeAll("ready-button-green");
                readyButton.getStyleClass().add("ready-button-red");
            }
        });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(selectButton,readyButton);

        this.getChildren().addAll(nameText, buttons);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPacMan() {
        return isPacMan;
    }

    public void setPacMan(boolean pacMan) {
        isPacMan = pacMan;
    }
}
