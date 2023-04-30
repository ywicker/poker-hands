package com.kata;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kata.CardValue.AS;
import static com.kata.CardValue.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

public class PokerHandTest {

    @Nested
    class Pairs {
        @Test
        void should_return_pair() {
            // given
            var pokerHand = new PokerHand(List.of(new Card(FOUR), new Card(FOUR)));

            // when
            var pairs = pokerHand.pairs();

            // then
            assertThat(pairs).containsExactly(new Pair(FOUR));
        }
        @Test
        void should_not_return_pair_when_have_not_pairs() {
            // given
            var pokerHand = new PokerHand(List.of(new Card(AS), new Card(FOUR)));

            // when
            var pairs = pokerHand.pairs();

            // then
            assertThat(pairs).isEmpty();
        }
        @Test
        void should_not_return_pair_when_have_three_of_kind() {
            // given
            var pokerHand = new PokerHand(List.of(new Card(FOUR), new Card(FOUR), new Card(FOUR)));

            // when
            var pairs = pokerHand.pairs();

            // then
            assertThat(pairs).isEmpty();
        }

    }
}
