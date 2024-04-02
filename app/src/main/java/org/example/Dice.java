package org.example;

public enum Dice {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6);

  private final int value;

  Dice(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
