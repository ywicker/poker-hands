package com.kata;

import java.util.Set;
import java.util.stream.Collectors;

public interface WithCardValues {
    Set<CardValue> cardValues();

    default CardValue maxCardValue() {
        return cardValues().stream().max(CardValue::compareTo).orElseThrow();
    }

    default Set<CardValue> withoutMaxCardValue() {
        return cardValues().stream()
                .filter(value -> !value.equals(this.maxCardValue()))
                .collect(Collectors.toSet());
    }
}
