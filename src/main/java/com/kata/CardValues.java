package com.kata;

import java.util.Set;
import java.util.stream.Collectors;

public record CardValues(Set<CardValue> cardValueSet) implements Comparable<CardValues> {

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

    public CardValues cardValuesWithoutMaxValue(){
        return cardValuesWithout(this.maxCardValue());
    }
    public CardValues cardValuesWithout(CardValues filteredCardValues){
        return cardValuesWithout(filteredCardValues.cardValueSet);
    }
    public CardValues cardValuesWithout(CardValue filteredCardValue){
        return cardValuesWithout(Set.of(filteredCardValue));
    }
    private CardValues cardValuesWithout(Set<CardValue> filteredCardValues){
        var cardValues = cardValueSet.stream()
                .filter(value -> !filteredCardValues.contains(value))
                .collect(Collectors.toSet());

        return new CardValues(cardValues);
    }

    @Override
    public int compareTo(CardValues cardValues) {
        var compareSize = Integer.compare(this.cardValueSet.size(), cardValues.cardValueSet.size());
        if(this.cardValueSet.isEmpty() && cardValues.cardValueSet.isEmpty() || compareSize != 0) {
            return compareSize;
        }

        var compareMaxValue = this.maxCardValue().compareTo(cardValues.maxCardValue());
        if(compareMaxValue != 0) {
            return compareMaxValue;
        }
        return this.cardValuesWithoutMaxValue().compareTo(cardValues.cardValuesWithoutMaxValue());
    }
}
