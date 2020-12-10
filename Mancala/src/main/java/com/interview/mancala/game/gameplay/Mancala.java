package com.interview.mancala.game.gameplay;

class Mancala extends Pit {


    Mancala(Player player) {
        super(player);
        this.setStoneCount(0);
    }

    @Override
    Pit nextPitForPlayer(Player player) {
        return getNextPit();
    }

}
