package tictactoe;

import tictactoe.enums.Symbol;
import tictactoe.exception.InvalidMoveException;
import tictactoe.model.Cell;

public class Board {
  private final int size;
  private final Cell[][] grid;
  private int filledCells = 0;

  public Board(int size) {
    this.size = size;
    grid = new Cell[size][size];
    initializeBoard();
  }

  private void initializeBoard() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        grid[i][j] = new Cell();
      }
    }
  }

  public void placeSymbol(int row, int col, Symbol symbol) {
    validatePosition(row, col);
    grid[row][col].setSymbol(symbol);
    filledCells++;
  }

  private void validatePosition(int row, int col) {
    if (row < 0 || row >= size || col < 0 || col >= size) {
      throw new InvalidMoveException("Cell (" + row + "," + col + ") out of bounds, try again");
    }
    if (!grid[row][col].isEmpty()) {
      throw new InvalidMoveException("Cell (" + row + "," + col
          + ") already occupied with " + grid[row][col].label() + ", try again");
    }
  }

  public boolean isFull() {
    return filledCells == size * size;
  }

  public boolean checkWin(int row, int col, Symbol symbol) {
    for (int i = 0; i < size; i++) {
      if (grid[row][i].getSymbol() != symbol) break;
      if (i == size - 1) {
        System.out.println("Row match");
        return true;
      }
    }
    for (int i = 0; i < size; i++) {
      if (grid[i][col].getSymbol() != symbol) break;
      if (i == size - 1) {
        System.out.println("Column match");
        return true;
      }
    }
    if (row == col) {
      for (int i = 0; i < size; i++) {
        if (grid[i][i].getSymbol() != symbol) break;
        if (i == size - 1) {
          System.out.println("Diagonal match");
          return true;
        }
      }
    }
    if (row + col == size - 1) {
      for (int i = 0; i < size; i++) {
        if (grid[i][size - 1 - i].getSymbol() != symbol) break;
        if (i == size - 1) {
          System.out.println("Anti-diagonal match");
          return true;
        }
      }
    }
    return false;
  }

  public void print() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print(grid[i][j].label() + " ");
      }
      System.out.println();
    }
  }
}