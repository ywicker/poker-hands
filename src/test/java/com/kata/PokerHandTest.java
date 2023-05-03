package com.kata;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.kata.CardValue.AS;
import static com.kata.CardValue.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

public class PokerHandTest {

    @Nested
    @Disabled
    class Pairs {
        @Test
        void should_return_pair() {
            var pokerHand = new PokerHand(new Player("Black Player"), Set.of(new Card(FOUR, CardColor.HEARTS), new Card(FOUR, CardColor.HEARTS)));

            assertThat(pokerHand)
                    .extracting("pairs")
                    .extracting("cardValues")
                    .asList().containsExactly(FOUR);
        }

        @Test
        void should_not_return_pair_when_have_not_pairs() {
            var pokerHand = new PokerHand(new Player("Black Player"), Set.of(new Card(AS, CardColor.HEARTS), new Card(FOUR, CardColor.HEARTS)));

            assertThat(pokerHand)
                    .extracting("pairs")
                    .extracting("cardValues")
                    .asList().isEmpty();
        }

        @Test
        void should_not_return_pair_when_have_three_of_kind() {
            var pokerHand = new PokerHand(new Player("Black Player"), Set.of(new Card(FOUR, CardColor.HEARTS), new Card(FOUR, CardColor.HEARTS), new Card(FOUR, CardColor.HEARTS)));

            assertThat(pokerHand)
                    .extracting("pairs")
                    .extracting("cardValues")
                    .asList().isEmpty();
        }

    }
}
