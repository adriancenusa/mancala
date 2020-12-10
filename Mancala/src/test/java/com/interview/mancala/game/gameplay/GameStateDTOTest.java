package com.interview.mancala.game.gameplay;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class GameStateDTOTest {


    @Test
    public void from() {
        //given
        GamePlay gamePlay = Mockito.mock(GamePlay.class);
        GameBoardState gameBoardState = createGameBoardState();
        when(gamePlay.getGameBoardState()).thenReturn(gameBoardState);
        when(gamePlay.getPlayerOnTurn()).thenReturn(gameBoardState.getPlayerOne());
        String winnerMessage = "Test winner message";
        when(gamePlay.getWinnerMessage()).thenReturn(winnerMessage);
        //when
        GameStateDTO gameStateDTO = GameStateDTO.from(gamePlay);
        //then
        gameStateDTO.getPlayerOnePockets().forEach(value -> assertEquals(7, value.intValue())); //set in createGameBoardState
        gameStateDTO.getPlayerTwoPockets().forEach(value -> assertEquals(6, value.intValue())); //default start value
        assertEquals(3, gameStateDTO.getPlayerOneMancala()); //set in createGameBoardState
        assertEquals(0, gameStateDTO.getPlayerTwoMancala()); //set in createGameBoardState
        assertEquals("ONE", gameStateDTO.getPlayerTurn());
        assertEquals(winnerMessage, gameStateDTO.getWinnerMessage());
    }

    @Test
    public void withErrorMessage() {
        //given
        GamePlay gamePlay = Mockito.mock(GamePlay.class);
        GameBoardState gameBoardState = createGameBoardState();
        when(gamePlay.getGameBoardState()).thenReturn(gameBoardState);
        when(gamePlay.getPlayerOnTurn()).thenReturn(gameBoardState.getPlayerOne());
        GameStateDTO gameStateDTO = GameStateDTO.from(gamePlay);
        String errorMessage = "Error message";
        //when
        gameStateDTO.withErrorMessage(errorMessage);
        //then
        assertEquals(errorMessage, gameStateDTO.getErrorMessage());
    }

    private GameBoardState createGameBoardState() {
        Player playerOne = Player.createPlayerOne();
        Player playerTwo = Player.createPlayerTwo();
        GameBoardState gameBoardState = new GameBoardState(playerOne, playerTwo);

        playerOne.getPocketList().forEach(pocket -> pocket.setStoneCount(7));
        playerOne.getMancala().setStoneCount(3);
        return gameBoardState;
    }
}