package com.kata;

import java.util.Arrays;

import static com.kata.PokerResult.*;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public PokerResult result() {
        var maxValueBlackPokerHand = Arrays.stream(blackPokerHand.cards())
                .max(Card::compareTo).get();
        var maxValueWhitePokerHand = Arrays.stream(whitePokerHand.cards())
                .max(Card::compareTo).get();

        switch (maxValueBlackPokerHand.compareTo(maxValueWhitePokerHand)) {
            case 1: return BLACK_WINS;
            case -1: return WHITE_WINS;
            default: return EGALITY;
        }
    }
}
