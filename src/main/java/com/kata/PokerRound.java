package com.kata;

import static com.kata.PokerResult.*;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public PokerResult result() {
        return hightCardResult();
    }

    public PokerResult hightCardResult() {
        var maxValueBlackPokerHand = blackPokerHand.maxCardValue();
        var maxValueWhitePokerHand = whitePokerHand.maxCardValue();

        return switch (maxValueBlackPokerHand.compareTo(maxValueWhitePokerHand)) {
            case 1 -> BLACK_WINS;
            case -1 -> WHITE_WINS;
            default -> EGALITY;
        };
    }
}
