package data.launcher;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
    private ArrayList<Message> messages = new ArrayList<>();

    public Conversation(){
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public  ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public  String toString() {
        return "Conversation{" +
                "messages=\n" + messages +
                '}';
    }
}