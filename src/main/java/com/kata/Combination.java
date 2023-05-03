package com.kata;

import java.util.Optional;

public record Combination(CombinationValue value, Comparable cardValueComparator, Optional<Comparable> lessCombinationCardValues) implements Comparable<Combination> {
    public Combination(CombinationValue value, Comparable cardValueComparator) {
        this(value, cardValueComparator, Optional.empty());
    }
    public Combination(CombinationValue value, Comparable cardValueComparator, Comparable lessCombinationCardValues) {
        this(value, cardValueComparator, Optional.of(lessCombinationCardValues));
    }

    // TODO better Optional<Comparable> management from Combination and maxValue and lessCombinationCardValues.isEmpty()
    // 5) TODO : CardValues.compare -> add constaint on Cards to have same size

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
