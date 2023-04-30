package com.kata;

import java.util.Set;

import static com.kata.PokerResult.*;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public PokerResult result() {
        var compare = blackPokerHand.compareTo(whitePokerHand);
        if (compare > 0) {
            return BLACK_WINS;
        } else if (compare < 0) {
            return WHITE_WINS;
        }
        return EGALITY;
    }
}
