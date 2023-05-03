package com.kata;

public record Combination(CombinationValue value, Comparable cardsComparator, SortedCardValues lessCombinationCardComparator) implements Comparable<Combination> {
    public Combination(CombinationValue value, SortedCardValues cardsComparator) {
        this(value, cardsComparator, new SortedCardValues());
    }
    // 3) TODO : build FLUSH with Combination(FLUSH, cardsComparator = cardValues, lessCombinationCardComparator = empty))
    // 4) TODO : build HIGHTCARD with Combination(HIGHTCARD, cardsComparator = values, lessCombinationCardComparator = empty)
    // 5) TODO : SortedCardValues cardsComparator => CardValues Set<CardValue> with recursive compare max...
    // 5) TODO : CardValues.compare -> add constaint on Cards to have same size

    // TODO ? : cardsComparator -> Comparable Type (equals CardValue or CardValues), lessCardComparator -> Comparable Type
    // TODO ? : build FULLHOUSE with Combination(FULLHOUSE, cardsComparator = thirdOfKind CardValue, lessCardComparator = Pair CardValue
    // TODO ? : build STRAIGH with Combination(STRAIGH, cardsComparator = CardValue, lessCardComparator = empty)
    //            and STRAIGH_FLUSH
    // 2) TODO : build 2 PAIRS with Combination(PAIR, cardsComparator = pairValues, lessCombinationCardComparator = other values))
    @Override
    public int compareTo(Combination combination) {
        var compareCombinationType = this.value.compareTo(combination.value);
        if(compareCombinationType != 0){
            return compareCombinationType;
        }

        var compareCombinaisonCardValues = this.cardsComparator.compareTo(combination.cardsComparator);
        if(this.lessCombinationCardComparator.cardValueList().isEmpty() && combination.lessCombinationCardComparator.cardValueList().isEmpty() || compareCombinaisonCardValues != 0) {
            return compareCombinaisonCardValues;
        }
        return this.lessCombinationCardComparator.compareTo(combination.lessCombinationCardComparator);
    }
}
