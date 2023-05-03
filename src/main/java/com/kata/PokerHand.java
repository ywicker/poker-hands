package com.kata;

import java.util.Set;
import java.util.stream.Collectors;

import static com.kata.CombinationValue.*;

public class PokerHand implements Comparable<PokerHand> {

    private final Combination bestCombinaison;
    private final Cards cards;

    public PokerHand(Set<Card> cardSet) {
        var cardValues = new CardValues(cardSet.stream().map(Card::value).collect(Collectors.toSet()));
        this.cards = new Cards(cardSet, cardValues);
        this.bestCombinaison = buildBestCombinaison(this.cards);
    }

    private static Combination buildBestCombinaison(Cards cards) {
        var flush = cards.flush();
        var straight = cards.straight();
        if (flush.containsValues() && straight.containsValues()) {
            return new Combination(STRAIGTH_FLUSH, cards.hightCards());
        }

        var fourOfAKinds = cards.fourOfAKinds();
        if (fourOfAKinds.containsValues()) {
            return new Combination(FOUR_OF_KIND, fourOfAKinds.addSortedValuesFrom(cards.cardValues()));
        }

        var threeOfAKinds = cards.threeOfAKinds();
        var pairs = cards.pairs();
        if (threeOfAKinds.containsValues() && !pairs.cardValueSet().isEmpty()) {
            return new Combination(FULL_HOUSSE, threeOfAKinds, cards.similarCardValue(2));
        }

        if (flush.containsValues()) {
            return new Combination(FLUSH, cards.cardValues());
        }

        if (straight.containsValues()) {
            return new Combination(STRAIGTH, cards.hightCards());
        }

        if (threeOfAKinds.containsValues()) {
            return new Combination(THREE_OF_KIND, threeOfAKinds.addSortedValuesFrom(cards.cardValues()));
        }

        if (!pairs.cardValueSet().isEmpty()) {
            return new Combination(PAIRS, pairs, cards.cardValues().sortedValuesWithout(pairs));
        }

        return new Combination(HIGHT_CARDS, cards.hightCards());
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        return this.bestCombinaison.compareTo(pokerHand.bestCombinaison);
    }
}
