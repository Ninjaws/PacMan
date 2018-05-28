package data.launcher;

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

    public void addPlayer(User user) {
        userNames.add(user.getUserName());
    }

    public synchronized void addPlayer(String userName){
        userNames.add(userName);
    }

    public synchronized void removePlayer(String userName) {
        userNames.remove(userName);
    }

    public synchronized Conversation getConversation() {
        return conversation;
    }

    public synchronized void addMessage(Message message){
        conversation.addMessage(message);
    }

    public synchronized String getLobbyName() {
        return lobbyName;
    }

    public synchronized List<String> getPlayers(){
        return userNames;
    }
}
