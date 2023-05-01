package com.kata;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record SameValueCards(Set<CardValue> cardValues, Comparable nextCompare) implements Comparable<SameValueCards>, WithCardValues {

    public static SameValueCards fromCards(Collection<Card> cards, int similarCardNumber, Comparable nextCompare) {
        var groupByValues = cards.stream().collect(Collectors.groupingBy(Card::value));

        var sameValues = groupByValues.entrySet().stream()
                .filter(cardValueListEntry -> cardValueListEntry.getValue().size() == similarCardNumber)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return new SameValueCards(sameValues, nextCompare);
    }

    @Override
    public int compareTo(SameValueCards cardsObject) {
        if(this.cardValues.isEmpty() && cardsObject.cardValues.isEmpty()){
            return this.nextCompare.compareTo(cardsObject.nextCompare);
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
        return new SameValueCards(this.withoutMaxCardValue(), nextCompare);
    }
}
