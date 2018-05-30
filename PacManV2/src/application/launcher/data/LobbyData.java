package application.launcher.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LobbyData implements Serializable {
    private String lobbyName;
    private List<String> userNames;
    private Conversation conversation;

    public LobbyData(String lobbyName) {
        this.lobbyName = lobbyName;
        userNames = new ArrayList<>();
        conversation = new Conversation();
    }

    public void addPlayer(UserData user) {
        userNames.add(user.getUserName());
    }

    public void addPlayer(String userName){
        userNames.add(userName);
    }

    public void removePlayer(String userName) {
        userNames.remove(userName);
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void addMessage(Message message){
        conversation.addMessage(message);
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public List<String> getPlayers(){
        return userNames;
    }
}
