package com.kata;

import org.junit.jupiter.api.Test;


import static com.kata.CardValue.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CompareCardTest {

    @Test
    void should_two_less_than_three(){
        // given
        var twoS = new Card(TWO);
        var threeC = new Card(THREE);

        // when then
        assertThat(twoS).isLessThan(threeC);
    }
    @Test
    void should_two_is_equals_two(){
        // given
        var twoC = new Card(TWO);
        var twoS = new Card(TWO);

        // when then
        assertThat(twoC).isEqualTo(twoS);
    }
    @Test
    void should_compare_card_by_values() {
        // given
        var twoS = new Card(TWO);
        var threeS = new Card(THREE);
        var fourD = new Card(FOUR);
        var fiveH = new Card(FIVE);
        var sixH = new Card(SIX);
        var sevenS = new Card(SEVEN);
        var heightD = new Card(HEIGHT);
        var nineH = new Card(NINE);
        var tenC = new Card(TEN);
        var jackC = new Card(JACK);
        var queenD = new Card(QUEEN);
        var kingC = new Card(KING);
        var asC = new Card(AS);

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
