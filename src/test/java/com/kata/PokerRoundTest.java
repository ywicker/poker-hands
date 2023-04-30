package com.kata;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.kata.CardValue.*;
import static com.kata.PokerResult.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PokerRoundTest {

    @Nested
    class HighCardWins {

        @Test
        void should_black_poker_hand_wins(){
            // given
            var blackPokerHand = new PokerHand(new Card(THREE));
            var whitePokerHand = new PokerHand(new Card(TWO));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(BLACK_WINS);
        }

        @Test
        void should_white_poker_hand_wins() {
            // given
            var blackPokerHand = new PokerHand(new Card(THREE));
            var whitePokerHand = new PokerHand(new Card(FOUR));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }

        @Test
        void should_egality() {
            // given
            var blackPokerHand = new PokerHand(new Card(FOUR));
            var whitePokerHand = new PokerHand(new Card(FOUR));
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
            var blackPokerHand = new PokerHand(new Card(THREE), new Card(THREE));
            var whitePokerHand = new PokerHand(new Card(TWO), new Card(THREE));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(BLACK_WINS);
        }
        @Test
        void should_white_poker_hand_wins_with_pair(){
            // given
            var blackPokerHand = new PokerHand(new Card(AS), new Card(THREE));
            var whitePokerHand = new PokerHand(new Card(FOUR), new Card(FOUR));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }
        @Test
        void should_white_poker_hand_wins_with_greater_pair(){
            // given
            var blackPokerHand = new PokerHand(new Card(THREE), new Card(THREE));
            var whitePokerHand = new PokerHand(new Card(FOUR), new Card(FOUR));
            var pokerRound = new PokerRound(blackPokerHand, whitePokerHand);

            // when
            var result = pokerRound.result();

            // then
            assertThat(result).isEqualTo(WHITE_WINS);
        }
    }

}
