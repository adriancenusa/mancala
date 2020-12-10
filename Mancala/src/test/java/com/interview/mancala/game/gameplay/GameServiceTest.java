package com.interview.mancala.game.gameplay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {


    @Spy
    GamePlay gamePlay = new GamePlay();

    @InjectMocks
    GameService gameService;


    @Test
    public void playWithoutError() throws PlayNotPossibleException {
        //given
        Mockito.doNothing().when(gamePlay).play(3);
        //when
        GameStateDTO play = gameService.play(3);
        //then
        Mockito.verify(gamePlay).play(3);
        assertNull(play.getErrorMessage());
    }


    @Test
    public void playWithError() throws PlayNotPossibleException {
        //given
        String errorMessage = "Error message";
        Mockito.doThrow(new PlayNotPossibleException(errorMessage)).when(gamePlay).play(3);
        //when
        GameStateDTO play = gameService.play(3);
        //then
        Mockito.verify(gamePlay).play(3);
        assertEquals(errorMessage, play.getErrorMessage());
    }

    @Test
    public void reset() {
        //given
        Mockito.doNothing().when(gamePlay).reset();
        //when
        GameStateDTO play = gameService.reset();
        //then
        Mockito.verify(gamePlay).reset();
        assertNull(play.getErrorMessage());
    }

    @Test
    public void getCurrentState() {
        //when
        GameStateDTO play = gameService.getCurrentState();
        //then
        assertNull(play.getErrorMessage());
    }
}