package com.kata;

public record Card(CardValue value, CardColor color) implements Comparable<Card>  {

    @Override
    public int compareTo(Card card) {
        return this.value.compareTo(card.value);
    }
}
