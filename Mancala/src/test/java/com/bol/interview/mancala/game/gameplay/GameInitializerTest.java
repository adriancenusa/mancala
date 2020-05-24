package com.bol.interview.mancala.game.gameplay;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameInitializerTest {

    @Test
    public void initializeGame() {
        //when
        GameBoardState gameBoardState = GameInitializer.initializeGame();
        //then
        //verify oposing pockets
        List<Pocket> pocketListPlayerOne = gameBoardState.getPlayerOne().getPocketList();
        List<Pocket> pocketListPlayerTwo = gameBoardState.getPlayerTwo().getPocketList();
        for (int i = 0; i < pocketListPlayerOne.size(); i++) {
            assertEquals(pocketListPlayerOne.get(i), pocketListPlayerTwo.get(i).getCorrespondingOponentPocket());
            assertEquals(pocketListPlayerTwo.get(i), pocketListPlayerOne.get(i).getCorrespondingOponentPocket());
        }

        //verify cyclic lync
        Pit firstPit = pocketListPlayerOne.get(0);
        int nrOfPits = pocketListPlayerOne.size() * 2 + 2;

        Pit actualPit = firstPit;
        for (int i = 0; i < nrOfPits; i++) {
            actualPit = actualPit.getNextPit();
        }

        assertEquals(firstPit, actualPit);
    }
}