package com.kata;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.kata.CardValue.*;
import static com.kata.PokerResult.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PokerRoundTest {

    @Nested
    class HighCardWins {

        @Test
        void should_black_poker_hand_wins(){
            // given
            var blackPokerHand = pokerHand(new Card(THREE));
            var whitePokerHand = pokerHand(new Card(TWO));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(BLACK_WINS);
        }

        @Test
        void should_white_poker_hand_wins() {
            // given
            var blackPokerHand = pokerHand(new Card(THREE));
            var whitePokerHand = pokerHand(new Card(FOUR));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }

        @Test
        void should_egality() {
            // given
            var blackPokerHand = pokerHand(new Card(FOUR));
            var whitePokerHand = pokerHand(new Card(FOUR));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(EGALITY);
        }

        @Test
        void should_black_poker_hand_wins_with_second_card() {
            // given
            var blackPokerHand = pokerHand(new Card(FOUR), new Card(THREE));
            var whitePokerHand = pokerHand(new Card(FOUR), new Card(TWO));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(BLACK_WINS);
        }

        @Test
        void should_egality_with_second_card() {
            // given
            var blackPokerHand = pokerHand(new Card(FOUR), new Card(THREE));
            var whitePokerHand = pokerHand(new Card(FOUR), new Card(THREE));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(EGALITY);
        }
    }

    @Nested
    class PairWins {
        @Test
        void should_black_poker_hand_wins_with_pair(){
            // given
            var blackPokerHand = pokerHand(new Card(THREE), new Card(THREE));
            var whitePokerHand = pokerHand(new Card(TWO), new Card(THREE));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(BLACK_WINS);
        }
        @Test
        void should_white_poker_hand_wins_with_pair(){
            // given
            var blackPokerHand = pokerHand(new Card(AS), new Card(THREE));
            var whitePokerHand = pokerHand(new Card(FOUR), new Card(FOUR));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }
        @Test
        void should_white_poker_hand_wins_with_greater_pair(){
            // given
            var blackPokerHand = pokerHand(new Card(THREE), new Card(THREE));
            var whitePokerHand = pokerHand(new Card(FOUR), new Card(FOUR));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }
    }

    @Nested
    class TwoPairWins {

        @Test
        void should_white_poker_hand_wins_with_two_pairs(){
            // given
            var blackPokerHand = pokerHand(new Card(FIVE), new Card(FIVE), new Card(TWO));
            var whitePokerHand = pokerHand(new Card(THREE), new Card(THREE), new Card(TWO), new Card(TWO));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }
        @Test
        void should_black_poker_hand_wins_with_two_pairs(){
            // given
            var blackPokerHand = pokerHand(new Card(FIVE), new Card(FIVE), new Card(TWO), new Card(TWO));
            var whitePokerHand = pokerHand(new Card(SIX), new Card(SIX), new Card(TWO));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(BLACK_WINS);
        }
        @Test
        void should_white_poker_hand_wins_with_greater_first_pair(){
            // given
            var blackPokerHand = pokerHand(new Card(THREE), new Card(THREE), new Card(TWO), new Card(TWO));
            var whitePokerHand = pokerHand(new Card(FOUR), new Card(FOUR), new Card(TWO), new Card(TWO));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }
        @Test
        void should_white_poker_hand_wins_with_greater_second_pair(){
            // given
            var blackPokerHand = pokerHand(new Card(FIVE), new Card(FIVE), new Card(TWO), new Card(TWO));
            var whitePokerHand = pokerHand(new Card(FIVE), new Card(FIVE), new Card(THREE), new Card(THREE));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }
        @Test
        void should_egality_with_two_pairs(){
            // given
            var blackPokerHand = pokerHand(new Card(FIVE), new Card(FIVE), new Card(THREE), new Card(THREE));
            var whitePokerHand = pokerHand(new Card(FIVE), new Card(FIVE), new Card(THREE), new Card(THREE));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(EGALITY);
        }
        @Test
        void should_white_poker_hand_wins_with_greater_last_card(){
            // given
            var blackPokerHand = pokerHand(new Card(FIVE), new Card(FIVE), new Card(THREE), new Card(THREE), new Card(SIX));
            var whitePokerHand = pokerHand(new Card(FIVE), new Card(FIVE), new Card(THREE), new Card(THREE), new Card(AS));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }
    }

    @Nested
    class ThreeOfAKindWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(FIVE)),
                            pokerHand(new Card(SIX), new Card(SIX), new Card(TWO)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(SIX)),
                            pokerHand(new Card(KING), new Card(KING), new Card(KING)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(FIVE)),
                            pokerHand(new Card(KING), new Card(KING), new Card(KING)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(AS), new Card(AS), new Card(AS)),
                            pokerHand(new Card(KING), new Card(KING), new Card(KING)),
                            BLACK_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_three_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerResult expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    private void should_expected_result_from_poker_hands(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerResult expectedResult){
        // given
        var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

        // when
        var result = pokerRound.result();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    public static PokerHand pokerHand(Card... cards) {
        return new PokerHand(Arrays.stream(cards).toList());
    }
}
