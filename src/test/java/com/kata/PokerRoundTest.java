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
    static String BLACK_WINS = "Black Player wins";
    static String WHITE_WINS = "White Player wins";
    static String EGALITY = "Egality";

    @Nested
    class HighCardWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(TWO, HEARTS)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, HEARTS)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(FOUR, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS)),
                            EGALITY),
                    Arguments.of(
                            blackPokerHand(new Card(FOUR, HEARTS), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS), new Card(TWO, DIAMONDS)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(FOUR, HEARTS), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS), new Card(THREE, DIAMONDS)),
                            EGALITY)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_three_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class PairWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(THREE, DIAMONDS), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(TWO, HEARTS), new Card(THREE, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(AS, HEARTS), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(THREE, DIAMONDS), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS)),
                            whitePokerHand(new Card(FOUR, SPADES), new Card(FOUR, CLUBS)),
                            EGALITY)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_three_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class TwoPairWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(TWO, HEARTS)),
                            whitePokerHand(new Card(THREE, HEARTS), new Card(THREE, DIAMONDS), new Card(TWO, CLUBS), new Card(TWO, DIAMONDS)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, CLUBS), new Card(TWO, HEARTS), new Card(TWO, DIAMONDS)),
                            whitePokerHand(new Card(SIX, HEARTS), new Card(SIX, CLUBS), new Card(TWO, CLUBS)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(THREE, HEARTS), new Card(THREE, CLUBS), new Card(TWO, HEARTS), new Card(TWO, DIAMONDS)),
                            whitePokerHand(new Card(FOUR, HEARTS), new Card(FOUR, CLUBS), new Card(TWO, CLUBS), new Card(TWO, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, CLUBS), new Card(FIVE, SPADES), new Card(THREE, CLUBS), new Card(THREE, SPADES)),
                            whitePokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(THREE, HEARTS), new Card(THREE, DIAMONDS)),
                            EGALITY),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, CLUBS), new Card(FIVE, SPADES), new Card(THREE, CLUBS), new Card(THREE, SPADES), new Card(SIX, DIAMONDS)),
                            whitePokerHand(new Card(FIVE, DIAMONDS), new Card(FIVE, HEARTS), new Card(THREE, DIAMONDS), new Card(THREE, HEARTS), new Card(AS, DIAMONDS)),
                            WHITE_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_three_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class ThreeOfAKindWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES)),
                            whitePokerHand(new Card(SIX, HEARTS), new Card(SIX, DIAMONDS), new Card(TWO, HEARTS)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES)),
                            whitePokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(AS, HEARTS), new Card(AS, DIAMONDS), new Card(AS, SPADES)),
                            whitePokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(FOUR, HEARTS)),
                            WHITE_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_three_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class StraightWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(SIX, DIAMONDS), new Card(SIX, DIAMONDS), new Card(SIX, DIAMONDS), new Card(TWO, DIAMONDS), new Card(THREE, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(KING, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            EGALITY),
                    Arguments.of(
                            blackPokerHand(new Card(SEVEN, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(SEVEN, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            WHITE_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_Straight(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class FlushWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(SIX, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(TWO, SPADES), new Card(THREE, SPADES), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(QUEEN, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            whitePokerHand(new Card(TWO, SPADES), new Card(THREE, SPADES), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(QUEEN, SPADES)),
                            EGALITY),
                    Arguments.of(
                            blackPokerHand(new Card(SEVEN, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            whitePokerHand(new Card(TWO, SPADES), new Card(THREE, SPADES), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(QUEEN, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            whitePokerHand(new Card(KING, SPADES), new Card(THREE, SPADES), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(QUEEN, SPADES)),
                            WHITE_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_flush(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class CombinationWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(AS, HEARTS), new Card(KING, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(TWO, HEARTS), new Card(TWO, SPADES), new Card(SIX, HEARTS), new Card(SIX, SPADES)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(KING, HEARTS), new Card(KING, HEARTS), new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS), new Card(FOUR, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(TWO, HEARTS), new Card(HEIGHT, SPADES), new Card(SIX, HEARTS), new Card(SIX, SPADES)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(KING, HEARTS), new Card(KING, HEARTS), new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS), new Card(FOUR, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(TWO, HEARTS), new Card(HEIGHT, SPADES), new Card(HEIGHT, HEARTS), new Card(HEIGHT, SPADES)),
                            BLACK_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_flush(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class FourOfAKindWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(FIVE, CLUBS)),
                            whitePokerHand(new Card(SIX, HEARTS), new Card(SIX, DIAMONDS), new Card(SIX, SPADES), new Card(TWO, CLUBS)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(QUEEN, HEARTS), new Card(QUEEN, DIAMONDS), new Card(QUEEN, SPADES), new Card(AS, CLUBS), new Card(AS, SPADES)),
                            whitePokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(FIVE, CLUBS), new Card(TWO, CLUBS)),
                            WHITE_WINS)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_four_of_a_kind(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class StraightFlushWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, HEARTS)),
                            whitePokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES), new Card(KING, CLUBS), new Card(TWO, CLUBS)),
                            BLACK_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(KING, HEARTS), new Card(QUEEN, DIAMONDS), new Card(AS, SPADES), new Card(JACK, CLUBS), new Card(TEN, CLUBS)),
                            whitePokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, HEARTS)),
                            WHITE_WINS),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, DIAMONDS)),
                            whitePokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, HEARTS)),
                            EGALITY)
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_flush(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    private void should_expected_result_from_poker_hands(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult){
        // given
        var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

        // when
        var result = pokerRound.result();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    public static PokerHand blackPokerHand(Card... cards) {
        return new PokerHand(new Player("Black Player"), Arrays.stream(cards).collect(Collectors.toSet()));
    }

    public static PokerHand whitePokerHand(Card... cards) {
        return new PokerHand(new Player("White Player"), Arrays.stream(cards).collect(Collectors.toSet()));
    }
}
