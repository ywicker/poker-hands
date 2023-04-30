package com.kata;

import java.util.Optional;
import java.util.Set;

public record Pairs(Set<CardValue> cardValues) {

    public Optional<CardValue> maxPairValue() {
        return cardValues.stream().max(CardValue::compareTo);
    }
}
