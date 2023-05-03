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
        if (!flush.cardValueSet().isEmpty() && straight.isPresent()) {
            return new Combination(STRAIGTH_FLUSH, straight.get());
        }

        var fourOfAKinds = cards.fourOfAKinds();
        if (fourOfAKinds.isPresent()) {
            return new Combination(FOUR_OF_KIND, fourOfAKinds.get(), cards.cardValues().sortedValuesWithout(fourOfAKinds.get()));
        }

        var threeOfAKinds = cards.threeOfAKinds();
        var pairs = cards.pairs();
        if (threeOfAKinds.isPresent() && !pairs.cardValueSet().isEmpty()) {
            return new Combination(FULL_HOUSSE, threeOfAKinds.get(), cards.similarCardValueOdl(2));
        }

        if (!flush.cardValueSet().isEmpty()) {
            return new Combination(FLUSH, flush);
        }

        if (straight.isPresent()) {
            return new Combination(STRAIGTH, straight.get());
        }

        if (threeOfAKinds.isPresent()) {
            return new Combination(THREE_OF_KIND, threeOfAKinds.get(), cards.cardValues().sortedValuesWithout(threeOfAKinds.get()));
        }

        if (!pairs.cardValueSet().isEmpty()) {
            return new Combination(PAIRS, pairs, cards.cardValues().sortedValuesWithout(pairs));
        }

        return new Combination(HIGHT_CARDS, cards.cardValues());
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        return this.bestCombinaison.compareTo(pokerHand.bestCombinaison);
    }
}
