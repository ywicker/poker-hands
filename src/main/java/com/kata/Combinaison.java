package com.kata;

import java.util.Set;
import java.util.stream.Collectors;

public record Combinaison(boolean state, Comparable nextCompare) implements Comparable<Combinaison> {
    @Override
    public int compareTo(Combinaison combinaison) {
        if (this.state && !combinaison.state) {
            return 1;
        } else if (!this.state && combinaison.state) {
            return -1;
        }
        return this.nextCompare.compareTo(combinaison.nextCompare);
    }

    public static Combinaison straight(Cards cards, Comparable nextCompare) {
        return new Combinaison(
                cards.cardValues().stream()
                        .map(Enum::ordinal)
                        .filter(i -> i <= cards.maxCardValue().ordinal()
                                && i > (cards.maxCardValue().ordinal() - 5))
                        .count() == 5,
                nextCompare
        );
    }

    public static Combinaison flush(Set<Card> cardSet, Comparable nextCompare) {
        var groupByValues = cardSet.stream().collect(Collectors.groupingBy(Card::color));
        return new Combinaison(
                groupByValues.entrySet().stream()
                        .anyMatch(cardColorListEntry -> cardColorListEntry.getValue().size() == 5),
                nextCompare
        );
    }
}
