package com.bol.interview.mancala.game.gameplay;

import lombok.Getter;
import lombok.Setter;

class Pocket extends Pit {

    @Setter
    @Getter
    Pocket correspondingOponentPocket;


    Pocket(Player player) {
        super(player);
        this.setStoneCount(6);
    }


    @Override
    void handleLastStone(Player playerOnTurn) {
        if (playerOnTurn == this.getPlayer()) {
            if (this.getStoneCount() == 1) {
                this.getPlayer().getMancala().increaseStoneCountWith(this.getCorrespondingOponentPocket().getStoneCount());
                this.getPlayer().getMancala().increaseStoneCount();
                this.getCorrespondingOponentPocket().removeAllStones();
                this.removeAllStones();
            }
        }
    }


    @Override
    boolean shouldChangeTurn() {
        return true;
    }

    Pit nextPitForPlayer(Player playerOnTurn) {
        if (this.getNextPit() instanceof Mancala && this.getNextPit().getPlayer() != playerOnTurn) {
            return this.getNextPit().getNextPit();
        }
        return this.getNextPit();
    }

}
