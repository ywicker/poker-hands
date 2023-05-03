package com.kata;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public record Cards(Set<Card> cardSet, CardValues cardValues) {

    public CardValues pairs() {
        return new CardValues(similarCardValue(2));
    }
    public Optional<CardValue> threeOfAKinds() {
        return similarCardValue(3).stream().findAny();
    }
    public Optional<CardValue> fourOfAKinds() {
        return similarCardValue(4).stream().findAny();
    }

    public Set<CardValue> similarCardValue(int similarCardNumber) {
        var groupByValues = cardSet.stream().collect(Collectors.groupingBy(Card::value));

        return groupByValues.entrySet().stream()
                .filter(cardValueListEntry -> cardValueListEntry.getValue().size() == similarCardNumber)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public Optional<CardValue> straight() {
        if (cardValues.isStraight()) {
            return Optional.of(this.cardValues.maxCardValue());
        }
        return Optional.empty();
    }

    public CardValues flush() {
        var groupByValues = cardSet.stream().collect(Collectors.groupingBy(Card::color));
        if(groupByValues.entrySet().stream()
                .anyMatch(cardColorListEntry -> cardColorListEntry.getValue().size() == 5)) {
            return this.cardValues();
        }
        return new CardValues(Collections.emptySet());
    }
}
