package com.gopal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player {
    private String playerName;
    private String playerCoin;
    private List<PlayerMove> playerMoves;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerCoin() {
        return playerCoin;
    }

    public void setPlayerCoin(String playerCoin) {
        this.playerCoin = playerCoin;
    }

    public List<PlayerMove> getPlayerMoves() {
        this.playerMoves.stream()
                .sorted(Comparator.comparing(PlayerMove::getRowNum))
                .toList();

        return playerMoves;
    }

    public void setPlayerMoves(List<PlayerMove> playerMoves) {
        this.playerMoves = playerMoves;
    }

    public Player(String playerName, String playerCoin) {
        this.playerName = playerName;
        this.playerCoin = playerCoin;
        this.playerMoves = new ArrayList<PlayerMove>();
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", playerCoin='" + playerCoin + '\'' +
                ", playerMoves=" + playerMoves +
                '}';
    }
}
