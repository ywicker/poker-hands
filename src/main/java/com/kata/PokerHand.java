package com.kata;

import java.util.*;
import java.util.stream.Collectors;

public record PokerHand(List<Card> cards) {
    public CardValue maxCardValue() {
        return cards.stream().max(Card::compareTo)
                .orElseThrow().value();
    }

    public Pairs pairs() {
        Map<CardValue, List<Card>> groupByValues = cards.stream().collect(Collectors.groupingBy(Card::value));
        Set<CardValue> pairValues = groupByValues.entrySet().stream().filter(cardValueListEntry -> cardValueListEntry.getValue().size() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        return new Pairs(pairValues);
    }

    public PokerHand withoutCardValues(Set<CardValue> cardValues) {
        return new PokerHand(
                cards.stream()
                .filter(card -> !cardValues.contains(card.value()))
                        .toList()
        );
    }

    public PokerHand withoutPairs() {
        return withoutCardValues(this.pairs().cardValues());
    }
}
