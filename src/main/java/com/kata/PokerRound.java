package com.kata;

import static com.kata.PokerResult.*;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public PokerResult result() {
        var maxPairValueBlackPokerHand = blackPokerHand.maxPairValue();
        var maxPairValueWhitePokerHand = whitePokerHand.maxPairValue();

        if(maxPairValueBlackPokerHand.isPresent() && maxPairValueWhitePokerHand.isPresent()) {
            return hightCardResult(maxPairValueBlackPokerHand.get(), maxPairValueWhitePokerHand.get());
        } else if (maxPairValueWhitePokerHand.isPresent()) {
            return WHITE_WINS;
        } else if (maxPairValueBlackPokerHand.isPresent()) {
            return BLACK_WINS;
        }

        var maxValueBlackPokerHand = blackPokerHand.maxCardValue();
        var maxValueWhitePokerHand = whitePokerHand.maxCardValue();

        return hightCardResult(maxValueBlackPokerHand, maxValueWhitePokerHand);
    }

    private PokerResult hightCardResult(CardValue maxValueBlackPokerHand, CardValue maxValueWhitePokerHand) {
        return switch (maxValueBlackPokerHand.compareTo(maxValueWhitePokerHand)) {
            case 1 -> BLACK_WINS;
            case -1 -> WHITE_WINS;
            default -> EGALITY;
        };
    }
}
