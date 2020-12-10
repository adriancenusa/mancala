package com.interview.mancala.game.gameplay;


import java.util.List;

final class GameInitializer {


    private GameInitializer() {
    }

    static GameBoardState initializeGame() {
        Player playerOne = Player.createPlayerOne();
        Player playerTwo = Player.createPlayerTwo();

        matchOpposingPockets(playerOne.getPocketList(), playerTwo.getPocketList());
        createCyclicLinkBetweenPits(playerOne, playerTwo);

        return new GameBoardState(playerOne, playerTwo);
    }

    private static void createCyclicLinkBetweenPits(Player playerOne, Player playerTwo) {
        for (int i = 0; i < Player.NR_OF_POCKETS - 1; i++) {
            playerOne.getPocketList().get(i).setNextPit(playerOne.getPocketList().get(i + 1));
        }
        playerOne.getPocketList().get(Player.NR_OF_POCKETS - 1).setNextPit(playerOne.getMancala());
        playerOne.getMancala().setNextPit(playerTwo.getPocketList().get(Player.NR_OF_POCKETS - 1));

        for (int i = Player.NR_OF_POCKETS - 1; i > 0; i--) {
            playerTwo.getPocketList().get(i).setNextPit(playerTwo.getPocketList().get(i - 1));
        }
        playerTwo.getPocketList().get(0).setNextPit(playerTwo.getMancala());
        playerTwo.getMancala().setNextPit(playerOne.getPocketList().get(0));
    }

    private static void matchOpposingPockets(List<Pocket> pocketListPlayerOne, List<Pocket> pocketListPlayerTwo) {
        for (int i = 0; i < pocketListPlayerOne.size(); i++) {
            pocketListPlayerOne.get(i).setCorrespondingOponentPocket(pocketListPlayerTwo.get(i));
            pocketListPlayerTwo.get(i).setCorrespondingOponentPocket(pocketListPlayerOne.get(i));
        }
    }



}
