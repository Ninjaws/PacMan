package presentation.frames.multiplayer.serverlist;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * @author Jordy van Raalte
 */
public class OnlineGame {
    private String name;
    private int playerCount;
    private int positionInSort;
    private int frameWidth;

    public OnlineGame(String name, int frameWidth, int frameHeight) {
        this.name = name;
        playerCount = 1;
        this.frameWidth = frameWidth;

    }

    public void draw(Graphics2D g2d) {
        //draws a line between the onlinegames
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(10,(positionInSort + 1) * 32 + 20, frameWidth + 10, (positionInSort + 1) * 32 + 20);

        g2d.setFont(new JLabel().getFont().deriveFont(20f));
        //draws the name of the online game
        g2d.drawString("Game name: " + name,frameWidth/20,(positionInSort + 1) * 32 + 15);
        g2d.drawString("Players: " + playerCount + "/4",frameWidth/20 * 8,(positionInSort + 1) * 32 + 15);
    }

    public void update(int frameWidth, ArrayList<OnlineGame> onlineGames) {
        this.frameWidth = frameWidth;
        int index = 0;
        for(OnlineGame onlineGame : onlineGames) {
            if(onlineGame.equals(this)) {
                positionInSort = index;
                break;
            }
            index++;
        }
    }


}
