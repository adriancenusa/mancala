package com.bol.interview.mancala.game.gameplay;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
class GameBoardState {
    Player playerOne;
    Player playerTwo;

    void reset() {
        playerOne.getPocketList().forEach(pocket -> pocket.setStoneCount(6));
        playerTwo.getPocketList().forEach(pocket -> pocket.setStoneCount(6));
        playerOne.getMancala().setStoneCount(0);
        playerTwo.getMancala().setStoneCount(0);
    }

    boolean isGameFinished() {
        return playerOne.hasNoMoreAvailableMoves() || playerTwo.hasNoMoreAvailableMoves();
    }


    int getPlayerOneScore() {
        return getScore(playerOne);
    }

    int getPlayerTwoScore() {
        return getScore(playerTwo);
    }

    private int getScore(Player player) {
        return player.getMancala().getStoneCount();
    }

}
