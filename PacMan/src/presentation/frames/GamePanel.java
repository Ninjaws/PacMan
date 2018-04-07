package presentation.frames;

import data.Game;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ian Vink
 */

public class GamePanel extends JPanel {

    Game game;


    public GamePanel(){
        game = Game.getInstance();
        setBackground(Color.BLACK);

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        game.getMap().draw(g2d);
    }
}
