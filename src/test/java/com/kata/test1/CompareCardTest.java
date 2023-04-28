package com.kata.test1;

import org.junit.jupiter.api.Test;

import static com.kata.test1.CardColor.*;
import static com.kata.test1.CardNumber.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CompareCardTest {

    @Test
    void should_two_less_than_three(){
        // given
        var twoS = new Card(TWO, SPADES);
        var threeC = new Card(THREE, CLUBS);

        // when then
        assertThat(twoS).isLessThan(threeC);
    }
    @Test
    void should_two_is_equals_two(){
        // given
        var twoC = new Card(TWO, CLUBS);
        var twoS = new Card(TWO, SPADES);

        // when then
        assertThat(twoC).isEqualTo(twoS);
    }
    @Test
    void should_compare_card_by_values() {
        // given
        var twoS = new Card(TWO, SPADES);
        var threeS = new Card(THREE, SPADES);
        var fourD = new Card(FOUR, DIAMONDS);
        var fiveH = new Card(FIVE, HEARTS);
        var sixH = new Card(SIX, HEARTS);
        var sevenS = new Card(SEVEN, SPADES);
        var heightD = new Card(HEIGHT, DIAMONDS);
        var nineH = new Card(NINE, HEARTS);
        var tenC = new Card(TEN, CLUBS);
        var jackC = new Card(JACK, CLUBS);
        var queenD = new Card(QUEEN, DIAMONDS);
        var kingC = new Card(KING, CLUBS);
        var asC = new Card(AS, CLUBS);

        // when then
        assertThat(asC).isGreaterThan(kingC);
        assertThat(kingC).isGreaterThan(queenD);
        assertThat(queenD).isGreaterThan(jackC);
        assertThat(jackC).isGreaterThan(tenC);
        assertThat(tenC).isGreaterThan(nineH);
        assertThat(nineH).isGreaterThan(heightD);
        assertThat(heightD).isGreaterThan(sevenS);
        assertThat(sevenS).isGreaterThan(sixH);
        assertThat(sixH).isGreaterThan(fiveH);
        assertThat(fiveH).isGreaterThan(fourD);
        assertThat(fourD).isGreaterThan(threeS);
        assertThat(threeS).isGreaterThan(twoS);
    }
}
