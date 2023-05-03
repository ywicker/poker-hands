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

    public SortedCardValues sortedValuesWithout(CardValues cardValues){
        var cardValueList = cardValueSet.stream()
                .filter(value -> !cardValues.cardValueSet.contains(value))
                .sorted().toList();

        return new SortedCardValues(cardValueList);
    }
    public SortedCardValues sortedValuesWithout(CardValue cardValue){
        var cardValueList = cardValueSet.stream()
                .filter(value -> !cardValue.equals(value))
                .sorted().toList();

        return new SortedCardValues(cardValueList);
    }
    private CardValues cardValuesWithoutMaxValue(){
        var cardValues = cardValueSet.stream()
                .filter(value -> !this.maxCardValue().equals(value))
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
