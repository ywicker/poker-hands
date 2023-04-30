package com.kata;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public record Pairs(Set<CardValue> cardValues) implements Comparable<Pairs> {

    public Optional<CardValue> maxPairValue() {
        return cardValues.stream().max(CardValue::compareTo);
    }

    @Override
    public int compareTo(Pairs pairs) {
        if(this.cardValues.isEmpty() && pairs.cardValues.isEmpty()){
            return 0;
        }

        var compare = Integer.compare(this.cardValues.size(), pairs.cardValues.size());
        if(compare != 0) {
            return compare;
        }

        var compareMax = Integer.compare(this.maxPairValue().get().ordinal(), pairs.maxPairValue().get().ordinal());
        if(compareMax != 0) {
            return compareMax;
        }
        return this.withoutCardValues(this.maxPairValue().get())
                .compareTo(pairs.withoutCardValues(pairs.maxPairValue().get()));
    }

    public Pairs withoutCardValues(CardValue cardValue) {
        return new Pairs(
                cardValues.stream()
                        .filter(value -> !cardValue.equals(value))
                        .collect(Collectors.toSet())
        );
    }
}
