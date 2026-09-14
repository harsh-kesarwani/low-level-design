package tictactoe.model;

import tictactoe.enums.Symbol;

public class Cell {
  private Symbol symbol = Symbol.EMPTY;

  public Symbol getSymbol() {
    return symbol;
  }

  public void setSymbol(Symbol symbol) {
    this.symbol = symbol;
  }

  public boolean isEmpty() {
    return symbol == Symbol.EMPTY;
  }

  public char label() {
    return symbol.getLabel();
  }
}
