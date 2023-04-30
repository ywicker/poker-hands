package com.kata;

import java.util.Set;
import java.util.stream.Collectors;

public class SingleCards implements Comparable<SingleCards> {
    private final Set<CardValue> cardValues;

    public SingleCards(Set<CardValue> cardValues) {
        this.cardValues = cardValues;
    }

    public CardValue maxCardValue() {
        return cardValues.stream().max(CardValue::compareTo)
                .orElseThrow();
    }

    public SingleCards withoutCardValues(CardValue cardValue) {
        return new SingleCards(
                cardValues.stream()
                        .filter(value -> !cardValue.equals(value))
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public int compareTo(SingleCards cards) {
        if(cardValues.isEmpty()) {
            return 0;
        }

        var compareCard = this.maxCardValue().compareTo(cards.maxCardValue());
        if(compareCard == 0) {
            return this.withoutCardValues(this.maxCardValue())
                    .compareTo(
                            cards.withoutCardValues(cards.maxCardValue())
                    );
        }
        return compareCard;
    }
}
