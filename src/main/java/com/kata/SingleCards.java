package com.kata;

import java.util.Set;

public record SingleCards(Set<CardValue> cardValues) implements Comparable<SingleCards>, WithCardValues {


    private SingleCards cardsWithoutMaxCardValue() {
        return new SingleCards(this.withoutMaxCardValue());
    }

    @Override
    public int compareTo(SingleCards cards) {
        if (cardValues.isEmpty()) {
            return 0;
        }

        var compareCard = this.maxCardValue().compareTo(cards.maxCardValue());
        if (compareCard == 0) {
            return this.cardsWithoutMaxCardValue()
                    .compareTo(cards.cardsWithoutMaxCardValue());
        }
        return compareCard;
    }
}
