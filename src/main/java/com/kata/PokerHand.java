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

    public PokerHand withoutCardValues(CardValue cardValue) {
        return new PokerHand(
                cards.stream()
                .filter(card -> !cardValue.equals(card.value()))
                        .toList()
        );
    }
}
