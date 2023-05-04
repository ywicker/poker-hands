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
import static org.assertj.core.api.Assertions.assertThat;

public class PokerRoundTest {
    static String BLACK_WINS = "Black Player wins";
    static String WHITE_WINS = "White Player wins";
    static String GREEN_WINS = "Green Player wins";
    static String EGALITY = "Egality between : Black Player and White Player";

    @Nested
    class HighCardWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(TWO, HEARTS)),
                            BLACK_WINS + " - with hight card: 3"),
                    Arguments.of(
                            blackPokerHand(new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, HEARTS)),
                            WHITE_WINS + " - with hight card: 4"),
                    Arguments.of(
                            blackPokerHand(new Card(FOUR, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS)),
                            EGALITY + " - with hight card: 4"),
                    Arguments.of(
                            blackPokerHand(new Card(FOUR, HEARTS), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS), new Card(TWO, DIAMONDS)),
                            BLACK_WINS + " - with hight card: 3, 4"),
                    Arguments.of(
                            blackPokerHand(new Card(FOUR, HEARTS), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS), new Card(THREE, DIAMONDS)),
                            EGALITY + " - with hight card: 4, 3")
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
                            BLACK_WINS + " - with pair: 3"),
                    Arguments.of(
                            blackPokerHand(new Card(ACE, HEARTS), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS)),
                            WHITE_WINS + " - with pair: 4"),
                    Arguments.of(
                            blackPokerHand(new Card(THREE, DIAMONDS), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS)),
                            WHITE_WINS + " - with pair: 4"),
                    Arguments.of(
                            blackPokerHand(new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS)),
                            whitePokerHand(new Card(FOUR, SPADES), new Card(FOUR, CLUBS)),
                            EGALITY + " - with pair: 4")
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
                            WHITE_WINS + " - with pair: 2, 3"),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, CLUBS), new Card(TWO, HEARTS), new Card(TWO, DIAMONDS)),
                            whitePokerHand(new Card(SIX, HEARTS), new Card(SIX, CLUBS), new Card(TWO, CLUBS)),
                            BLACK_WINS + " - with pair: 2, 5"),
                    Arguments.of(
                            blackPokerHand(new Card(THREE, HEARTS), new Card(THREE, CLUBS), new Card(TWO, HEARTS), new Card(TWO, DIAMONDS)),
                            whitePokerHand(new Card(FOUR, HEARTS), new Card(FOUR, CLUBS), new Card(TWO, CLUBS), new Card(TWO, SPADES)),
                            WHITE_WINS + " - with pair: 2, 4"),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, CLUBS), new Card(FIVE, SPADES), new Card(THREE, CLUBS), new Card(THREE, SPADES)),
                            whitePokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(THREE, HEARTS), new Card(THREE, DIAMONDS)),
                            EGALITY + " - with pair: 5, 3"),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, CLUBS), new Card(FIVE, SPADES), new Card(THREE, CLUBS), new Card(THREE, SPADES), new Card(SIX, DIAMONDS)),
                            whitePokerHand(new Card(FIVE, DIAMONDS), new Card(FIVE, HEARTS), new Card(THREE, DIAMONDS), new Card(THREE, HEARTS), new Card(ACE, DIAMONDS)),
                            WHITE_WINS + " - with pair: 5, 3 over Ace")
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
                            BLACK_WINS + " - with three of kind: 5"),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES)),
                            WHITE_WINS + " - with three of kind: King"),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES)),
                            whitePokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES)),
                            WHITE_WINS + " - with three of kind: King"),
                    Arguments.of(
                            blackPokerHand(new Card(ACE, HEARTS), new Card(ACE, DIAMONDS), new Card(ACE, SPADES)),
                            whitePokerHand(new Card(KING, HEARTS), new Card(KING, DIAMONDS), new Card(KING, SPADES)),
                            BLACK_WINS + " - with three of kind: Ace"),
                    Arguments.of(
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(THREE, HEARTS)),
                            whitePokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(FOUR, HEARTS)),
                            WHITE_WINS + " - with three of kind: 5 over 4")
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
                            BLACK_WINS + " - with straight: 6"),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(KING, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            WHITE_WINS + " - with straight: 6"),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            EGALITY + " - with straight: 6"),
                    Arguments.of(
                            blackPokerHand(new Card(SEVEN, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            BLACK_WINS + " - with straight: 7"),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(SEVEN, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, SPADES)),
                            WHITE_WINS + " - with straight: 7")
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
                            BLACK_WINS + " - with flush: 2, 5, Queen, 3, 4"),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, SPADES)),
                            whitePokerHand(new Card(TWO, SPADES), new Card(THREE, SPADES), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(QUEEN, SPADES)),
                            WHITE_WINS + " - with flush: 2, 5, 3, Queen, 4"),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            whitePokerHand(new Card(TWO, SPADES), new Card(THREE, SPADES), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(QUEEN, SPADES)),
                            EGALITY + " - with flush: 2, 5, Queen, 3, 4"),
                    Arguments.of(
                            blackPokerHand(new Card(SEVEN, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            whitePokerHand(new Card(TWO, SPADES), new Card(THREE, SPADES), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(QUEEN, SPADES)),
                            BLACK_WINS + " - with flush: 7, 5, Queen, 3, 4"),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            whitePokerHand(new Card(KING, SPADES), new Card(THREE, SPADES), new Card(FOUR, SPADES), new Card(FIVE, SPADES), new Card(QUEEN, SPADES)),
                            WHITE_WINS + " - with flush: King, 5, 3, Queen, 4")
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_flush(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }

    @Nested
    class FullHouseWins {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(ACE, HEARTS), new Card(KING, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(QUEEN, HEARTS)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(TWO, HEARTS), new Card(TWO, SPADES), new Card(SIX, HEARTS), new Card(SIX, SPADES)),
                            WHITE_WINS + " - with full house: 2 over 6"),
                    Arguments.of(
                            blackPokerHand(new Card(KING, HEARTS), new Card(KING, HEARTS), new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS), new Card(FOUR, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(TWO, HEARTS), new Card(HEIGHT, SPADES), new Card(SIX, HEARTS), new Card(SIX, SPADES)),
                            BLACK_WINS + " - with full house: 4 over King"),
                    Arguments.of(
                            blackPokerHand(new Card(KING, HEARTS), new Card(KING, HEARTS), new Card(FOUR, DIAMONDS), new Card(FOUR, HEARTS), new Card(FOUR, SPADES)),
                            whitePokerHand(new Card(TWO, DIAMONDS), new Card(TWO, HEARTS), new Card(HEIGHT, SPADES), new Card(HEIGHT, HEARTS), new Card(HEIGHT, SPADES)),
                            WHITE_WINS + " - with full house: 8 over 2")
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
                            blackPokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(FIVE, CLUBS), new Card(KING, CLUBS)),
                            whitePokerHand(new Card(SIX, HEARTS), new Card(SIX, DIAMONDS), new Card(SIX, SPADES), new Card(TWO, CLUBS), new Card(QUEEN, CLUBS)),
                            BLACK_WINS + " - with four of kind: 5 over King"),
                    Arguments.of(
                            blackPokerHand(new Card(QUEEN, HEARTS), new Card(QUEEN, DIAMONDS), new Card(QUEEN, SPADES), new Card(ACE, CLUBS), new Card(ACE, SPADES)),
                            whitePokerHand(new Card(FIVE, HEARTS), new Card(FIVE, DIAMONDS), new Card(FIVE, SPADES), new Card(FIVE, CLUBS), new Card(TWO, CLUBS)),
                            WHITE_WINS + " - with four of kind: 5 over 2")
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
                            BLACK_WINS + " - with straight flush: 6"),
                    Arguments.of(
                            blackPokerHand(new Card(KING, HEARTS), new Card(QUEEN, DIAMONDS), new Card(ACE, SPADES), new Card(JACK, CLUBS), new Card(TEN, CLUBS)),
                            whitePokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, HEARTS)),
                            WHITE_WINS + " - with straight flush: 6"),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, DIAMONDS), new Card(THREE, DIAMONDS), new Card(FOUR, DIAMONDS), new Card(FIVE, DIAMONDS), new Card(SIX, DIAMONDS)),
                            whitePokerHand(new Card(TWO, HEARTS), new Card(THREE, HEARTS), new Card(FOUR, HEARTS), new Card(FIVE, HEARTS), new Card(SIX, HEARTS)),
                            EGALITY + " - with straight flush: 6")
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_wins_with_greater_flush(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult) {
            should_expected_result_from_poker_hands(blackPokerHand, whitePokerHand, expectedResult);
        }
    }
    @Nested
    class ThirdPlayers {
        private static Stream<Arguments> provideCases() {
            return Stream.of(
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS)),
                            whitePokerHand(new Card(KING, HEARTS)),
                            greenPokerHand(new Card(ACE, HEARTS)),
                            GREEN_WINS + " - with hight card: Ace"),
                    Arguments.of(
                            blackPokerHand(new Card(TWO, HEARTS), new Card(KING, DIAMONDS)),
                            whitePokerHand(new Card(KING, HEARTS), new Card(KING, SPADES)),
                            greenPokerHand(new Card(ACE, HEARTS), new Card(KING, CLUBS)),
                            WHITE_WINS + " - with pair: King"),
                    Arguments.of(
                            blackPokerHand(new Card(KING, CLUBS), new Card(KING, DIAMONDS)),
                            whitePokerHand(new Card(ACE, HEARTS), new Card(QUEEN, CLUBS)),
                            greenPokerHand(new Card(KING, HEARTS), new Card(KING, SPADES)),
                            "Egality between : Black Player and Green Player" + " - with pair: King")
            );
        }
        @ParameterizedTest
        @MethodSource("provideCases")
        void should_expected_result_from_three_poker_hands(PokerHand blackPokerHand, PokerHand whitePokerHand, PokerHand greenPokerHand, String expectedResult){
            // given
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand, greenPokerHand);

            // when
            var result = pokerRound.reportDetail();

            // then
            assertThat(result).isEqualTo(expectedResult);
        }
    }

    private void should_expected_result_from_poker_hands(PokerHand blackPokerHand, PokerHand whitePokerHand, String expectedResult){
        // given
        var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

        // when
        var result = pokerRound.reportDetail();

        // then
        assertThat(result).startsWith(expectedResult);
    }

    public static PokerHand blackPokerHand(Card... cards) {
        return new PokerHand(new Player("Black Player"), Arrays.stream(cards).collect(Collectors.toSet()));
    }

    public static PokerHand whitePokerHand(Card... cards) {
        return new PokerHand(new Player("White Player"), Arrays.stream(cards).collect(Collectors.toSet()));
    }

    public static PokerHand greenPokerHand(Card... cards) {
        return new PokerHand(new Player("Green Player"), Arrays.stream(cards).collect(Collectors.toSet()));
    }
}
