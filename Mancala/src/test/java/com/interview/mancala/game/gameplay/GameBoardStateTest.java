package com.interview.mancala.game.gameplay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameBoardStateTest {

    @Test
    public void reset() {
        //given
        Player playerOne = Player.createPlayerOne();
        Player playerTwo = Player.createPlayerTwo();

        for (int i = 0; i < playerOne.getPocketList().size(); i++) {
            playerOne.getPocketList().get(i).setStoneCount(i);
            playerTwo.getPocketList().get(i).setStoneCount(i);
        }

        playerOne.getMancala().setStoneCount(23);
        playerTwo.getMancala().setStoneCount(2);

        GameBoardState gameBoardState = new GameBoardState(playerOne, playerTwo);

        //when
        gameBoardState.reset();

        //then
        assertEquals(0, playerOne.getMancala().getStoneCount());
        assertEquals(0, playerTwo.getMancala().getStoneCount());
        playerOne.getPocketList().forEach(pocket -> assertEquals(6, pocket.getStoneCount()));
        playerTwo.getPocketList().forEach(pocket -> assertEquals(6, pocket.getStoneCount()));

    }

    @Test
    public void isGameFinishedPlayerOneOutOfMoves() {
        //given
        Player playerOne = Mockito.mock(Player.class);
        Player playerTwo = Mockito.mock(Player.class);
        GameBoardState gameBoardState = new GameBoardState(playerOne, playerTwo);
        when(playerOne.hasNoMoreAvailableMoves()).thenReturn(true);
        //when
        boolean gameFinished = gameBoardState.isGameFinished();
        //then
        assertTrue(gameFinished);
    }


    @Test
    public void isGameFinishedPlayerTwoOutOfMoves() {
        //given
        Player playerOne = Mockito.mock(Player.class);
        Player playerTwo = Mockito.mock(Player.class);
        GameBoardState gameBoardState = new GameBoardState(playerOne, playerTwo);
        when(playerOne.hasNoMoreAvailableMoves()).thenReturn(false);
        when(playerTwo.hasNoMoreAvailableMoves()).thenReturn(true);
        //when
        boolean gameFinished = gameBoardState.isGameFinished();
        //then
        assertTrue(gameFinished);
    }

    @Test
    public void isGameFinishedPlayerNoPlayerOutOfMoves() {
        //given
        Player playerOne = Mockito.mock(Player.class);
        Player playerTwo = Mockito.mock(Player.class);
        GameBoardState gameBoardState = new GameBoardState(playerOne, playerTwo);
        when(playerOne.hasNoMoreAvailableMoves()).thenReturn(false);
        when(playerTwo.hasNoMoreAvailableMoves()).thenReturn(false);
        //when
        boolean gameFinished = gameBoardState.isGameFinished();
        //then
        assertFalse(gameFinished);
    }

    @Test
    public void getPlayerOneScore() {
        //given
        Player playerOne = Mockito.mock(Player.class, Answers.RETURNS_DEEP_STUBS);
        when(playerOne.getMancala().getStoneCount()).thenReturn(3);
        GameBoardState gameBoardState = new GameBoardState(playerOne, null);
        //when
        int playerOneScore = gameBoardState.getPlayerOneScore();
        //then
        assertEquals(3, playerOneScore);
    }

    @Test
    public void getPlayerTwoScore() {
        //given
        Player playerTwo = Mockito.mock(Player.class, Answers.RETURNS_DEEP_STUBS);
        when(playerTwo.getMancala().getStoneCount()).thenReturn(3);
        GameBoardState gameBoardState = new GameBoardState(null, playerTwo);
        //when
        int playerTwoScore = gameBoardState.getPlayerTwoScore();
        //then
        assertEquals(3, playerTwoScore);
    }
}