package application.networking.packets.game;

import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketGameStart implements Packet, Serializable {

    private boolean inGame;
    private String gameName;

    public PacketGameStart(boolean inGame, String gameName) {
        this.inGame = inGame;
        this.gameName = gameName;
    }

    public boolean isInGame() {
        return inGame;
    }

    public String getGameName() {
        return gameName;
    }
}
