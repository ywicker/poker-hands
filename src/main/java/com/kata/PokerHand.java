package com.kata;

import java.util.Set;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {

    private final Cards cards;
    public final SameValueCards pairs;
    public final SameValueCards threeOfAKinds;
    public final SameValueCards fourOfAKinds;
    public final Combinaison fullHousse;
    public final Combinaison straight;
    public final Combinaison flush;
    public final Combinaison straightFlush;

    public PokerHand(Set<Card> cardSet) {
        this.cards = new Cards(cardSet.stream().map(Card::value).collect(Collectors.toSet()));
        this.pairs = SameValueCards.fromCards(cardSet, 2, cards);
        this.threeOfAKinds = SameValueCards.fromCards(cardSet, 3, pairs);
        this.straight = Combinaison.straight(this.cards, threeOfAKinds);
        this.flush = Combinaison.flush(cardSet, straight);
        this.fullHousse = new Combinaison(
                !this.threeOfAKinds.cardValues().isEmpty() && !this.pairs.cardValues().isEmpty(),
                flush);
        this.fourOfAKinds = SameValueCards.fromCards(cardSet, 4, fullHousse);
        this.straightFlush = new Combinaison(straight.state() && flush.state(), fourOfAKinds);
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        return straightFlush.compareTo(pokerHand.straightFlush);
    }


}
