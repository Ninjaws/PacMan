package application.launcher.presentation.listview;

import application.launcher.data.Conversation;
import application.launcher.data.Message;
import application.networking.client.data.Storage;
import application.networking.packets.message.PacketMessageSend;
import application.networking.packets.user.PacketIsPacMan;
import application.networking.packets.user.PacketIsReady;
import com.jfoenix.controls.JFXButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;


public class LobbyUserItem extends VBox {
    private String name;
    private String lobbyName;
    private boolean isPacMan;
    private boolean isReady;

    public LobbyUserItem(String name, String lobbyName, boolean isPacMan, boolean isReady) {
        this.name = name;
        this.lobbyName = lobbyName;
        this.isPacMan = isPacMan;
        this.isReady = isReady;



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
        if(isPacMan)
            selectButton.setText("PacMan");
        else
            selectButton.setText("Ghost");

        selectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            if(this.isReady){

            }
            else if(selectButton.getText().equals("Ghost") && !Storage.getInstance().getApplicationData().isThereAPacMan(lobbyName, name)){
                this.isPacMan = true;
                try {
                    Storage.getInstance().getObjectToServer().writeObject(new PacketIsPacMan(name, true));
                    Storage.getInstance().getApplicationData().getUser(name).setPacMan(true);
                    selectButton.setText("PacMan");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(Storage.getInstance().getApplicationData().isThereAPacMan(lobbyName, name)){
                try {
                    Storage.getInstance().getObjectToServer().writeObject(new PacketMessageSend(lobbyName,
                            new Message(
                                    "SERVER",
                                    "PACMAN IS ALREADY CHOSEN, LET THE PACMAN USER DESELECT PACMAN OR ACCEPT IT.")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                this.isPacMan = false;
                try {
                    Storage.getInstance().getApplicationData().getUser(name).setPacMan(false);
                    Storage.getInstance().getObjectToServer().writeObject(new PacketIsPacMan(name,false));
                    selectButton.setText("Ghost");
                } catch (IOException e) {
                    e.printStackTrace();
                };

            }
        });


        //ready button
        JFXButton readyButton = new JFXButton();
        if(isReady){
            readyButton.getStyleClass().removeAll("ready-button-red");
            readyButton.getStyleClass().add("ready-button-green");
        }
        else {
            readyButton.getStyleClass().removeAll("ready-button-green");
            readyButton.getStyleClass().add("ready-button-red");
        }
        readyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(!isReady){
                this.isReady = true;
                try {
                    Storage.getInstance().getObjectToServer().writeObject(new PacketIsReady(name, true));
                    Storage.getInstance().getApplicationData().getUser(name).setReady(true);
                    readyButton.getStyleClass().removeAll("ready-button-red");
                    readyButton.getStyleClass().add("ready-button-green");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                this.isReady = false;
                try {
                    Storage.getInstance().getObjectToServer().writeObject(new PacketIsReady(name,false));
                    Storage.getInstance().getApplicationData().getUser(name).setReady(false);
                    readyButton.getStyleClass().removeAll("ready-button-green");
                    readyButton.getStyleClass().add("ready-button-red");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
