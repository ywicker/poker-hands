package com.kata;

public record Combination(CombinationValue value, SortedCardValues cardsComparator, SortedCardValues lessCombinationCardComparator) implements Comparable<Combination> {
    public Combination(CombinationValue value, SortedCardValues cardsComparator) {
        this(value, cardsComparator, new SortedCardValues());
    }
    // 2) TODO : build 2 PAIRS with Combination(PAIR, cardsComparator = pairValues, lessCombinationCardComparator = other values))
    // 3) TODO : build FLUSH with Combination(FLUSH, cardsComparator = empty, lessCombinationCardComparator = all values))
    // 4) TODO : build HIGHTCARD with Combination(HIGHTCARD, cardsComparator = values, lessCombinationCardComparator = empty)
    // 5) TODO : SortedCardValues cardsComparator => CardValues Set<CardValue> with recursive compare max...

    // TODO ? : build FULLHOUSE with Combination(FULLHOUSE, cardsComparator = thirdOfKind Value, lessCardComparator = Pair Value
    // TODO ? : build STRAIGH with Combination(STRAIGH, cardsComparator = bestValue, lessCardComparator = empty)
    //            and STRAIGH_FLUSH
    @Override
    public int compareTo(Combination combination) {
        var compareCombinationType = this.value.compareTo(combination.value);
        if(compareCombinationType != 0){
            return compareCombinationType;
        }

        var compareCombinaisonCardValues = this.cardsComparator.compareTo(combination.cardsComparator);
        if(this.lessCombinationCardComparator.cardValueList().isEmpty() && combination.lessCombinationCardComparator.cardValueList().isEmpty() && compareCombinaisonCardValues != 0) {
            return compareCombinaisonCardValues;
        }
        return this.lessCombinationCardComparator.compareTo(combination.lessCombinationCardComparator);
    }
}
