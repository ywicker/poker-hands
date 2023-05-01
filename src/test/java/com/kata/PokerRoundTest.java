package com.kata;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kata.CardColor.*;
import static com.kata.CardValue.*;
import static com.kata.PokerResult.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PokerRoundTest {

    @Nested
    class HighCardWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            pokerHand(new Card(THREE, HEARTS)),
                            pokerHand(new Card(TWO, HEARTS)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(THREE, HEARTS)),
                            pokerHand(new Card(FOUR, HEARTS)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FOUR, HEARTS)),
                            pokerHand(new Card(FOUR, DIAMONDS)),
                            EGALITY),
                    Arguments.of(
                            pokerHand(new Card(FOUR, HEARTS), new Card(THREE, HEARTS)),
                            pokerHand(new Card(FOUR, DIAMONDS), new Card(TWO, DIAMONDS)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(FOUR, HEARTS), new Card(THREE, HEARTS)),
                            pokerHand(new Card(FOUR, DIAMONDS), new Card(THREE, DIAMONDS)),
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
                            pokerHand(new Card(THREE, DIAMONDS), new Card(THREE, HEARTS)),
                            pokerHand(new Card(TWO, HEARTS), new Card(THREE, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(AS, HEARTS), new Card(THREE, HEARTS)),
                            pokerHand(new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(THREE, DIAMONDS), new Card(THREE, HEARTS)),
                            pokerHand(new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS)),
                            pokerHand(new Card(FOUR, SPADES), new Card(FOUR, CLUBS)),
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
                            pokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(TWO, HEARTS)),
                            pokerHand(new Card(THREE, HEARTS), new Card(THREE, DIAMONDS), new Card(TWO, CLUBS), new Card(TWO, DIAMONDS)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE, HEARTS), new Card(FIVE, CLUBS), new Card(TWO, HEARTS), new Card(TWO, DIAMONDS)),
                            pokerHand(new Card(SIX, HEARTS), new Card(SIX, CLUBS), new Card(TWO, CLUBS)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(THREE, HEARTS), new Card(THREE, CLUBS), new Card(TWO, HEARTS), new Card(TWO, DIAMONDS)),
                            pokerHand(new Card(FOUR, HEARTS), new Card(FOUR, CLUBS), new Card(TWO, CLUBS), new Card(TWO, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE, CLUBS), new Card(FIVE, SPADES), new Card(THREE, CLUBS), new Card(THREE, SPADES)),
                            pokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(THREE, HEARTS), new Card(THREE, DIAMONDS)),
                            EGALITY),
                    Arguments.of(
                            pokerHand(new Card(FIVE, CLUBS), new Card(FIVE, SPADES), new Card(THREE, CLUBS), new Card(THREE, SPADES), new Card(SIX, DIAMONDS)),
                            pokerHand(new Card(FIVE, DIAMONDS), new Card(FIVE, HEARTS), new Card(THREE, DIAMONDS), new Card(THREE, HEARTS), new Card(AS, DIAMONDS)),
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
                            pokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES)),
                            pokerHand(new Card(SIX, HEARTS), new Card(SIX, DIAMONDS), new Card(TWO, HEARTS)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            pokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES)),
                            pokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(AS, HEARTS), new Card(AS, DIAMONDS), new Card(AS, SPADES)),
                            pokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(THREE, HEARTS)),
                            pokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(FOUR, HEARTS)),
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
                            pokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            pokerHand(new Card(SIX, DIAMONDS), new Card(SIX, DIAMONDS), new Card(SIX, DIAMONDS), new Card(TWO, DIAMONDS), new Card(THREE, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            pokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(KING, SPADES)),
                            pokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            pokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            pokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
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
    class FlushWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            pokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            pokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(SIX, SPADES)),
                            BLACK_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_flush(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerResult expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class FourOfAKindWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            pokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(FIVE, CLUBS)),
                            pokerHand(new Card(SIX, HEARTS), new Card(SIX, DIAMONDS), new Card(SIX, SPADES), new Card(TWO, CLUBS)),
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
        return new PokerHand(Arrays.stream(cards).collect(Collectors.toSet()));
    }
}
