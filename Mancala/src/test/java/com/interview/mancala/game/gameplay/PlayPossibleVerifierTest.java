package com.interview.mancala.game.gameplay;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class PlayPossibleVerifierTest {

    @Test(expected = PlayNotPossibleException.class)
    public void isPlayPossibleGameFinished() throws PlayNotPossibleException {
        //given
        GamePlay gamePlay = Mockito.mock(GamePlay.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(gamePlay.getGameBoardState().isGameFinished()).thenReturn(true);
        //when
        new PlayPossibleVerifier().isPlayPossible(gamePlay, 3);
        //then
        //exception expected
    }

    @Test(expected = PlayNotPossibleException.class)
    public void isPlayPossibleWrongIndex() throws PlayNotPossibleException {
        //given
        GamePlay gamePlay = Mockito.mock(GamePlay.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(gamePlay.getGameBoardState().isGameFinished()).thenReturn(false);
        //when
        new PlayPossibleVerifier().isPlayPossible(gamePlay, 10);
        //then
        //exception expected
    }

    @Test(expected = PlayNotPossibleException.class)
    public void isPlayPossibleFromEmptyPocket() throws PlayNotPossibleException {
        //given
        GamePlay gamePlay = Mockito.mock(GamePlay.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(gamePlay.getGameBoardState().isGameFinished()).thenReturn(false);

        Pocket pocket = new Pocket(Player.createPlayerOne());
        pocket.setStoneCount(0);
        Mockito.when(gamePlay.getPlayerOnTurn().getPocketList()).thenReturn(Arrays.asList(pocket));
        //when
        new PlayPossibleVerifier().isPlayPossible(gamePlay, 0);
        //then
        //exception expected
    }
}