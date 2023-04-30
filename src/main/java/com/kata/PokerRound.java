package com.kata;

import static com.kata.PokerResult.*;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public PokerResult result() {
        var maxPairValueBlackPokerHand = blackPokerHand.maxPairValue();
        var maxPairValueWhitePokerHand = whitePokerHand.maxPairValue();

        if(maxPairValueBlackPokerHand.isPresent() && maxPairValueWhitePokerHand.isPresent()) {
            return switch (maxPairValueBlackPokerHand.get().compareTo(maxPairValueWhitePokerHand.get())) {
                case 1 -> BLACK_WINS;
                case -1 -> WHITE_WINS;
                default -> EGALITY;
            };
        } else if (maxPairValueWhitePokerHand.isPresent()) {
            return WHITE_WINS;
        } else if (maxPairValueBlackPokerHand.isPresent()) {
            return BLACK_WINS;
        }

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
