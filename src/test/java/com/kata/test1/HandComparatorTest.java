package com.kata.test1;

import org.junit.jupiter.api.Test;

import static com.kata.test1.CardColor.*;
import static com.kata.test1.CardNumber.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HandComparatorTest {

    @Test
    void should_high_card_wins() {
        // given
        var blackHand = new PockerHand(
                new Card(TWO, SPADES),
                new Card(THREE, HEARTS),
                new Card(FIVE, HEARTS),
                new Card(HEIGHT, DIAMONDS),
                new Card(QUEEN, SPADES)
        );
        var whiteHand = new PockerHand(
                new Card(TWO, SPADES),
                new Card(THREE, HEARTS),
                new Card(FIVE, HEARTS),
                new Card(HEIGHT, DIAMONDS),
                new Card(AS, SPADES)
        );

        // when then
        assertThat(blackHand).isLessThan(whiteHand);
    }
}
