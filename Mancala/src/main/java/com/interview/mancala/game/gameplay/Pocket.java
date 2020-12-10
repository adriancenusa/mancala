package com.interview.mancala.game.gameplay;

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

    Pit nextPitForPlayer(Player playerOnTurn) {
        if (this.getNextPit() instanceof Mancala && this.getNextPit().getPlayer() != playerOnTurn) {
            return this.getNextPit().getNextPit();
        }
        return this.getNextPit();
    }

}
