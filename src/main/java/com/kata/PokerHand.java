package com.kata;

import java.util.Arrays;

public record PokerHand(Card... cards) {
    public CardValue maxCardValue() {
        return Arrays.stream(cards).max(Card::compareTo)
                .orElseThrow().value();
    }
}
