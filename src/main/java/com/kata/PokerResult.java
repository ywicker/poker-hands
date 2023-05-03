package com.kata;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PokerResult {

    private final Set<Player> playerListWins;

    public PokerResult(PokerHand... pokerHandWins) {
        this.playerListWins = Arrays.stream(pokerHandWins).map(PokerHand::player).collect(Collectors.toSet());
    }

    public String report() {
        if(playerListWins.size() == 1) {
            var playerWins = playerListWins.stream().findAny().get();
            return playerWins.name() + " wins";
        }
        return "Egality";
    }
}
