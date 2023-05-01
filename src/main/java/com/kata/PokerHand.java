package com.kata;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private final SameValueCards pairs;
    private final SingleCards singleCards;
    private final SameValueCards threeOfAKinds;

    public PokerHand(Collection<Card> cards){
        this.threeOfAKinds = SameValueCards.fromCards(cards, 3);
        this.pairs = SameValueCards.fromCards(cards, 2);
        this.singleCards = new SingleCards(
                cards.stream()
                        .map(Card::value)
                        .filter(value -> !this.pairs.cardValues().contains(value))
                        .collect(Collectors.toSet()));
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        var compareThreeOfAKind = this.threeOfAKinds.compareTo(pokerHand.threeOfAKinds);
        if(compareThreeOfAKind != 0) {
            return compareThreeOfAKind;
        }
        var comparePairs = this.pairs.compareTo(pokerHand.pairs);
        if(comparePairs != 0) {
            return comparePairs;
        }
        return this.singleCards.compareTo(pokerHand.singleCards);
    }
}
