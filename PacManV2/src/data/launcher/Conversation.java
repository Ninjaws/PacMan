package data.launcher;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
    private ArrayList<Message> messages = new ArrayList<>();

    public Conversation(){
    }

    public synchronized void addMessage(Message message){
        messages.add(message);
    }

    public synchronized ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public synchronized String toString() {
        return "Conversation{" +
                "messages=\n" + messages +
                '}';
    }
}