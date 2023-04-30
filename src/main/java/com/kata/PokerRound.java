package com.kata;

import java.util.Set;

import static com.kata.PokerResult.*;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public PokerResult result() {
        var compare = blackPokerHand.pairs().compareTo(whitePokerHand.pairs());
        if(compare > 0) {
            return BLACK_WINS;
        } else if (compare < 0) {
            return WHITE_WINS;
        }

        var blackPokerHandNoPairs = blackPokerHand.withoutPairs();
        var whitePokerHandNoPairs = whitePokerHand.withoutPairs();

        if(blackPokerHandNoPairs.cards().isEmpty() && whitePokerHandNoPairs.cards().isEmpty()) {
            return EGALITY;
        }

        var hightCardResult = hightCardResult(blackPokerHandNoPairs.maxCardValue(), whitePokerHandNoPairs.maxCardValue());
        if(EGALITY.equals(hightCardResult)) {
            return new PokerRound(
                    blackPokerHand.withoutCardValues(Set.of(blackPokerHand.maxCardValue())),
                    whitePokerHand.withoutCardValues(Set.of(whitePokerHand.maxCardValue()))
            ).result();
        }
        return hightCardResult;
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
