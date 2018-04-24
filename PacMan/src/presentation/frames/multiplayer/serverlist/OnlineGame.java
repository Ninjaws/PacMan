package presentation.frames.multiplayer.serverlist;

import java.awt.*;
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
