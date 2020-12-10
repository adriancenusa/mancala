package com.interview.mancala.game.gameplay;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Getter
@Setter
class GameBoardState {
    private final Player playerOne;
    private final Player playerTwo;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<Player, Player> nextPlayerMap = new HashMap<>();

    GameBoardState(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        nextPlayerMap.put(playerOne, playerTwo);
        nextPlayerMap.put(playerTwo, playerOne);
    }


    void reset() {
        playerOne.getPocketList().forEach(pocket -> pocket.setStoneCount(6));
        playerTwo.getPocketList().forEach(pocket -> pocket.setStoneCount(6));
        playerOne.getMancala().setStoneCount(0);
        playerTwo.getMancala().setStoneCount(0);
    }

    Optional<EndState> getEndState() {
        if (isGameFinished()) {
            return Optional.of(getWinner());
        }
        return Optional.empty();
    }

    boolean isGameFinished() {
        return playerOne.hasNoMoreAvailableMoves() || playerTwo.hasNoMoreAvailableMoves();
    }


    private EndState getWinner() {
        int playerOneScore = getScore(playerOne);
        int playerTwoScore = getScore(playerTwo);

        if (playerOneScore == playerTwoScore) {
            return EndState.DRAW;
        }
        if (playerOneScore > playerTwoScore) {
            return EndState.PLAYER_ONE_WON;
        }
        return EndState.PLAYER_TWO_WON;
    }


    int getPlayerOneScore() {
        return getScore(playerOne);
    }

    int getPlayerTwoScore() {
        return getScore(playerTwo);
    }

    Player getNextPlayer(Player playerOnTurn) {
        return nextPlayerMap.get(playerOnTurn);
    }

    private int getScore(Player player) {
        return player.getMancala().getStoneCount();
    }


}
