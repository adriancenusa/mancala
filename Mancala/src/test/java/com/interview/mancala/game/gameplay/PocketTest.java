package com.interview.mancala.game.gameplay;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PocketTest {

    Player playerOne = Player.createPlayerOne();
    Player playerTwo = Player.createPlayerTwo();

//    @Test
//    public void handleLastStoneOnOppositePlayerPocket() {
//        //given
//        Player playerOne = Mockito.mock(Player.class);
//        Pocket pocket = new Pocket(playerOne);
//        pocket.setStoneCount(1);
//
//        Player playerTwo = Mockito.mock(Player.class);
//        Pocket correspondingPocket = new Pocket(playerTwo);
//
//        pocket.setCorrespondingOponentPocket(correspondingPocket);
//        //when
//        pocket.handleLastStone(playerTwo);
//        //then
//        Mockito.verifyNoInteractions(playerOne);
//        assertEquals(6, correspondingPocket.getStoneCount());
//        assertEquals(1, pocket.getStoneCount());
//
//    }
//
//    @Test
//    public void handleLastStoneOnNonEmptyPocket() {
//        //given
//        Player playerOne = Mockito.mock(Player.class);
//        Pocket pocket = new Pocket(playerOne);
//        pocket.setStoneCount(3);
//
//        Player playerTwo = Mockito.mock(Player.class);
//        Pocket correspondingPocket = new Pocket(playerTwo);
//
//        pocket.setCorrespondingOponentPocket(correspondingPocket);
//        //when
//        pocket.handleLastStone(playerOne);
//        //then
//        Mockito.verifyNoInteractions(playerOne);
//        assertEquals(6, correspondingPocket.getStoneCount());
//        assertEquals(3, pocket.getStoneCount());
//
//    }
//
//    @Test
//    public void handleLastStoneCaptureStones() {
//        //given
//        Pocket pocket = playerOne.getPocketList().get(3);
//        Pocket correspondingOponentPocket = playerTwo.getPocketList().get(3);
//
//        pocket.setStoneCount(1);
//        pocket.setCorrespondingOponentPocket(correspondingOponentPocket);
//        correspondingOponentPocket.setStoneCount(3);
//
//        playerOne.getMancala().setStoneCount(5);
//        //when
//        pocket.handleLastStone(playerOne);
//        //then
//        assertEquals(9, playerOne.getMancala().getStoneCount());//5 (already in mancala)+ 3(opposite pocket) + 1
//        assertEquals(0, correspondingOponentPocket.getStoneCount());
//        assertEquals(0, pocket.getStoneCount());
//
//    }


    @Test
    public void nextPitForPlayerForNextPitPocket() {
        //given
        Pocket pocket = new Pocket(playerOne);
        Pocket expectedNextPit = new Pocket(playerOne);
        pocket.setNextPit(expectedNextPit);
        //when
        Pit actualNextPit = pocket.nextPitForPlayer(playerOne);
        //then
        assertEquals(expectedNextPit, actualNextPit);
    }

    @Test
    public void nextPitForPlayerForNextPitMancalaSamePlayer() {
        //given
        Pocket pocket = new Pocket(playerOne);
        Mancala expectedNextPit = new Mancala(playerOne);
        pocket.setNextPit(expectedNextPit);
        //when
        Pit actualNextPit = pocket.nextPitForPlayer(playerOne);
        //then
        assertEquals(expectedNextPit, actualNextPit);
    }

    @Test
    public void nextPitForPlayerForNextPitMancalaOppositePlayer() {
        //given
        Pocket pocket = new Pocket(playerOne);
        Mancala nextMancala = new Mancala(playerTwo);
        Pocket expectedNextPit = new Pocket(playerTwo);


        pocket.setNextPit(nextMancala);
        nextMancala.setNextPit(expectedNextPit);
        //when
        Pit actualNextPit = pocket.nextPitForPlayer(playerOne);
        //then
        assertEquals(expectedNextPit, actualNextPit);
    }
}