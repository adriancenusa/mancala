package com.bol.interview.mancala.game.gameplay;


import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@SessionScope
@Component
class GamePlay {

    private GameBoardState gameBoardState;
    private String winnerMessage;
    private Player playerOnTurn;

    GamePlay() {
        this.gameBoardState = GameInitializer.initializeGame();
        this.playerOnTurn = gameBoardState.getPlayerOne();
    }


    void play(int pocketIndex) throws PlayNotPossibleException {
        isPlayPossible(pocketIndex);
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
        pit.handleLastStone(playerOnTurn);

        if (gameBoardState.isGameFinished()) {
            winnerMessage = createWinnerMessage(gameBoardState);
        }

        if (pit.shouldChangeTurn()) {
            playerOnTurn = getNextPlayer(playerOnTurn);
        }
    }

    private void isPlayPossible(int pocketIndex) throws PlayNotPossibleException {
        if (this.getGameBoardState().isGameFinished()) {
            throw new PlayNotPossibleException("Cannot play after the game has ended, reset to start a new game");
        }
        if (pocketIndex < 0 || pocketIndex > playerOnTurn.getPocketList().size() - 1) {
            throw new PlayNotPossibleException("Please use UI to play.");
        }
        if (getPlayerOnTurn().getPocketList().get(pocketIndex).getStoneCount() == 0) {
            throw new PlayNotPossibleException("Cannot play an empty pocket, pick one with stones in it");
        }
    }

    private String createWinnerMessage(GameBoardState gameBoardState) {
        int playerOneScore = gameBoardState.getPlayerOneScore();
        int playerTwoScore = gameBoardState.getPlayerTwoScore();

        if (playerOneScore == playerTwoScore) {
            return "This game was a draw";
        }
        if (playerOneScore > playerTwoScore) {
            return "Player ONE won the game";
        }
        return "Player TWO won the game";
    }


    private Player getNextPlayer(Player playerOnTurn) {
        if (playerOnTurn == gameBoardState.getPlayerOne())
            return gameBoardState.getPlayerTwo();
        return gameBoardState.getPlayerOne();
    }

}
