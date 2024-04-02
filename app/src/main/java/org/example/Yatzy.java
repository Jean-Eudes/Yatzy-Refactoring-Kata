package org.example;

import java.util.Arrays;

public class Yatzy {

  protected int[] dices;

  public Yatzy(int d1, int d2, int d3, int d4, int _5) {
    dices = new int[5];
    dices[0] = d1;
    dices[1] = d2;
    dices[2] = d3;
    dices[3] = d4;
    dices[4] = _5;
  }

  public int chance() {
    int total = 0;
    total += dices[0];
    total += dices[1];
    total += dices[2];
    total += dices[3];
    total += dices[4];
    return total;
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
    return Arrays.stream(dices).filter(dice -> dice == diceExpected).sum();
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
    int[] counts = new int[6];
    counts[dices[0] - 1]++;
    counts[dices[1] - 1]++;
    counts[dices[2] - 1]++;
    counts[dices[3] - 1]++;
    counts[dices[4] - 1]++;
    int at;
    for (at = 0; at != 6; at++) {
      if (counts[6 - at - 1] >= 2) {
        return (6 - at) * 2;
      }
    }
    return 0;
  }

  public int two_pair() {
    int[] counts = new int[6];
    counts[dices[0] - 1]++;
    counts[dices[1] - 1]++;
    counts[dices[2] - 1]++;
    counts[dices[3] - 1]++;
    counts[dices[4] - 1]++;
    int n = 0;
    int score = 0;
    for (int i = 0; i < 6; i += 1) {
      if (counts[6 - i - 1] >= 2) {
        n++;
        score += (6 - i);
      }
    }
    if (n == 2) {
      return score * 2;
    } else {
      return 0;
    }
  }

  public int four_of_a_kind() {
    int[] tallies;
    tallies = new int[6];
    tallies[dices[0] - 1]++;
    tallies[dices[1] - 1]++;
    tallies[dices[2] - 1]++;
    tallies[dices[3] - 1]++;
    tallies[dices[4] - 1]++;
    for (int i = 0; i < 6; i++) {
      if (tallies[i] >= 4) {
        return (i + 1) * 4;
      }
    }
    return 0;
  }

  public int three_of_a_kind() {
    int[] t;
    t = new int[6];
    t[dices[0] - 1]++;
    t[dices[1] - 1]++;
    t[dices[2] - 1]++;
    t[dices[3] - 1]++;
    t[dices[4] - 1]++;
    for (int i = 0; i < 6; i++) {
      if (t[i] >= 3) {
        return (i + 1) * 3;
      }
    }
    return 0;
  }

  public int smallStraight() {
    int[] tallies;
    tallies = new int[6];
    tallies[dices[0] - 1] += 1;
    tallies[dices[1] - 1] += 1;
    tallies[dices[2] - 1] += 1;
    tallies[dices[3] - 1] += 1;
    tallies[dices[4] - 1] += 1;
    if (tallies[0] == 1 &&
        tallies[1] == 1 &&
        tallies[2] == 1 &&
        tallies[3] == 1 &&
        tallies[4] == 1) {
      return 15;
    }
    return 0;
  }

  public int largeStraight() {
    int[] tallies;
    tallies = new int[6];
    tallies[dices[0] - 1] += 1;
    tallies[dices[1] - 1] += 1;
    tallies[dices[2] - 1] += 1;
    tallies[dices[3] - 1] += 1;
    tallies[dices[4] - 1] += 1;
    if (tallies[1] == 1 &&
        tallies[2] == 1 &&
        tallies[3] == 1 &&
        tallies[4] == 1
        && tallies[5] == 1) {
      return 20;
    }
    return 0;
  }

  public int fullHouse() {
    int[] tallies;
    boolean _2 = false;
    int i;
    int _2_at = 0;
    boolean _3 = false;
    int _3_at = 0;

    tallies = new int[6];
    tallies[dices[0] - 1] += 1;
    tallies[dices[1] - 1] += 1;
    tallies[dices[2] - 1] += 1;
    tallies[dices[3] - 1] += 1;
    tallies[dices[4] - 1] += 1;

    for (i = 0; i != 6; i += 1) {
      if (tallies[i] == 2) {
        _2 = true;
        _2_at = i + 1;
      }
    }

    for (i = 0; i != 6; i += 1) {
      if (tallies[i] == 3) {
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
