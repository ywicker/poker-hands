package com.kata;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private final Pairs pairs;
    private final SingleCards singleCards;

    public PokerHand(Collection<Card> cards){
        Map<CardValue, List<Card>> groupByValues = cards.stream().collect(Collectors.groupingBy(Card::value));
        Set<CardValue> pairValues = groupByValues.entrySet().stream().filter(cardValueListEntry -> cardValueListEntry.getValue().size() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        this.pairs = new Pairs(pairValues);

        this.singleCards = new SingleCards(
                cards.stream()
                        .map(Card::value)
                        .filter(value -> !this.pairs.cardValues().contains(value))
                        .collect(Collectors.toSet()));
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        var comparePairs = this.pairs.compareTo(pokerHand.pairs);
        if(comparePairs != 0) {
            return comparePairs;
        }
        return this.singleCards.compareTo(pokerHand.singleCards);
    }
}
