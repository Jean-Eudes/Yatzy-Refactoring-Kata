package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.Dice.FIVE;
import static org.example.Dice.FOUR;
import static org.example.Dice.ONE;
import static org.example.Dice.SIX;
import static org.example.Dice.THREE;
import static org.example.Dice.TWO;

import org.junit.jupiter.api.Test;

class YatzyTest {

  @Test
  public void chance_scores_sum_of_all_dice() {
    assertThat(new Yatzy(TWO, THREE, FOUR, FIVE, ONE).chance()).isEqualTo(15);
    assertThat(new Yatzy(THREE, THREE, FOUR, FIVE, ONE).chance()).isEqualTo(16);
  }

  @Test
  public void yatzy_scores_50() {
    assertThat(new Yatzy(FOUR, FOUR, FOUR, FOUR, FOUR).yatzy()).isEqualTo(50);
    assertThat(new Yatzy(SIX, SIX, SIX, SIX, SIX).yatzy()).isEqualTo(50);
    assertThat(new Yatzy(SIX, SIX, SIX, SIX, THREE).yatzy()).isEqualTo(0);
  }

  @Test
  public void test_1s() {
    assertThat(new Yatzy(ONE, TWO, THREE, FOUR, FIVE).ones()).isEqualTo(1);
    assertThat(new Yatzy(ONE, TWO, ONE, FOUR, FIVE).ones()).isEqualTo(2);
    assertThat(new Yatzy(SIX, TWO, TWO, FOUR, FIVE).ones()).isEqualTo(0);
    assertThat(new Yatzy(ONE, TWO, ONE, ONE, ONE).ones()).isEqualTo(4);
  }

  @Test
  public void test_2s() {
    assertThat(new Yatzy(ONE, TWO, THREE, TWO, SIX).twos()).isEqualTo(4);
    assertThat(new Yatzy(TWO, TWO, TWO, TWO, TWO).twos()).isEqualTo(10);
  }

  @Test
  public void test_threes() {
    assertThat(new Yatzy(ONE, TWO, THREE, TWO, THREE).threes()).isEqualTo(6);
    assertThat(new Yatzy(TWO, THREE, THREE, THREE, THREE).threes()).isEqualTo(12);
  }

  @Test
  public void fours_test() {
    assertThat(new Yatzy(FOUR, FOUR, FOUR, FIVE, FIVE).fours()).isEqualTo(12);
    assertThat(new Yatzy(FOUR, FOUR, FIVE, FIVE, FIVE).fours()).isEqualTo(8);
    assertThat(new Yatzy(FOUR, FIVE, FIVE, FIVE, FIVE).fours()).isEqualTo(4);
  }

  @Test
  public void fives() {
    assertThat(new Yatzy(FOUR, FOUR, FOUR, FIVE, FIVE).fives()).isEqualTo(10);
    assertThat(new Yatzy(FOUR, FOUR, FIVE, FIVE, FIVE).fives()).isEqualTo(15);
    assertThat(new Yatzy(FOUR, FIVE, FIVE, FIVE, FIVE).fives()).isEqualTo(20);
  }

  @Test
  public void sixes_test() {
    assertThat(new Yatzy(FOUR, FOUR, FOUR, FIVE, FIVE).sixes()).isEqualTo(0);
    assertThat(new Yatzy(FOUR, FOUR, SIX, FIVE, FIVE).sixes()).isEqualTo(6);
    assertThat(new Yatzy(SIX, FIVE, SIX, SIX, FIVE).sixes()).isEqualTo(18);
  }

  @Test
  public void one_pair() {
    assertThat(new Yatzy(THREE, FOUR, THREE, FIVE, SIX).score_pair()).isEqualTo(6);
    assertThat(new Yatzy(FIVE, THREE, THREE, THREE, FIVE).score_pair()).isEqualTo(10);
    assertThat(new Yatzy(FIVE, THREE, SIX, SIX, FIVE).score_pair()).isEqualTo(12);
  }

  @Test
  public void two_Pair() {
    assertThat(new Yatzy(THREE, THREE, FIVE, FOUR, FIVE).two_pair()).isEqualTo(16);
    assertThat(new Yatzy(THREE, THREE, FIVE, FIVE, FIVE).two_pair()).isEqualTo(16);
  }

  @Test
  public void three_of_a_kind() {
    assertThat(new Yatzy(THREE, THREE, THREE, FOUR, FIVE).three_of_a_kind()).isEqualTo(9);
    assertThat(new Yatzy(FIVE, THREE, FIVE, FOUR, FIVE).three_of_a_kind()).isEqualTo(15);
    assertThat(new Yatzy(THREE, THREE, THREE, THREE, FIVE).three_of_a_kind()).isEqualTo(9);
  }

  @Test
  public void four_of_a_knd() {
    assertThat(new Yatzy(THREE, THREE, THREE, THREE, FIVE).four_of_a_kind()).isEqualTo(12);
    assertThat(new Yatzy(FIVE, FIVE, FIVE, FOUR, FIVE).four_of_a_kind()).isEqualTo(20);
    assertThat(new Yatzy(THREE, THREE, THREE, THREE, THREE).three_of_a_kind()).isEqualTo(9);
  }

  @Test
  public void smallStraight() {
    assertThat(new Yatzy(ONE, TWO, THREE, FOUR, FIVE).smallStraight()).isEqualTo(15);
    assertThat(new Yatzy(TWO, THREE, FOUR, FIVE, ONE).smallStraight()).isEqualTo(15);
    assertThat(new Yatzy(ONE, TWO, TWO, FOUR, FIVE).smallStraight()).isEqualTo(0);
  }

  @Test
  public void largeStraight() {
    assertThat(new Yatzy(SIX, TWO, THREE, FOUR, FIVE).largeStraight()).isEqualTo(20);
    assertThat(new Yatzy(TWO, THREE, FOUR, FIVE, SIX).largeStraight()).isEqualTo(20);
    assertThat(new Yatzy(ONE, TWO, TWO, FOUR, FIVE).largeStraight()).isEqualTo(0);
    assertThat(new Yatzy(ONE, TWO, THREE, FOUR, FIVE).largeStraight()).isEqualTo(0);
  }

  @Test
  public void fullHouse() {
    assertThat(new Yatzy(SIX, TWO, TWO, TWO, SIX).fullHouse()).isEqualTo(18);
    assertThat(new Yatzy(TWO, THREE, FOUR, FIVE, SIX).fullHouse()).isEqualTo(0);
    assertThat(new Yatzy(SIX, SIX, SIX, SIX, SIX).fullHouse()).isEqualTo(0);
  }

  @Test
  void should_yatzy_constructor_failed_if_one_dice_is_null() {
    assertThatThrownBy(() -> new Yatzy(null, TWO, TWO, FOUR, FIVE)).isInstanceOf(NullPointerException.class);
  }
}