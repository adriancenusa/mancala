package com.interview.mancala.game.gameplay;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract class Pit {
    private int stoneCount;
    private Player player;
    private Pit nextPit;


    Pit(Player player) {
        this.player = player;
    }

    void increaseStoneCount() {
        stoneCount++;
    }

    void increaseStoneCountWith(int stonesToAdd) {
        stoneCount += stonesToAdd;
    }

    void removeAllStones(){
        stoneCount = 0;
    }

    abstract Pit nextPitForPlayer(Player player);

}
