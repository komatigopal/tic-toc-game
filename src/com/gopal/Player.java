package com.gopal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player {
    private String playerName;
    private String players;
    private List<PlayerMove> playerMoves;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
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

    public Player(String playerName, String players) {
        this.playerName = playerName;
        this.players = players;
        this.playerMoves = new ArrayList<PlayerMove>();
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", players='" + players + '\'' +
                ", playerMoves=" + playerMoves +
                '}';
    }
}
