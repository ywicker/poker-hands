package com.kata;

import java.util.Set;

public record SameValueCards(Set<CardValue> cardValues) implements Comparable<SameValueCards>, WithCardValues {

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
