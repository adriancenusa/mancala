package com.interview.mancala.game.gameplay;

public enum EndState {
    PLAYER_ONE_WON("Player ONE won the game"), PLAYER_TWO_WON("Player TWO won the game"), DRAW("This game was a draw");

    private String message;

    EndState(String s) {
        message = s;
    }

    public String getMessage() {
        return message;
    }
}
