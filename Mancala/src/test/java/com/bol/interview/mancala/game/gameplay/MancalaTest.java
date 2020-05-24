package com.bol.interview.mancala.game.gameplay;

import org.junit.Test;

import static org.junit.Assert.*;

public class MancalaTest {

    Player player = Player.createPlayerOne();

    @Test
    public void nextPitForPlayer() {
        //given
        Mancala mancala = new Mancala(player);
        Pocket expectedNextPit = new Pocket(player);
        mancala.setNextPit(expectedNextPit);
        //when
        Pit actualNextPit = mancala.nextPitForPlayer(player);
        //then
        assertEquals(expectedNextPit, actualNextPit);
    }

    @Test
    public void shouldChangeTurn() {
        //given
        Mancala mancala = new Mancala(player);
        //when
        boolean result = mancala.shouldChangeTurn();
        //then
        assertFalse(result);
    }
}