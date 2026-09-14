package tictactoe.model;

import tictactoe.enums.Symbol;

public class Player {
  private final String name;
  private final Symbol symbol;

  public Player(String name, Symbol symbol) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Player name cannot be empty");
    }
    if (symbol == null || symbol == Symbol.EMPTY) {
      throw new IllegalArgumentException("Player symbol cannot be empty");
    }
    this.name = name;
    this.symbol = symbol;
  }

  public Symbol getSymbol() {
    return symbol;
  }

  public String label() {
    return name + " (" + symbol.getLabel() + ")";
  }
}
