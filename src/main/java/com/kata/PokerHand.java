package com.kata;

import java.util.*;
import java.util.stream.Collectors;

public record PokerHand(List<Card> cards) {
    public CardValue maxCardValue() {
        return cards.stream().max(Card::compareTo)
                .orElseThrow().value();
    }

    public Set<Pair> pairs() {
        Map<CardValue, List<Card>> groupByValues = cards.stream().collect(Collectors.groupingBy(Card::value));
        return groupByValues.entrySet().stream().filter(cardValueListEntry -> cardValueListEntry.getValue().size() == 2)
                .map(Map.Entry::getKey)
                .map(Pair::new)
                .collect(Collectors.toSet());
    }

    public Optional<CardValue> maxPairValue() {
        return pairs().stream().map(Pair::cardValue).max(CardValue::compareTo);
    }

    public PokerHand withoutCardValues(CardValue cardValue) {
        return new PokerHand(
                cards.stream()
                .filter(card -> !cardValue.equals(card.value()))
                        .toList()
        );
    }
}
