package com.bol.interview.mancala.game.gameplay;

class Mancala extends Pit {


    Mancala(Player player) {
        super(player);
        this.setStoneCount(0);
    }

    @Override
    Pit nextPitForPlayer(Player player) {
        return getNextPit();
    }

    @Override
    void handleLastStone(Player playerOnTurn) {
        //do nothing. No changes to the gameplay when last stone lands on mancala
    }

    @Override
    boolean shouldChangeTurn() {
        return false;
    }


}
