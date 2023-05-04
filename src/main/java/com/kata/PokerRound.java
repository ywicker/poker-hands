package com.kata;

import java.util.*;

public record PokerRound(PokerHand... pokerHands) {
    public PokerRound {
        if (pokerHands.length < 2) {
            throw new java.lang.IllegalArgumentException(
                    String.format("PokerRound need 2 poker hands to be played"));
        }
    }
    public String report() {
        return result().report();
    }
    public String reportDetail() {
        return result().reportDetail();
    }

    private PokerResult result() {
        Set<PokerHand> pokerHandsWin = new HashSet<>();

        var pokerHandsRanked = Arrays.stream(pokerHands).sorted(Comparator.reverseOrder()).toList().listIterator();
        var pokerHand1 = pokerHandsRanked.next();
        pokerHandsWin.add(pokerHand1);

        while (pokerHandsRanked.hasNext()) {
            var pokerHand2 = pokerHandsRanked.next();
            var compare = pokerHand1.compareTo(pokerHand2);
            if(compare == 0) {
                pokerHandsWin.add(pokerHand2);
            }
        }

        return new PokerResult(pokerHandsWin);
    }
}
