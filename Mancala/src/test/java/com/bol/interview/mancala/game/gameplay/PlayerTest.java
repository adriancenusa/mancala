package com.bol.interview.mancala.game.gameplay;


import org.junit.Test;

import static org.junit.Assert.*;


public class PlayerTest {

    @Test
    public void createPlayerOne() {
        //when
        Player player = Player.createPlayerOne();
        //then
        assertEquals("ONE", player.getName());
        assertPlayerPits(player);
    }


    @Test
    public void createPlayerTwo() {
        //when
        Player player = Player.createPlayerOne();
        //then
        assertEquals("ONE", player.getName());
        assertPlayerPits(player);
    }

    @Test
    public void hasNoMoreAvailableMovesFalse() {
        //given
        Player player = Player.createPlayerOne();
        player.getPocketList().forEach(pocket -> pocket.setStoneCount(0));
        //when
        boolean result = player.hasNoMoreAvailableMoves();
        //then
        assertTrue(result);
    }

    @Test
    public void hasNoMoreAvailableMovesTrue() {
        //given
        Player player = Player.createPlayerOne();
        //when
        boolean result = player.hasNoMoreAvailableMoves();
        //then
        assertFalse(result);
    }

    private void assertPlayerPits(Player player) {
        assertEquals(player, player.getMancala().getPlayer());
        assertEquals(6, player.getPocketList().size());
        player.getPocketList().forEach(pocket -> assertEquals(player, pocket.getPlayer()));
    }
}