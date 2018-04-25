package presentation.frames.multiplayer.serverlist;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class OnlineGame {
    private String name;
    private int playerCount;
    private int positionInSort;
    private int frameWidth;
    private int frameHeight;

    public OnlineGame(String name, int frameWidth, int frameHeight) {
        this.name = name;
        playerCount = 1;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    public void increasePlayerCount() {
        playerCount++;
    }

    public void decreasePlayerCount() {
        playerCount--;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        g2d.draw(new Rectangle2D.Double(frameWidth/8, positionInSort *32, 32,32));
        g2d.drawString(name,frameWidth/8,positionInSort * 32);
    }

    public void update(int frameWidth, int frameHeight, ArrayList<OnlineGame> onlineGames) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

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
