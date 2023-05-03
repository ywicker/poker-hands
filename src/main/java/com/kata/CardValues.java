package com.kata;

import java.util.Set;

public record CardValues(Set<CardValue> cardValueSet) {

    public boolean isStraight() {
        return cardValueSet.stream()
                .map(Enum::ordinal)
                .filter(i -> i <= maxCardValue().ordinal()
                        && i > (maxCardValue().ordinal() - 5))
                .count() == 5;
    }


    public CardValue maxCardValue() {
        return cardValueSet.stream().max(CardValue::compareTo).orElseThrow();
    }
}
