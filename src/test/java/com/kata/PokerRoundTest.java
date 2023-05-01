package com.kata;

import org.junit.jupiter.api.Nested;
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
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            pokerHand(new Card(THREE)),
                            pokerHand(new Card(TWO)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(THREE)),
                            pokerHand(new Card(FOUR)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FOUR)),
                            pokerHand(new Card(FOUR)),
                            EGALITY),
                    Arguments.of(
                            pokerHand(new Card(FOUR), new Card(THREE)),
                            pokerHand(new Card(FOUR), new Card(TWO)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(FOUR), new Card(THREE)),
                            pokerHand(new Card(FOUR), new Card(THREE)),
                            EGALITY)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_three_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerResult expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class PairWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            pokerHand(new Card(THREE), new Card(THREE)),
                            pokerHand(new Card(TWO), new Card(THREE)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(AS), new Card(THREE)),
                            pokerHand(new Card(FOUR), new Card(FOUR)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(THREE), new Card(THREE)),
                            pokerHand(new Card(FOUR), new Card(FOUR)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FOUR), new Card(FOUR)),
                            pokerHand(new Card(FOUR), new Card(FOUR)),
                            EGALITY)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_three_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerResult expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class TwoPairWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(TWO)),
                            pokerHand(new Card(THREE), new Card(THREE), new Card(TWO), new Card(TWO)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(TWO), new Card(TWO)),
                            pokerHand(new Card(SIX), new Card(SIX), new Card(TWO)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(THREE), new Card(THREE), new Card(TWO), new Card(TWO)),
                            pokerHand(new Card(FOUR), new Card(FOUR), new Card(TWO), new Card(TWO)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(THREE), new Card(THREE)),
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(THREE), new Card(THREE)),
                            EGALITY),
                    Arguments.of(
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(THREE), new Card(THREE), new Card(SIX)),
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(THREE), new Card(THREE), new Card(AS)),
                            WHITE_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_three_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerResult expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
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
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(FIVE), new Card(THREE)),
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(FIVE), new Card(FOUR)),
                            WHITE_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_three_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerResult expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class StraightWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            pokerHand(new Card(TWO), new Card(THREE), new Card(FOUR), new Card(FIVE), new Card(SIX)),
                            pokerHand(new Card(SIX), new Card(SIX), new Card(SIX), new Card(TWO), new Card(THREE)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(TWO), new Card(THREE), new Card(FOUR), new Card(FIVE), new Card(KING)),
                            pokerHand(new Card(TWO), new Card(THREE), new Card(FOUR), new Card(FIVE), new Card(SIX)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(TWO), new Card(THREE), new Card(FOUR), new Card(FIVE), new Card(SIX)),
                            pokerHand(new Card(TWO), new Card(THREE), new Card(FOUR), new Card(FIVE), new Card(SIX)),
                            EGALITY)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_Straight(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerResult expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class FourOfAKindWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            pokerHand(new Card(FIVE), new Card(FIVE), new Card(FIVE), new Card(FIVE)),
                            pokerHand(new Card(SIX), new Card(SIX), new Card(SIX), new Card(TWO)),
                            BLACK_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_four_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerResult expectedResult) {
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
