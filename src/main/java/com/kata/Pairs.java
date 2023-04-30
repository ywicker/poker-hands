package com.kata;

import java.util.Set;

public record Pairs(Set<CardValue> cardValues) implements Comparable<Pairs>, WithCardValues {

    @Override
    public int compareTo(Pairs pairs) {
        if(this.cardValues.isEmpty() && pairs.cardValues.isEmpty()){
            return 0;
        }

        var compareSize = Integer.compare(this.cardValues.size(), pairs.cardValues.size());
        if(compareSize != 0) {
            return compareSize;
        }

        var compareMax = Integer.compare(this.maxCardValue().ordinal(), pairs.maxCardValue().ordinal());
        if(compareMax != 0) {
            return compareMax;
        }
        return pairWithoutMaxCardValue()
                .compareTo(pairs.pairWithoutMaxCardValue());
    }

    public Pairs pairWithoutMaxCardValue() {
        return new Pairs(this.withoutMaxCardValue());
    }
}
