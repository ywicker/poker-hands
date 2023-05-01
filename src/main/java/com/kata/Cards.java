package com.kata;

import java.util.Set;
import java.util.stream.Collectors;

public record Cards(Set<Card> cards) implements Comparable<Cards>, WithCardValues {


    @Override
    public Set<CardValue> cardValues() {
        return cards.stream().map(Card::value).collect(Collectors.toSet());
    }

    private Cards cardsWithoutMaxCardValue() {
        var withoutMaxCard = cards.stream()
                .filter(card -> this.withoutMaxCardValue().contains(card.value()))
                .collect(Collectors.toSet());
        return new Cards(withoutMaxCard);
    }

    public boolean isStraight() {
        return this.cardValues().stream().map(Enum::ordinal)
                .filter(i -> i <= this.maxCardValue().ordinal()
                    && i > (this.maxCardValue().ordinal() - 5))
                .count() == 5;
    }

    public boolean isFlush() {
        var groupByValues = cards.stream().collect(Collectors.groupingBy(Card::color));
        return groupByValues.entrySet().stream()
                .anyMatch(cardColorListEntry -> cardColorListEntry.getValue().size() == 5);
    }

    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    @Override
    public int compareTo(Cards cards) {
        if (this.cardValues().isEmpty()) {
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
