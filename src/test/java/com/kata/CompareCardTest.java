package com.kata;

import org.junit.jupiter.api.Test;


import static com.kata.CardValue.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CompareCardTest {

    @Test
    void should_two_less_than_three(){
        // given
        var twoS = new Card(TWO, CardColor.HEARTS);
        var threeC = new Card(THREE, CardColor.HEARTS);

        // when then
        assertThat(twoS).isLessThan(threeC);
    }
    @Test
    void should_two_is_equals_two(){
        // given
        var twoC = new Card(TWO, CardColor.HEARTS);
        var twoS = new Card(TWO, CardColor.HEARTS);

        // when then
        assertThat(twoC).isEqualTo(twoS);
    }
    @Test
    void should_compare_card_by_values() {
        // given
        var twoS = new Card(TWO, CardColor.HEARTS);
        var threeS = new Card(THREE, CardColor.HEARTS);
        var fourD = new Card(FOUR, CardColor.HEARTS);
        var fiveH = new Card(FIVE, CardColor.HEARTS);
        var sixH = new Card(SIX, CardColor.HEARTS);
        var sevenS = new Card(SEVEN, CardColor.HEARTS);
        var heightD = new Card(HEIGHT, CardColor.HEARTS);
        var nineH = new Card(NINE, CardColor.HEARTS);
        var tenC = new Card(TEN, CardColor.HEARTS);
        var jackC = new Card(JACK, CardColor.HEARTS);
        var queenD = new Card(QUEEN, CardColor.HEARTS);
        var kingC = new Card(KING, CardColor.HEARTS);
        var asC = new Card(ACE, CardColor.HEARTS);

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
