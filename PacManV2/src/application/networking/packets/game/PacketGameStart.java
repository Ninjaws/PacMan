package application.networking.packets.game;

import application.networking.packets.Packet;

import java.io.Serializable;

public class PacketGameStart implements Packet, Serializable {

    private boolean inGame;
    private int gameId;

    public PacketGameStart(boolean inGame, int gameId) {
        this.inGame = inGame;
        this.gameId = gameId;
    }

    public boolean isInGame() {
        return inGame;
    }

    public int getGameId() {
        return gameId;
    }
}
