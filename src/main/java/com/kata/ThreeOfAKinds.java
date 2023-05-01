package com.kata;

import java.util.Set;

public record ThreeOfAKinds(Set<CardValue> cardValues) implements Comparable<ThreeOfAKinds>, WithCardValues {


    @Override
    public int compareTo(ThreeOfAKinds threeOfAKinds) {
        if(this.cardValues.isEmpty() && threeOfAKinds.cardValues.isEmpty()){
            return 0;
        }

        var compareSize = Integer.compare(this.cardValues.size(), threeOfAKinds.cardValues.size());
        if(compareSize != 0) {
            return compareSize;
        }

        var compareMax = Integer.compare(this.maxCardValue().ordinal(), threeOfAKinds.maxCardValue().ordinal());
        if(compareMax != 0) {
            return compareMax;
        }
        return threeOfAKindsWithoutMaxCardValue()
                .compareTo(threeOfAKinds.threeOfAKindsWithoutMaxCardValue());
    }

    public ThreeOfAKinds threeOfAKindsWithoutMaxCardValue() {
        return new ThreeOfAKinds(this.withoutMaxCardValue());
    }
}
