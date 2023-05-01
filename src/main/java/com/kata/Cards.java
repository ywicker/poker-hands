package com.kata;

import java.util.Set;

public record Cards(Set<CardValue> cardValues) implements Comparable<Cards>, WithCardValues {


    private Cards cardsWithoutMaxCardValue() {
        return new Cards(this.withoutMaxCardValue());
    }

    public boolean isStraight() {
        return cardValues.stream().map(Enum::ordinal)
                .filter(i -> i <= this.maxCardValue().ordinal()
                    && i > (this.maxCardValue().ordinal() - 5))
                .count() == 5;
    }

    @Override
    public int compareTo(Cards cards) {
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
