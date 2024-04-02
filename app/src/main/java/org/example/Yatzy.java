package org.example;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.rangeClosed;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Yatzy {

  private final int[] dices;

  public Yatzy(int d1, int d2, int d3, int d4, int d5) {
    dices = new int[5];
    dices[0] = d1;
    dices[1] = d2;
    dices[2] = d3;
    dices[3] = d4;
    dices[4] = d5;
  }

  public int chance() {
    return stream(dices).sum();
  }

  public int yatzy() {
    int[] counts = new int[6];
    for (int die : dices) {
      counts[die - 1]++;
    }
    for (int i = 0; i != 6; i++) {
      if (counts[i] == 5) {
        return 50;
      }
    }
    return 0;
  }

  public int ones() {
    return sumDiceWithExpectedValue(1);
  }

  private int sumDiceWithExpectedValue(int diceExpected) {
    return stream(dices).filter(dice -> dice == diceExpected).sum();
  }

  public int twos() {

    return sumDiceWithExpectedValue(2);
  }

  public int threes() {
    return sumDiceWithExpectedValue(3);
  }

  public int fours() {
    return sumDiceWithExpectedValue(4);
  }

  public int fives() {
    return sumDiceWithExpectedValue(5);
  }

  public int sixes() {
    return sumDiceWithExpectedValue(6);
  }

  public int score_pair() {
    int numberOfDice = 2;
    List<Integer> pairs = diceWithAtLeastTheSameValue(numberOfDice);
    return pairs.stream().max(Comparator.naturalOrder()).map(i -> numberOfDice * i).orElse(0);
  }

  public int two_pair() {
    List<Integer> pairs = diceWithAtLeastTheSameValue(2);
    if (pairs.size() != 2) {
      return 0;
    } else {
      return pairs.stream().map(i -> 2 * i).reduce(0, Integer::sum);
    }
  }

  public int four_of_a_kind() {
    int numberOfDice = 4;
    List<Integer> fourOfAKind = diceWithAtLeastTheSameValue(numberOfDice);
    return fourOfAKind.stream().findFirst().map(i -> numberOfDice * i).orElse(0);
  }

  public int three_of_a_kind() {
    int numberOfDice = 3;
    List<Integer> threeOfAKind = diceWithAtLeastTheSameValue(numberOfDice);
    return threeOfAKind.stream().findFirst().map(i -> numberOfDice * i).orElse(0);
  }

  private List<Integer> diceWithAtLeastTheSameValue(int numberOfDice) {
    Map<Integer, Integer> tallies = countNumberOfDicePerValue();

    return tallies.entrySet().stream().filter(
        entry -> entry.getValue() >= numberOfDice
    ).map(Entry::getKey).collect(Collectors.toList());
  }

  public int smallStraight() {

    Map<Integer, Integer> tallies = countNumberOfDicePerValue();

    if (tallies.get(1) == 1 &&
        tallies.get(2) == 1 &&
        tallies.get(3) == 1 &&
        tallies.get(4) == 1 &&
        tallies.get(5) == 1) {
      return 15;
    }
    return 0;
  }

  private Map<Integer, Integer> countNumberOfDicePerValue() {
    HashMap<Integer, Integer> occurrencePerDice = new HashMap<>();

    rangeClosed(1, 6).forEach((v -> occurrencePerDice.put(v, 0)));
    stream(dices).forEach(dice -> occurrencePerDice.merge(dice, 1, Integer::sum));

    return occurrencePerDice;
  }

  public int largeStraight() {

    Map<Integer, Integer> tallies = countNumberOfDicePerValue();

    if (tallies.get(2) == 1 &&
        tallies.get(3) == 1 &&
        tallies.get(4) == 1 &&
        tallies.get(5) == 1
        && tallies.get(6) == 1) {
      return 20;
    }
    return 0;
  }

  public int fullHouse() {
    boolean _2 = false;
    int i;
    int _2_at = 0;
    boolean _3 = false;
    int _3_at = 0;

    Map<Integer, Integer> tallies = countNumberOfDicePerValue();

    for (i = 0; i != 6; i += 1) {
      if (tallies.get(i + 1) == 2) {
        _2 = true;
        _2_at = i + 1;
      }
    }

    for (i = 0; i != 6; i += 1) {
      if (tallies.get(i + 1) == 3) {
        _3 = true;
        _3_at = i + 1;
      }
    }

    if (_2 && _3) {
      return _2_at * 2 + _3_at * 3;
    } else {
      return 0;
    }
  }

}
