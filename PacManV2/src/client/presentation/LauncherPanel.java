package client.presentation;

import client.data.Storage;
import data.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LauncherPanel extends JPanel implements ActionListener {

    private TextArea textArea;

    public LauncherPanel() {

        JPanel content = new JPanel(new BorderLayout());

        JPanel southPanel = new JPanel(new FlowLayout());
        JPanel centerPanel = new JPanel(new FlowLayout());

        textArea = new TextArea();
        centerPanel.add(textArea);

        JLabel label = new JLabel("Type a message");
        TextField tf = new TextField();
        JButton button = new JButton("Send");
        southPanel.add(label);
        southPanel.add(tf);
        southPanel.add(button);


        button.addActionListener(e -> {
            if (!tf.getText().equals("")) {
                try {
                    Message message = new Message(Storage.getInstance().getUsername(), tf.getText());
                    System.out.println(message);
                    Storage.getInstance().getObjectToServer().writeObject(message);


                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //     Storage.getInstance().getConversation().addMessage(new Message(Storage.getInstance().getUsername(), tf.getText()));
            }
        });


        content.add(centerPanel, BorderLayout.CENTER);
        content.add(southPanel, BorderLayout.SOUTH);

        add(content);


        new Timer(1000 / 2, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setText(Storage.getInstance().getConversation().toString());
    }

    public void setText(String text) {
        this.textArea.setText(text);
        ;
    }
}