package data;

import tiled.Map;

/**
 * @author Ian Vink
 */

public class Game {

    private static Game instance;

    private Map map;

    private int screenWidth;
    private int screenHeight;

    private Game(){

    }

    public static Game getInstance(){
        if (instance == null)
            instance = new Game();

        return instance;
    }




    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setScreenDimensions(int width, int height){
        this.screenHeight = height;
        this.screenWidth = width;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
