package org.example;

import static org.example.Dice.FIVE;
import static org.example.Dice.FOUR;
import static org.example.Dice.ONE;
import static org.example.Dice.SIX;
import static org.example.Dice.THREE;
import static org.example.Dice.TWO;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class YatzyTest {

  @Test
  public void chance_scores_sum_of_all_dice() {
    assertEquals(15, new Yatzy(TWO, THREE, FOUR, FIVE, ONE).chance());
    assertEquals(16, new Yatzy(THREE, THREE, FOUR, FIVE, ONE).chance());
  }

  @Test
  public void yatzy_scores_50() {
    assertEquals(50, new Yatzy(FOUR, FOUR, FOUR, FOUR, FOUR).yatzy());
    assertEquals(50, new Yatzy(SIX, SIX, SIX, SIX, SIX).yatzy());
    assertEquals(0, new Yatzy(SIX, SIX, SIX, SIX, THREE).yatzy());
  }

  @Test
  public void test_1s() {
    assertTrue(new Yatzy(ONE, TWO, THREE, FOUR, FIVE).ones() == 1);
    assertEquals(2, new Yatzy(ONE, TWO, ONE, FOUR, FIVE).ones());
    assertEquals(0, new Yatzy(SIX, TWO, TWO, FOUR, FIVE).ones());
    assertEquals(4, new Yatzy(ONE, TWO, ONE, ONE, ONE).ones());
  }

  @Test
  public void test_2s() {
    assertEquals(4, new Yatzy(ONE, TWO, THREE, TWO, SIX).twos());
    assertEquals(10, new Yatzy(TWO, TWO, TWO, TWO, TWO).twos());
  }

  @Test
  public void test_threes() {
    assertEquals(6, new Yatzy(ONE, TWO, THREE, TWO, THREE).threes());
    assertEquals(12, new Yatzy(TWO, THREE, THREE, THREE, THREE).threes());
  }

  @Test
  public void fours_test() {
    assertEquals(12, new Yatzy(FOUR, FOUR, FOUR, FIVE, FIVE).fours());
    assertEquals(8, new Yatzy(FOUR, FOUR, FIVE, FIVE, FIVE).fours());
    assertEquals(4, new Yatzy(FOUR, FIVE, FIVE, FIVE, FIVE).fours());
  }

  @Test
  public void fives() {
    assertEquals(10, new Yatzy(FOUR, FOUR, FOUR, FIVE, FIVE).fives());
    assertEquals(15, new Yatzy(FOUR, FOUR, FIVE, FIVE, FIVE).fives());
    assertEquals(20, new Yatzy(FOUR, FIVE, FIVE, FIVE, FIVE).fives());
  }

  @Test
  public void sixes_test() {
    assertEquals(0, new Yatzy(FOUR, FOUR, FOUR, FIVE, FIVE).sixes());
    assertEquals(6, new Yatzy(FOUR, FOUR, SIX, FIVE, FIVE).sixes());
    assertEquals(18, new Yatzy(SIX, FIVE, SIX, SIX, FIVE).sixes());
  }

  @Test
  public void one_pair() {
    assertEquals(6, new Yatzy(THREE, FOUR, THREE, FIVE, SIX).score_pair());
    assertEquals(10, new Yatzy(FIVE, THREE, THREE, THREE, FIVE).score_pair());
    assertEquals(12, new Yatzy(FIVE, THREE, SIX, SIX, FIVE).score_pair());
  }

  @Test
  public void two_Pair() {
    assertEquals(16, new Yatzy(THREE, THREE, FIVE, FOUR, FIVE).two_pair());
    assertEquals(16, new Yatzy(THREE, THREE, FIVE, FIVE, FIVE).two_pair());
  }

  @Test
  public void three_of_a_kind() {
    assertEquals(9, new Yatzy(THREE, THREE, THREE, FOUR, FIVE).three_of_a_kind());
    assertEquals(15, new Yatzy(FIVE, THREE, FIVE, FOUR, FIVE).three_of_a_kind());
    assertEquals(9, new Yatzy(THREE, THREE, THREE, THREE, FIVE).three_of_a_kind());
  }

  @Test
  public void four_of_a_knd() {
    assertEquals(12, new Yatzy(THREE, THREE, THREE, THREE, FIVE).four_of_a_kind());
    assertEquals(20, new Yatzy(FIVE, FIVE, FIVE, FOUR, FIVE).four_of_a_kind());
    assertEquals(9, new Yatzy(THREE, THREE, THREE, THREE, THREE).three_of_a_kind());
  }

  @Test
  public void smallStraight() {
    assertEquals(15, new Yatzy(ONE, TWO, THREE, FOUR, FIVE).smallStraight());
    assertEquals(15, new Yatzy(TWO, THREE, FOUR, FIVE, ONE).smallStraight());
    assertEquals(0, new Yatzy(ONE, TWO, TWO, FOUR, FIVE).smallStraight());
  }

  @Test
  public void largeStraight() {
    assertEquals(20, new Yatzy(SIX, TWO, THREE, FOUR, FIVE).largeStraight());
    assertEquals(20, new Yatzy(TWO, THREE, FOUR, FIVE, SIX).largeStraight());
    assertEquals(0, new Yatzy(ONE, TWO, TWO, FOUR, FIVE).largeStraight());
    assertEquals(0, new Yatzy(ONE, TWO, THREE, FOUR, FIVE).largeStraight());
  }

  @Test
  public void fullHouse() {
    assertEquals(18, new Yatzy(SIX, TWO, TWO, TWO, SIX).fullHouse());
    assertEquals(0, new Yatzy(TWO, THREE, FOUR, FIVE, SIX).fullHouse());
    assertEquals(0, new Yatzy(SIX, SIX, SIX, SIX, SIX).fullHouse());
  }

}