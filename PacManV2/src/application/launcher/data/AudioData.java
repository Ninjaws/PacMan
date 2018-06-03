package application.launcher.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AudioData implements Serializable {
    public String lobbyName = new String();
    private HashMap<byte[], String> audioMap = new HashMap<>();

    public AudioData() {

    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public void addAudioBytes(String name, byte audioByte[]){
        audioMap.put(audioByte, name);
    }

    public void removeAudioBytes(){
        audioMap.clear();
    }

    public HashMap<byte[], String> getAudioMap() {
        return audioMap;
    }

    public void setAudioMap(HashMap<byte[], String> audioMap) {
        this.audioMap = audioMap;
    }

}

