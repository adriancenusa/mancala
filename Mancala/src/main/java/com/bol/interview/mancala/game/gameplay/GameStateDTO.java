package com.bol.interview.mancala.game.gameplay;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class GameStateDTO {
    private List<Integer> playerOnePockets;
    private List<Integer> playerTwoPockets;
    private int playerOneMancala;
    private int playerTwoMancala;
    private String playerTurn;
    private String winnerMessage;
    private String errorMessage;

    private GameStateDTO() {
    }

    static GameStateDTO from(GamePlay gamePlay) {
        GameStateDTO gameStateDTO = new GameStateDTO();
        GameBoardState gameBoardState = gamePlay.getGameBoardState();
        gameStateDTO.playerOnePockets = mapListOfPocketsToListOfInts(gameBoardState.getPlayerOne().getPocketList());
        gameStateDTO.playerTwoPockets = mapListOfPocketsToListOfInts(gameBoardState.getPlayerTwo().getPocketList());
        gameStateDTO.playerOneMancala = gameBoardState.getPlayerOne().getMancala().getStoneCount();
        gameStateDTO.playerTwoMancala = gameBoardState.getPlayerTwo().getMancala().getStoneCount();
        gameStateDTO.playerTurn = gamePlay.getPlayerOnTurn().getName();
        gameStateDTO.winnerMessage = gamePlay.getWinnerMessage();
        return gameStateDTO;
    }

    private static List<Integer> mapListOfPocketsToListOfInts(List<Pocket> pocketList) {
        return pocketList.stream().map(Pocket::getStoneCount).collect(Collectors.toList());
    }


    GameStateDTO withErrorMessage(String message) {
        this.errorMessage = message;
        return this;
    }
}
