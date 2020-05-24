package com.bol.interview.mancala.game.gameplay;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GameService {

    @Autowired
    private GamePlay gamePlay;

    public GameStateDTO play(int index) {
        try {
            gamePlay.play(index);
            return GameStateDTO.from(gamePlay);
        } catch (PlayNotPossibleException e) {
            return GameStateDTO.from(gamePlay)
                    .withErrorMessage(e.getMessage());
        }
    }

    public GameStateDTO reset() {
        gamePlay.reset();
        return GameStateDTO.from(gamePlay);
    }

    public GameStateDTO getCurrentState() {
        return GameStateDTO.from(gamePlay);
    }


}
