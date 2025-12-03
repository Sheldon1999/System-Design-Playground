package org.ankurGangwar.systemDesign.model;

import org.ankurGangwar.systemDesign.model.playerPiece.PlayerPiece;

public class Player {
    private String playerId;
    private PlayerPiece playerPiece;

    public Player(String playerId, PlayerPiece playerPiece) {
        this.playerId = playerId;
        this.playerPiece = playerPiece;
    }

    public String getPlayerId() {
        return playerId;
    }

    public PlayerPiece getPlayerPiece() {
        return playerPiece;
    }
}
