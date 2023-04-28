package com.kata.test1;

import java.util.Objects;

public record Card(CardNumber cardNumber, CardColor cardColor) implements Comparable<Card> {

    @Override
    public int compareTo(Card card) {
        return this.cardNumber.compareTo(card.cardNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardColor);
    }
}
