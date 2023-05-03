package com.kata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record SortedCardValues(List<CardValue> cardValueList) implements Comparable<SortedCardValues> {

    @Override
    public int compareTo(SortedCardValues sortedCardValues) {
        var compareSize = Integer.compare(this.cardValueList.size(), sortedCardValues.cardValueList.size());
        if(compareSize != 0) {
            return compareSize;
        }

        var compareValue = 0;
        for (int index = 0; index< this.cardValueList.size(); index++) {
            compareValue = this.cardValueList.get(index).compareTo(sortedCardValues.cardValueList.get(index));
            if(compareValue != 0) {
                break;
            }
        }
        return compareValue;
    }

    public boolean containsValues(){
        return !cardValueList.isEmpty();
    }

    public SortedCardValues addSortedValuesFrom(CardValues cardValues){
        return addSortedValuesFrom(cardValues.cardValueSet());
    }

    public SortedCardValues addSortedValuesFrom(SortedCardValues sortedCardValues){
        return addSortedValuesFrom(new HashSet<>(sortedCardValues.cardValueList()));
    }
    private SortedCardValues addSortedValuesFrom(Set<CardValue> cardValues){
        var cardValueArray = new ArrayList<>(this.cardValueList);
        var test = cardValues.stream()
                .filter(value -> !this.cardValueList.contains(value))
                .sorted().toList();
        cardValueArray.addAll(
                test
        );

        return new SortedCardValues(cardValueArray);
    }
}
