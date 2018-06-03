package application.launcher.presentation.panes;

import application.launcher.data.*;
import application.launcher.presentation.listview.LobbyUserItem;
import application.networking.client.data.Storage;
import application.networking.packets.game.PacketGameStart;
import application.networking.packets.launcher.lobby.PacketLobbyLeave;
import application.networking.packets.launcher.lobby.PacketLobbySound;
import application.networking.packets.launcher.lobby.PacketSoundPlayed;
import application.networking.packets.launcher.message.PacketMessageSend;
import application.testgame.presentation.frames.GameFrame;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LobbyPane extends VBox {
    private String name;
    private TextArea textArea = new TextArea();
    private TextField textField = new TextField();
    private ListView<LobbyUserItem> usersView = new ListView();
    private JFXButton sendButton = new JFXButton("Send");

    private boolean isStarted = false;
    private boolean stopCapture = true;
    private ByteArrayOutputStream byteArrayOutputStream;
    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;
    private AudioInputStream audioInputStream;
    private SourceDataLine sourceDataLine;


    public LobbyPane(String name) {
        this.name = name;
        this.setId("lobby-pane");
        HBox top = new HBox();
        JFXButton leave = new JFXButton("Leave");
        leave.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                Storage.getInstance().getObjectToServer().writeObject(new PacketLobbyLeave(name, Storage.getInstance().getUsername()));
                LauncherPane.setNewCenter(new LobbiesPane());
                targetDataLine.close();
            } catch (IOException e) {
                //e.printStackTrace();
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

        JFXButton recordButton = new JFXButton("Capture");
        recordButton.getStyleClass().clear();
        recordButton.getStyleClass().add("record-button-red");
        recordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(stopCapture){
                recordButton.getStyleClass().clear();
                recordButton.getStyleClass().add("record-button-green");
                stopCapture = false;
                captureAudio();
            }
            else{
                recordButton.getStyleClass().clear();
                recordButton.getStyleClass().add("record-button-red");
                stopCapture = true;
                playAudio();
            }
        });


        Text title = new Text("Name: " + name);
        title.setId("lobby-title");

        HBox chatBox = new HBox();
        chatBox.getChildren().addAll(textField, sendButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(textArea, chatBox);

        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(leave, recordButton, usersView);

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(vBox,vBox2);

        usersView.setId("user-listview");

        textArea.setEditable(false);
        textArea.setWrapText(true);

        top.getChildren().addAll(title);
        this.getChildren().addAll(top, hbox2);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            try {
                double top1 = textArea.getScrollTop();
                StringBuilder stringBuilder = new StringBuilder();
                LobbyData lobbyData = Storage.getInstance().getApplicationData().getLauncherData().getLobby(name);

                Conversation conversation = lobbyData.getConversation();
                //   textArea.setText(conversation.toString());

                conversation.getMessages().forEach(message -> {
                    stringBuilder.append("[" + message.getDateTime().getHour() + ":" + message.getDateTime().getMinute() + "] " +
                            message.getAuthor() + ": " + message.getText() + "\n");
                });

                textArea.setText(stringBuilder.toString());
                textArea.setScrollTop(top1 + textArea.getHeight());

                usersView.getItems().clear();
                for(String userName : lobbyData.getPlayers()){
                    UserData userData = Storage.getInstance().getApplicationData().getUser(userName);
                    usersView.getItems().addAll(new LobbyUserItem(userData.getUserName(), name, userData.isPacMan(), userData.isReady()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //works
    private void captureAudio() {
        try{
            Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            System.out.println("Available mixers: ");
            for(int cnt = 0; cnt < mixerInfo.length; cnt++){
                System.out.println(mixerInfo[cnt].getName());
            }

            audioFormat = getAudioFormat();
            if(!isStarted){
                isStarted = true;
                Mixer mixer = AudioSystem.getMixer(mixerInfo[3]);
                DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
                targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
            }


            targetDataLine.open(audioFormat);
            targetDataLine.start();
            CaptureThread captureThread = new CaptureThread();
            captureThread.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    class CaptureThread extends Thread{
        byte tempBuffer[] = new byte[10000];
        public void run(){
            byteArrayOutputStream = new ByteArrayOutputStream();
            try{
                while (!stopCapture) {
                    System.out.println("capturing");
                    int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
                    if (cnt > 0) {
                        byteArrayOutputStream.write(tempBuffer, 0, cnt);
                    }
                }
                Storage.getInstance().getObjectToServer().writeObject(new PacketLobbySound(Storage.getInstance().getUsername(),name, byteArrayOutputStream.toByteArray()));
                byteArrayOutputStream.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void playAudio() {
        try{
            new Thread(() ->{
                while (stopCapture){
                    try {
                        Thread.sleep(1000);
                        AudioData audioData = Storage.getInstance().getApplicationData().getLauncherData().getLobby(name).getAudioData();
                        for (byte[] bytes : audioData.getAudioMap().keySet()) {
                            if(!audioData.getAudioMap().get(bytes).equals(Storage.getInstance().getUsername())){
                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

                                audioInputStream = new AudioInputStream(byteArrayInputStream, audioFormat, bytes.length / audioFormat.getFrameSize());
                                AudioFormat audioFormat = getAudioFormat();
                                //is going to play the data
                                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
                                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                                sourceDataLine.open(audioFormat);
                                sourceDataLine.start();

                                Storage.getInstance().getObjectToServer().writeObject(new PacketSoundPlayed(name));
                                PlayThread playThread = new PlayThread();
                                playThread.start();
                            }
                        }
                    }
                    catch(InterruptedException | LineUnavailableException | IOException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    class PlayThread extends Thread {
        byte tempBuffer[] = new byte[10000];
        public void run(){
            try{
                System.out.println("Playing");
                int cnt;
                while((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1){
                    System.out.println("Playing");
                    if(cnt > 0)
                        sourceDataLine.write(tempBuffer, 0, cnt);
                }
                sourceDataLine.drain();
                sourceDataLine.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private AudioFormat getAudioFormat() {
        float sampleRate = 8000.0F;
        //8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;
        //8,16
        int channels = 1;
        return new AudioFormat(
                sampleRate,
                sampleSizeInBits,
                channels,
                true,
                true);
    }






}
