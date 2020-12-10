package com.interview.mancala.game.gameplay;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class GamePlayTest {

    @Spy
    PlayPossibleVerifier playPossibleVerifier = new PlayPossibleVerifier();


    @InjectMocks
    GamePlay gamePlay;

    @Before
    public void setup() throws PlayNotPossibleException {
        Mockito.doNothing().when(playPossibleVerifier).isPlayPossible(any(), any(Integer.class));
    }

    @Test
    public void playPocket() throws PlayNotPossibleException {
        //given

        //when
        gamePlay.play(3);
        //then
        assertEquals(0, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(3).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(4).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(5).getStoneCount());
        assertEquals(1, gamePlay.getGameBoardState().getPlayerOne().getMancala().getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(5).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(4).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(3).getStoneCount());
        assertEquals("TWO", gamePlay.getGameBoardState().getPlayerTwo().getName());
    }


    @Test
    public void playPocketRepeatTurn() throws PlayNotPossibleException {
        //given

        //when
        gamePlay.play(0);

        //then
        assertEquals(0, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(0).getStoneCount());

        assertEquals(7, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(1).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(2).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(3).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(4).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(5).getStoneCount());
        assertEquals(1, gamePlay.getGameBoardState().getPlayerOne().getMancala().getStoneCount());
        assertEquals(6, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(3).getStoneCount());
        assertEquals("ONE", gamePlay.getPlayerOnTurn().getName());
    }

    @Test
    public void playPocketCapture() throws PlayNotPossibleException {
        //given
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(0).setStoneCount(2);
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(1).setStoneCount(0);
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(2).setStoneCount(0);
        gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(2).setStoneCount(5);
        gamePlay.getGameBoardState().getPlayerOne().getMancala().setStoneCount(3);
        //when
        gamePlay.play(0);

        //then
        assertEquals(0, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(0).getStoneCount());
        assertEquals(1, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(1).getStoneCount());
        assertEquals(0, gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(2).getStoneCount());
        assertEquals(0, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(2).getStoneCount());
        assertEquals(9, gamePlay.getGameBoardState().getPlayerOne().getMancala().getStoneCount());
        assertEquals("TWO", gamePlay.getPlayerOnTurn().getName());

    }

    @Test
    public void playPocketSkipOpposingMancala() throws PlayNotPossibleException {
        //given
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(5).setStoneCount(9);
        //when
        gamePlay.play(5);

        //then
        assertEquals(1, gamePlay.getGameBoardState().getPlayerOne().getMancala().getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(0).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(1).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(2).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(3).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(4).getStoneCount());
        assertEquals(7, gamePlay.getGameBoardState().getPlayerTwo().getPocketList().get(5).getStoneCount());
        assertEquals(0, gamePlay.getGameBoardState().getPlayerTwo().getMancala().getStoneCount());

        assertEquals("TWO", gamePlay.getPlayerOnTurn().getName());
    }

    @Test
    public void playPocketPlayerOneWins() throws PlayNotPossibleException {
        //given
        gamePlay.getGameBoardState().getPlayerOne().getMancala().setStoneCount(10);
        gamePlay.getGameBoardState().getPlayerTwo().getMancala().setStoneCount(10);
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().forEach(pocket -> pocket.setStoneCount(0));
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(5).setStoneCount(1);
        //when
        gamePlay.play(5);
        //then
        assertEquals("Player ONE won the game", gamePlay.getWinnerMessage());
    }

    @Test
    public void playPocketPlayerTwoWins() throws PlayNotPossibleException {
        //given
        gamePlay.getGameBoardState().getPlayerOne().getMancala().setStoneCount(10);
        gamePlay.getGameBoardState().getPlayerTwo().getMancala().setStoneCount(12);
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().forEach(pocket -> pocket.setStoneCount(0));
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(5).setStoneCount(1);
        //when
        gamePlay.play(5);
        //then
        assertEquals("Player TWO won the game", gamePlay.getWinnerMessage());
    }

    @Test
    public void playPocketPlayerDraw() throws PlayNotPossibleException {
        //given
        gamePlay.getGameBoardState().getPlayerOne().getMancala().setStoneCount(10);
        gamePlay.getGameBoardState().getPlayerTwo().getMancala().setStoneCount(11);
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().forEach(pocket -> pocket.setStoneCount(0));
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(5).setStoneCount(1);
        //when
        gamePlay.play(5);
        //then
        assertEquals("This game was a draw", gamePlay.getWinnerMessage());
    }

    @Test(expected = PlayNotPossibleException.class)
    public void testPlayNotPossible() throws PlayNotPossibleException {
        //given
        Mockito.doThrow(PlayNotPossibleException.class).when(playPossibleVerifier).isPlayPossible(any(), any(Integer.class));
        //when
        gamePlay.play(10);
        //then exception is thrown
    }



    @Test
    public void reset() throws PlayNotPossibleException {
        //given
        gamePlay.getGameBoardState().getPlayerOne().getMancala().setStoneCount(10);
        gamePlay.getGameBoardState().getPlayerTwo().getMancala().setStoneCount(11);
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().forEach(pocket -> pocket.setStoneCount(0));
        gamePlay.getGameBoardState().getPlayerOne().getPocketList().get(5).setStoneCount(1);
        //when
        gamePlay.play(5);
        gamePlay.reset();
        //then
        assertNull(gamePlay.getWinnerMessage());
        assertEquals(gamePlay.getGameBoardState().getPlayerOne(), gamePlay.getPlayerOnTurn());


    }

}