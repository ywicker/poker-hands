package com.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PockerRoundTest {

    @Test
    void should_poker_hand_with_high_card_wins(){
        // given
        var blackPockerHand = new PockerHand(new Card(3));
        var whitePockerHand = new PockerHand(new Card(2));
        var pockerRound = new PockerRound(blackPockerHand, whitePockerHand);

        // when
        var result = pockerRound.pockerHandWinned();

        // then
        assertThat(result).isEqualTo(blackPockerHand);
    }
}
