package com.kata;

import java.util.*;

import static com.kata.PokerResult.*;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public PokerResult result() {
        int maxValueBlackPokerHand = Arrays.stream(blackPokerHand.cards())
                .map(Card::i).max(Integer::compareTo).get();
        int maxValueWhitePokerHand = Arrays.stream(whitePokerHand.cards())
                .map(Card::i).max(Integer::compareTo).get();

        if(maxValueBlackPokerHand < maxValueWhitePokerHand) {
            return WHITE_WINS;
        } else if (maxValueBlackPokerHand > maxValueWhitePokerHand) {
            return BLACK_WINS;
        }
        return EGALITY;
    }
}
