package data;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String author;
    private LocalDateTime dateTime;
    private String text;

    public Message(String author,String text) {
        this.author = author;
        this.dateTime = LocalDateTime.now();
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "author='" + author + '\'' +
                ", dateTime=" + dateTime +
                ", text='" + text + '\'' +
                '}' + '\n';
    }
}