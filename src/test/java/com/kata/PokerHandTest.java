package com.kata;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kata.CardValue.AS;
import static com.kata.CardValue.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

public class PokerHandTest {

    @Nested
    @Disabled
    class Pairs {
        @Test
        void should_return_pair() {
            var pokerHand = new PokerHand(List.of(new Card(FOUR), new Card(FOUR)));

            assertThat(pokerHand)
                    .extracting("pairs")
                    .extracting("cardValues")
                    .asList().containsExactly(FOUR);
        }
        @Test
        void should_not_return_pair_when_have_not_pairs() {
            var pokerHand = new PokerHand(List.of(new Card(AS), new Card(FOUR)));

            assertThat(pokerHand)
                    .extracting("pairs")
                    .extracting("cardValues")
                    .asList().isEmpty();
        }
        @Test
        void should_not_return_pair_when_have_three_of_kind() {
            var pokerHand = new PokerHand(List.of(new Card(FOUR), new Card(FOUR), new Card(FOUR)));

            assertThat(pokerHand)
                    .extracting("pairs")
                    .extracting("cardValues")
                    .asList().isEmpty();
        }

    }
}
