package com.interview.mancala.game.gameplay;

import org.springframework.stereotype.Component;

@Component
class PlayPossibleVerifier {

    void isPlayPossible(GamePlay gamePlay , int pocketIndex ) throws PlayNotPossibleException {
        if (gamePlay.getGameBoardState().isGameFinished()) {
            throw new PlayNotPossibleException("Cannot play after the game has ended, reset to start a new game");
        }
        if (pocketIndex < 0 || pocketIndex > gamePlay.getPlayerOnTurn().getPocketList().size() - 1) {
            throw new PlayNotPossibleException("Please use UI to play.");
        }
        if (gamePlay.getPlayerOnTurn().getPocketList().get(pocketIndex).getStoneCount() == 0) {
            throw new PlayNotPossibleException("Cannot play an empty pocket, pick one with stones in it");
        }
    }
}
