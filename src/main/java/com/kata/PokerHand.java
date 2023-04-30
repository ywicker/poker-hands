package com.kata;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record PokerHand(List<Card> cards) implements Comparable<PokerHand> {
    public CardValue maxCardValue() {
        return cards.stream().max(Card::compareTo)
                .orElseThrow().value();
    }

    public Pairs pairs() {
        Map<CardValue, List<Card>> groupByValues = cards.stream().collect(Collectors.groupingBy(Card::value));
        Set<CardValue> pairValues = groupByValues.entrySet().stream().filter(cardValueListEntry -> cardValueListEntry.getValue().size() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        return new Pairs(pairValues);
    }

    public PokerHand withoutCardValues(Set<CardValue> cardValues) {
        return new PokerHand(
                cards.stream()
                .filter(card -> !cardValues.contains(card.value()))
                        .toList()
        );
    }

    public PokerHand withoutPairs() {
        return withoutCardValues(this.pairs().cardValues());
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        var comparePairs = this.pairs().compareTo(pokerHand.pairs());
        if(comparePairs != 0) {
            return comparePairs;
        }

        var blackPokerHandNoPairs = this.withoutPairs();
        var whitePokerHandNoPairs = pokerHand.withoutPairs();

        if(blackPokerHandNoPairs.cards().isEmpty() && whitePokerHandNoPairs.cards().isEmpty()) {
            return 0;
        }

        var compareCard = blackPokerHandNoPairs.maxCardValue().compareTo(whitePokerHandNoPairs.maxCardValue());
        if(compareCard == 0) {
            return blackPokerHandNoPairs.withoutCardValues(Set.of(blackPokerHandNoPairs.maxCardValue()))
                            .compareTo(
                                    whitePokerHandNoPairs.withoutCardValues(Set.of(whitePokerHandNoPairs.maxCardValue()))
                            );
        }
        return compareCard;
    }
}
