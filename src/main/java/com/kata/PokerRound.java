package com.kata;

import java.util.*;

public record PokerRound(PokerHand... pokerHands) {
    public String result() {

        var list = Arrays.stream(pokerHands).sorted(Comparator.reverseOrder()).toList();
        var one = list.listIterator();
        var pokerHand1 = one.next();
        Set<PokerHand> pokerHandsWin = new HashSet<>();
        pokerHandsWin.add(pokerHand1);
        for(ListIterator<PokerHand> two = list.listIterator(one.nextIndex()); two.hasNext();) {
            var pokerHand2 = two.next();
            var compare = pokerHand1.compareTo(pokerHand2);
            if(compare == 0) {
                pokerHandsWin.add(pokerHand2);
            }
        }

        return new PokerResult(pokerHandsWin).report();
    }
}
