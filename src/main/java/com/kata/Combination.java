package com.kata;

import java.util.Optional;

public record Combination<T extends Comparable>(CombinationValue value, Comparable cardValueComparator, Optional<T> lessCombinationCardValues) implements Comparable<Combination> {
    public Combination(CombinationValue value, Comparable cardValueComparator) {
        this(value, cardValueComparator, Optional.empty());
    }

    @Override
    public int compareTo(Combination combination) {
        var compareCombinationType = this.value.compareTo(combination.value);
        if(compareCombinationType != 0){
            return compareCombinationType;
        }

        var compareCombinaisonCardValues = this.cardValueComparator.compareTo(combination.cardValueComparator);
        if(this.lessCombinationCardValues.isEmpty() && combination.lessCombinationCardValues.isEmpty() || compareCombinaisonCardValues != 0) {
            return compareCombinaisonCardValues;
        }
        return this.lessCombinationCardValues.get().compareTo(combination.lessCombinationCardValues.get());
    }
}
