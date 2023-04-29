package com.kata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record PokerHand(Card... cards) {
    public CardValue maxCardValue() {
        return Arrays.stream(cards).max(Card::compareTo)
                .orElseThrow().value();
    }

    public Set<Pair> pairs() {
        Map<CardValue, List<Card>> groupByValues = Arrays.stream(cards).collect(Collectors.groupingBy(Card::value));
        return groupByValues.entrySet().stream().filter(cardValueListEntry -> cardValueListEntry.getValue().stream().count() == 2)
                .map(Map.Entry::getKey)
                .map(Pair::new)
                .collect(Collectors.toSet());
    }
}
