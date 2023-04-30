package com.kata;

import static com.kata.PokerResult.*;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public PokerResult result() {
        if(blackPokerHand.cards().isEmpty() && whitePokerHand.cards().isEmpty()) {
            return EGALITY;
        }

        var blackPokerHandPairs = blackPokerHand.pairs();
        var whitePokerHandPairs = whitePokerHand.pairs();
        var compare = Integer.compare(blackPokerHandPairs.cardValues().size(), whitePokerHandPairs.cardValues().size());

        if(blackPokerHandPairs.cardValues().isEmpty() && whitePokerHandPairs.cardValues().isEmpty()) {
            var maxValueBlackPokerHand = blackPokerHand.maxCardValue();
            var maxValueWhitePokerHand = whitePokerHand.maxCardValue();

            return hightCardResult(maxValueBlackPokerHand, maxValueWhitePokerHand);
        }
        if(compare > 0) {
            return BLACK_WINS;
        } else if (compare < 0) {
            return WHITE_WINS;
        }

        var maxPairValueBlackPokerHand = blackPokerHandPairs.maxPairValue();
        var maxPairValueWhitePokerHand = whitePokerHandPairs.maxPairValue();

        if(maxPairValueBlackPokerHand.isPresent() && maxPairValueWhitePokerHand.isPresent()) {
            var hightPairResult = hightCardResult(maxPairValueBlackPokerHand.get(), maxPairValueWhitePokerHand.get());
            if(EGALITY.equals(hightPairResult)) {
                return new PokerRound(
                        blackPokerHand.withoutCardValues(maxPairValueBlackPokerHand.get()),
                        whitePokerHand.withoutCardValues(maxPairValueWhitePokerHand.get())
                ).result();
            }
            return hightPairResult;
        } else if (maxPairValueWhitePokerHand.isPresent()) {
            return WHITE_WINS;
        }
        return BLACK_WINS;
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
