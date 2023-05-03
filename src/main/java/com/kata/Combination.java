package com.kata;

import java.util.Optional;

public record Combination(CombinationValue value, SortedCardValues cardsComparator, Optional<Combination> lessCardComparator) implements Comparable<Combination> {
    public Combination(CombinationValue value, SortedCardValues cardsComparator) {
        this(value, cardsComparator, Optional.empty());
    }
    // 1) TODO : build FULLHOUSE with Combination(THREE, cardsComparator = value, lessCardComparator = Combination(PAIR, cardsComparator = value))
    // 2) TODO : build 2 PAIRS with Combination(PAIR, cardsComparator = bestValue, lessCardComparator = Combination(PAIR, cardsComparator = secondValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = value)))
    // 3) TODO : build STRAIGH with Combination(STRAIGH, cardsComparator = bestValue, lessCardComparator = empty)
    // 4) TODO : build FLUSH with Combination(FLUSH, cardsComparator = bestValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = secondValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = thirdValue...)))
    //        OU build FLUSH with Combination(FLUSH, cardsComparator = bestValue, lessCardComparator = Combination(FLUSH, cardsComparator = secondValue, lessCardComparator = Combination(FLUSH, cardsComparator = thirdValue...)))
    // 5) TODO : build HIGHTCARD with Combination(HIGHTCARD, cardsComparator = bestValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = secondValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = thirdValue...))e)
    // 6) TODO : cardsComparator => CardValue
    @Override
    public int compareTo(Combination combination) {
        var compareValue = this.value.compareTo(combination.value);
        if(compareValue != 0){
            return compareValue;
        }
        return this.cardsComparator.compareTo(combination.cardsComparator);
    }
}
