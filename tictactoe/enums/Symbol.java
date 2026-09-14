package tictactoe.enums;

public enum Symbol {
  EMPTY('-'),
  X('X'),
  O('O');

  private final char label;

  Symbol(char label) {
    this.label = label;
  }

  public char getLabel() {
    return label;
  }
}
