package com.kata;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kata.CombinationValue.*;

public class PokerHand implements Comparable<PokerHand> {

    public final Combination bestCombinaison;
    private final Cards cards;
    private final Player player;

    public PokerHand(Player player, Set<Card> cardSet) {
        var cardValues = new CardValues(cardSet.stream().map(Card::value).collect(Collectors.toSet()));
        this.player = player;
        this.cards = new Cards(cardSet, cardValues);
        this.bestCombinaison = buildBestCombinaison(this.cards);
    }

    private static Combination buildBestCombinaison(Cards cards) {
        var flush = cards.flush();
        var straight = cards.straight();
        if (flush.isPresent() && straight.isPresent()) {
            return new Combination(STRAIGTH_FLUSH, straight.get());
        }

        var fourOfAKinds = cards.fourOfAKinds();
        if (fourOfAKinds.isPresent()) {
            return new Combination(FOUR_OF_KIND, fourOfAKinds.get(), cards.cardValues().cardValuesWithout(fourOfAKinds.get()).optional());
        }

        var threeOfAKinds = cards.threeOfAKinds();
        var pairs = cards.pairs();
        if (threeOfAKinds.isPresent() && pairs.isPresent()) {
            return new Combination(FULL_HOUSSE, threeOfAKinds.get(), pairs);
        }

        if (flush.isPresent()) {
            return new Combination(FLUSH, flush.get());
        }

        if (straight.isPresent()) {
            return new Combination(STRAIGTH, straight.get());
        }

        if (threeOfAKinds.isPresent()) {
            return new Combination(THREE_OF_KIND, threeOfAKinds.get(), cards.cardValues().cardValuesWithout(threeOfAKinds.get()).optional());
        }

        if (pairs.isPresent()) {
            return new Combination(PAIRS, pairs.get(), cards.cardValues().cardValuesWithout(pairs.get()).optional());
        }

        return new Combination(HIGHT_CARDS, cards.cardValues());
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        return this.bestCombinaison.compareTo(pokerHand.bestCombinaison);
    }

    public Player player() {
        return player;
    }
}
