package com.interview.mancala.game.gameplay;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
class Player {
    static final int NR_OF_POCKETS = 6;
    private String name;
    private Mancala mancala;
    private List<Pocket> pocketList;


    private Player(String name) {
        this.name = name;
        this.mancala = new Mancala(this);
        pocketList = createPocketsListForPlayer();

    }

    static Player createPlayerOne() {
        return new Player("ONE");
    }

    static Player createPlayerTwo() {
        return new Player("TWO");
    }

    boolean hasNoMoreAvailableMoves() {
        return this.getPocketList().stream().allMatch(pocket -> pocket.getStoneCount() == 0);
    }


    private List<Pocket> createPocketsListForPlayer() {
        List<Pocket> pocketList = new ArrayList<>();

        for (int i = 0; i < NR_OF_POCKETS; i++) {
            Pocket pocket = new Pocket(this);
            pocketList.add(pocket);
        }
        return pocketList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
