package com.kata;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record Cards(Set<Card> cardSet, CardValues cardValues) {


    public SortedCardValues hightCards() {
        var cardValuesComparator = new SortedCardValues(Collections.emptyList());
        return cardValuesComparator.addSortedValuesFrom(cardValues);
    }
    public SortedCardValues pairs() {
        return similarCardValue(2);
    }
    public SortedCardValues threeOfAKinds() {
        return similarCardValue(3);
    }
    public SortedCardValues fourOfAKinds() {
        return similarCardValue(4);
    }


    public SortedCardValues similarCardValue(int similarCardNumber) {
        var groupByValues = cardSet.stream().collect(Collectors.groupingBy(Card::value));

        var cardValueList = groupByValues.entrySet().stream()
                .filter(cardValueListEntry -> cardValueListEntry.getValue().size() == similarCardNumber)
                .map(Map.Entry::getKey)
                .sorted().toList();

        return new SortedCardValues(cardValueList);
    }

    public SortedCardValues straight() {
        if (cardValues.isStraight()) {
            return this.hightCards();
        }
        return new SortedCardValues(Collections.emptyList());
    }

    public SortedCardValues flush() {
        var groupByValues = cardSet.stream().collect(Collectors.groupingBy(Card::color));
        if(groupByValues.entrySet().stream()
                .anyMatch(cardColorListEntry -> cardColorListEntry.getValue().size() == 5)) {
            return this.hightCards();
        }
        return new SortedCardValues(Collections.emptyList());
    }
}
