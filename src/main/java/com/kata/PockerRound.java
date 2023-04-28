package com.kata;

public record PockerRound(PockerHand blackPockerHand, PockerHand whitePockerHand) {
    public PockerHand pockerHandWinned() {
        return blackPockerHand;
    }
}
