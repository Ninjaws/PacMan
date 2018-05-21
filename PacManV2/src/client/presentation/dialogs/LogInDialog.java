package client.presentation.dialogs;

import javax.swing.*;
import java.awt.*;

public class LogInDialog extends JDialog {

    private String name;
    private DialogResult result;


    protected JPanel mainPanel = new JPanel(new BorderLayout());


    public LogInDialog(String title) {
        setTitle(title);

        initialize();
        mainPanel.add(buildActionPanel(), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);

    }

    private JPanel buildActionPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        TextField tf = new TextField();
        tf.setColumns(15);


        JLabel label = new JLabel("Choose a name");
        inputPanel.add(label);
        inputPanel.add(tf);


        JButton save = new JButton("Done");
        save.addActionListener(e -> {
            name = tf.getText();
            result = new DialogResult(name);

            if(!result.getName().equals(""))
                dispose();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(save);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }


    private void initialize() {
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setContentPane(mainPanel);
    }


    public DialogResult getResult() {
        setVisible(true);
        return result;
    }


    public class DialogResult {
        private String name;

        private DialogResult(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}