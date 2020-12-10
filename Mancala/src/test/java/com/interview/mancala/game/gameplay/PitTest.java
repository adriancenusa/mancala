package com.interview.mancala.game.gameplay;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PitTest {

    Player player = Player.createPlayerOne();

    @Test
    public void increaseStoneCount() {
        //given
        DummyPitForTest dummyPitForTest = new DummyPitForTest(player);
        dummyPitForTest.setStoneCount(5);
        //when
        dummyPitForTest.increaseStoneCount();
        //then
        assertEquals(6, dummyPitForTest.getStoneCount());
    }

    @Test
    public void increaseStoneCountWith() {
        //given
        DummyPitForTest dummyPitForTest = new DummyPitForTest(player);
        dummyPitForTest.setStoneCount(5);
        //when
        dummyPitForTest.increaseStoneCountWith(5);
        //then
        assertEquals(10, dummyPitForTest.getStoneCount());
    }

    @Test
    public void removeAllStones() {
        //given
        DummyPitForTest dummyPitForTest = new DummyPitForTest(player);
        dummyPitForTest.setStoneCount(5);
        //when
        dummyPitForTest.removeAllStones();
        //then
        assertEquals(0, dummyPitForTest.getStoneCount());
    }


    class DummyPitForTest extends Pit {

        DummyPitForTest(Player player) {
            super(player);
        }

        @Override
        Pit nextPitForPlayer(Player player) {
            return null;
        }

    }
}