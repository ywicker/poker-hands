package com.kata;

public record Combination(CombinationValue value, SortedCardValues cardsComparator, SortedCardValues lessCardComparator) implements Comparable<Combination> {
    public Combination(CombinationValue value, SortedCardValues cardsComparator) {
        this(value, cardsComparator, new SortedCardValues());
    }
    // 1) TODO : build FULLHOUSE with Combination(FULLHOUSE, cardsComparator = value, lessCardComparator = Combination(PAIR, cardsComparator = value))
    // 2) TODO : build 2 PAIRS with Combination(PAIR, cardsComparator = bestValue, lessCardComparator = Combination(PAIR, cardsComparator = secondValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = value)))
    // 3) TODO : build STRAIGH with Combination(STRAIGH, cardsComparator = bestValue, lessCardComparator = empty)
    // 4) TODO : build FLUSH with Combination(FLUSH, cardsComparator = bestValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = secondValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = thirdValue...)))
    //        OU build FLUSH with Combination(FLUSH, cardsComparator = bestValue, lessCardComparator = Combination(FLUSH, cardsComparator = secondValue, lessCardComparator = Combination(FLUSH, cardsComparator = thirdValue...)))
    // 5) TODO : build HIGHTCARD with Combination(HIGHTCARD, cardsComparator = bestValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = secondValue, lessCardComparator = Combination(HIGHTCARD, cardsComparator = thirdValue...)))
    // 6) TODO : SortedCardValues cardsComparator => CardValues Optional<bestValue>

    // TODO ? : build FULLHOUSE with Combination(THREE, cardsComparator = value, lessCardComparator = Combination(PAIR, cardsComparator = value))
    @Override
    public int compareTo(Combination combination) {
        var compareCombinationType = this.value.compareTo(combination.value);
        if(compareCombinationType != 0){
            return compareCombinationType;
        }

        var compareCombinaisonCardValues = this.cardsComparator.compareTo(combination.cardsComparator);
        if(this.lessCardComparator.cardValueList().isEmpty() && combination.lessCardComparator.cardValueList().isEmpty() && compareCombinaisonCardValues != 0) {
            return compareCombinaisonCardValues;
        }
        return this.lessCardComparator.compareTo(combination.lessCardComparator);
    }
}
