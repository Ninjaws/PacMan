package data;

import tiled.Map;

/**
 * @author Ian Vink
 */

public class Game {

    private static Game instance;

    private Map map;

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
}
