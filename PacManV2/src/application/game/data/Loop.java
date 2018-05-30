package application.game.data;

import java.awt.*;

/**
 * @author Ian Vink
 */

public class Loop {

    private String name;

    private Point entrance;
    private Point exit;

    public Loop(String name, Point entrance, Point exit) {
        this.name = name;
        this.entrance = entrance;
        this.exit = exit;
    }

    public String getName() {
        return name;
    }

    public Point getEntrance() {
        return entrance;
    }

    public void setEntrance(Point entrance) {
        this.entrance = entrance;
    }

    public Point getExit() {
        return exit;
    }

    public void setExit(Point exit) {
        this.exit = exit;
    }
}
