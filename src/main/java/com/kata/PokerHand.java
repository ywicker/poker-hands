package com.kata;

import java.util.Collection;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private final SameValueCards pairs;
    private final Cards cards;
    private final SameValueCards threeOfAKinds;
    private final SameValueCards fourOfAKinds;

    public PokerHand(Collection<Card> cards) {
        this.fourOfAKinds = SameValueCards.fromCards(cards, 4);
        this.threeOfAKinds = SameValueCards.fromCards(cards, 3);
        this.pairs = SameValueCards.fromCards(cards, 2);
        this.cards = new Cards(
                cards.stream()
                        .map(Card::value)
                        .collect(Collectors.toSet()));
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        var compareFourOfAKind = this.fourOfAKinds.compareTo(pokerHand.fourOfAKinds);
        if (compareFourOfAKind != 0) {
            return compareFourOfAKind;
        }
        if (this.cards.isStraight() && !pokerHand.cards.isStraight()) {
            return 1;
        } else if (!this.cards.isStraight() && pokerHand.cards.isStraight()) {
            return -1;
        }
        var compareThreeOfAKind = this.threeOfAKinds.compareTo(pokerHand.threeOfAKinds);
        if (compareThreeOfAKind != 0) {
            return compareThreeOfAKind;
        }
        var comparePairs = this.pairs.compareTo(pokerHand.pairs);
        if (comparePairs != 0) {
            return comparePairs;
        }
        return this.cards.compareTo(pokerHand.cards);
    }
}
