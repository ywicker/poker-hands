package com.kata;

import org.junit.jupiter.api.Test;

import static com.kata.CardNumber.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CompareCardNumberTest {

    @Test
    void should_one_less_than_two(){
        assertThat(ONE).isLessThan(TWO);
    }
    @Test
    void should_one_is_equals_one(){
        assertThat(ONE).isEqualTo(ONE);
    }
    @Test
    void should_have_fourteen_card_numbers(){
        var cardNumberValues = CardNumber.values();

        assertThat(cardNumberValues).hasSize(14);
    }
    @Test
    void should_compare_card_numbers_by_values() {
        assertThat(AS).isGreaterThan(KING);
        assertThat(KING).isGreaterThan(QUEEN);
        assertThat(QUEEN).isGreaterThan(JACK);
        assertThat(JACK).isGreaterThan(TEN);
        assertThat(TEN).isGreaterThan(NINE);
        assertThat(NINE).isGreaterThan(HEIGHT);
        assertThat(HEIGHT).isGreaterThan(SEVEN);
        assertThat(SEVEN).isGreaterThan(SIX);
        assertThat(SIX).isGreaterThan(FIVE);
        assertThat(FIVE).isGreaterThan(FOUR);
        assertThat(FOUR).isGreaterThan(THREE);
        assertThat(THREE).isGreaterThan(TWO);
        assertThat(TWO).isGreaterThan(ONE);
    }
}
