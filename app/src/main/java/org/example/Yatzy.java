package org.example;

import static java.util.Arrays.stream;
import static org.example.Dice.FIVE;
import static org.example.Dice.FOUR;
import static org.example.Dice.ONE;
import static org.example.Dice.SIX;
import static org.example.Dice.THREE;
import static org.example.Dice.TWO;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Yatzy {

  private final List<Dice> dices;

  public Yatzy(Dice d1, Dice d2, Dice d3, Dice d4, Dice d5) {
    dices = List.of(d1, d2, d3, d4, d5);
  }

  public int chance() {
    return dices.stream().mapToInt(Dice::value).sum();
  }

  public int yatzy() {
    List<Dice> yatzy = diceWithAtLeastTheSameValue(5);
    return yatzy.isEmpty() ? 0 : 50;
  }

  public int ones() {
    return sumDiceWithExpectedValue(ONE);
  }

  private int sumDiceWithExpectedValue(Dice diceExpected) {
    return dices.stream().filter(dice -> dice == diceExpected).mapToInt(Dice::value).sum();
  }

  public int twos() {
    return sumDiceWithExpectedValue(TWO);
  }

  public int threes() {
    return sumDiceWithExpectedValue(THREE);
  }

  public int fours() {
    return sumDiceWithExpectedValue(FOUR);
  }

  public int fives() {
    return sumDiceWithExpectedValue(FIVE);
  }

  public int sixes() {
    return sumDiceWithExpectedValue(SIX);
  }

  public int score_pair() {
    int numberOfDice = 2;
    List<Dice> pairs = diceWithAtLeastTheSameValue(numberOfDice);
    return pairs.stream().max(Comparator.naturalOrder()).map(dice -> numberOfDice * dice.value()).orElse(0);
  }

  public int two_pair() {
    List<Dice> pairs = diceWithAtLeastTheSameValue(2);
    if (pairs.size() != 2) {
      return 0;
    } else {
      return pairs.stream().mapToInt(dice -> 2 * dice.value()).sum();
    }
  }

  public int four_of_a_kind() {
    int numberOfDice = 4;
    List<Dice> fourOfAKind = diceWithAtLeastTheSameValue(numberOfDice);
    return fourOfAKind.stream().findFirst().map(dice -> numberOfDice * dice.value()).orElse(0);
  }

  public int three_of_a_kind() {
    int numberOfDice = 3;
    List<Dice> threeOfAKind = diceWithAtLeastTheSameValue(numberOfDice);
    return threeOfAKind.stream().findFirst().map(dice -> numberOfDice * dice.value()).orElse(0);
  }

  private List<Dice> diceWithAtLeastTheSameValue(int numberOfDice) {
    Map<Dice, Integer> tallies = countNumberOfDicePerValue();

    return tallies.entrySet().stream().filter(
        entry -> entry.getValue() >= numberOfDice
    ).map(Entry::getKey).collect(Collectors.toList());
  }

  public int smallStraight() {

    Map<Dice, Integer> tallies = countNumberOfDicePerValue();

    if (tallies.get(ONE) == 1 &&
        tallies.get(TWO) == 1 &&
        tallies.get(THREE) == 1 &&
        tallies.get(FOUR) == 1 &&
        tallies.get(FIVE) == 1) {
      return 15;
    }
    return 0;
  }

  private Map<Dice, Integer> countNumberOfDicePerValue() {
    HashMap<Dice, Integer> occurrencePerDice = new HashMap<>();

    EnumSet.allOf(Dice.class).forEach((v -> occurrencePerDice.put(v, 0)));
    dices.forEach(dice -> occurrencePerDice.merge(dice, 1, Integer::sum));

    return occurrencePerDice;
  }

  public int largeStraight() {

    Map<Dice, Integer> tallies = countNumberOfDicePerValue();

    if (tallies.get(TWO) == 1 &&
        tallies.get(THREE) == 1 &&
        tallies.get(FOUR) == 1 &&
        tallies.get(FIVE) == 1
        && tallies.get(SIX) == 1) {
      return 20;
    }
    return 0;
  }

  public int fullHouse() {
    List<Dice> pairs = diceWithAtLeastTheSameValue(2);
    List<Dice> threeOfAKind = diceWithAtLeastTheSameValue(3);

    pairs.removeAll(threeOfAKind);
    if (pairs.isEmpty() || threeOfAKind.isEmpty()) {
      return 0;
    } else {
      return dices.stream().mapToInt(Dice::value).sum();
    }
  }

}
