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

    private PokerResult hightCardResult(CardValue blackCardValue, CardValue whiteCardValue) {
        var compare = blackCardValue.compareTo(whiteCardValue);
        if(compare > 0) {
            return BLACK_WINS;
        } else if (compare < 0) {
            return WHITE_WINS;
        }
        return EGALITY;
    }
}
