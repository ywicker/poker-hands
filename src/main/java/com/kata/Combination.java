package com.kata;

public record Combination(CombinationValue value, SortedCardValues cardsComparator) implements Comparable<Combination> {
    
    @Override
    public int compareTo(Combination combination) {
        var compareValue = this.value.compareTo(combination.value);
        if(compareValue != 0){
            return compareValue;
        }
        return this.cardsComparator.compareTo(combination.cardsComparator);
    }
}
