package com.kata;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record SameValueCards(Set<CardValue> cardValues) implements Comparable<SameValueCards>, WithCardValues {

    public static SameValueCards fromCards(Collection<Card> cards, int similarCardNumber) {
        Map<CardValue, List<Card>> groupByValues = cards.stream().collect(Collectors.groupingBy(Card::value));

        Set<CardValue> sameValues = groupByValues.entrySet().stream()
                .filter(cardValueListEntry -> cardValueListEntry.getValue().size() == similarCardNumber)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return new SameValueCards(sameValues);
    }

    @Override
    public int compareTo(SameValueCards cardsObject) {
        if(this.cardValues.isEmpty() && cardsObject.cardValues.isEmpty()){
            return 0;
        }

        var compareSize = Integer.compare(this.cardValues.size(), cardsObject.cardValues.size());
        if(compareSize != 0) {
            return compareSize;
        }

        var compareMax = Integer.compare(this.maxCardValue().ordinal(), cardsObject.maxCardValue().ordinal());
        if(compareMax != 0) {
            return compareMax;
        }
        return sameValueCardsWithoutMaxCardValue()
                .compareTo(cardsObject.sameValueCardsWithoutMaxCardValue());
    }

    public SameValueCards sameValueCardsWithoutMaxCardValue() {
        return new SameValueCards(this.withoutMaxCardValue());
    }
}
