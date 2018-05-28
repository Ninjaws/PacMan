package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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

    public void removePlayer(User user) {
        Iterator<String> it = userNames.iterator();
        while (it.hasNext()) {
            String t = it.next();
        }
    }

    public synchronized void addMessage(Message message){
        conversation.addMessage(message);
    }

    public String getLobbyName() {
        return lobbyName;
    }
}
