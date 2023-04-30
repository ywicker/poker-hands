package com.kata;

import java.util.Set;

import static com.kata.PokerResult.*;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public PokerResult result() {
        if(blackPokerHand.cards().isEmpty() && whitePokerHand.cards().isEmpty()) {
            return EGALITY;
        }

        var compare = blackPokerHand.pairs().compareTo(whitePokerHand.pairs());
        if(compare > 0) {
            return BLACK_WINS;
        } else if (compare < 0) {
            return WHITE_WINS;
        }

        if(blackPokerHand.pairs().cardValues().isEmpty()
                && blackPokerHand.pairs().cardValues().isEmpty()) {
            var hightCardResult = hightCardResult(blackPokerHand.maxCardValue(), whitePokerHand.maxCardValue());
            if(EGALITY.equals(hightCardResult)) {
                return new PokerRound(
                        blackPokerHand.withoutCardValues(Set.of(blackPokerHand.maxCardValue())),
                        whitePokerHand.withoutCardValues(Set.of(whitePokerHand.maxCardValue()))
                ).result();
            }
            return hightCardResult;
        }
        return new PokerRound(
                blackPokerHand.withoutCardValues(blackPokerHand.pairs().cardValues()),
                whitePokerHand.withoutCardValues(whitePokerHand.pairs().cardValues())
        ).result();
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
