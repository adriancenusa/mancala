package com.interview.mancala.game.gameplay;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@SessionScope
@Component
class GamePlay {

    private GameBoardState gameBoardState;
    private String winnerMessage;
    private Player playerOnTurn;

    @Autowired
    private PlayPossibleVerifier playPossibleVerifier;

    GamePlay() {
        this.gameBoardState = GameInitializer.initializeGame();
        this.playerOnTurn = gameBoardState.getPlayerOne();
    }


    void play(int pocketIndex) throws PlayNotPossibleException {
        playPossibleVerifier.isPlayPossible(this, pocketIndex);
        Pocket playedPocket = playerOnTurn.getPocketList().get(pocketIndex);
        int nrOfStonesToPlay = playedPocket.getStoneCount();
        playedPocket.removeAllStones();

        Pit nextPit = playedPocket;
        for (int i = 0; i < nrOfStonesToPlay; i++) {
            nextPit = nextPit.nextPitForPlayer(playerOnTurn);
            nextPit.increaseStoneCount();
        }

        finishTurn(nextPit);
    }


    void reset() {
        gameBoardState.reset();
        playerOnTurn = gameBoardState.getPlayerOne();
        winnerMessage = null;
    }


    private void finishTurn(Pit pit) {
        if (pit instanceof Pocket) {
            handleLastStone((Pocket) pit);
            playerOnTurn = gameBoardState.getNextPlayer(playerOnTurn);
        }
        winnerMessage = gameBoardState.getEndState().map(EndState::getMessage).orElse(null);
    }


    private void handleLastStone(Pocket pocket) {
        if (this.playerOnTurn == pocket.getPlayer()) {
            if (pocket.getStoneCount() == 1) {
                pocket.getPlayer().getMancala().increaseStoneCountWith(pocket.getCorrespondingOponentPocket().getStoneCount());
                pocket.getPlayer().getMancala().increaseStoneCount();
                pocket.getCorrespondingOponentPocket().removeAllStones();
                pocket.removeAllStones();
            }
        }
    }


}
