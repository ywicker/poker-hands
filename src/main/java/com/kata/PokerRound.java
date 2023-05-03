package com.kata;

public record PokerRound(PokerHand blackPokerHand, PokerHand whitePokerHand) {
    public String result() {
        var compare = blackPokerHand.compareTo(whitePokerHand);
        if (compare > 0) {
            return new PokerResult(blackPokerHand).report();
        } else if (compare < 0) {
            return new PokerResult(whitePokerHand).report();
        }
        return new PokerResult(blackPokerHand, whitePokerHand).report();
    }
}
