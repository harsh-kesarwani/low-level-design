package tictactoe;

import tictactoe.enums.GameStatus;
import tictactoe.enums.Symbol;
import tictactoe.exception.InvalidMoveException;
import tictactoe.model.Player;

public class Game {
  private final Board board;
  private final Player player1;
  private final Player player2;
  private Player currentPlayer;
  private GameStatus gameStatus;

  Game(Player player1, Player player2, int boardSize) {
    if (player1.getSymbol() == player2.getSymbol()) {
      throw new IllegalArgumentException("Players cannot have same symbol");
    }
    this.player1 = player1;
    this.player2 = player2;
    this.currentPlayer = player1;
    this.board = new Board(boardSize);
    gameStatus = GameStatus.IN_PROGRESS;
    System.out.println(currentPlayer.label() + "'s turn");
  }

  public void makeMove(int row, int col) {
    if (gameStatus != GameStatus.IN_PROGRESS) {
      throw new InvalidMoveException("Game is already over, start a new game");
    }
    System.out.println(currentPlayer.label() + " played (" + row + "," + col + ")");
    board.placeSymbol(row, col, currentPlayer.getSymbol());
    if (board.checkWin(row, col, currentPlayer.getSymbol())) {
      System.out.println(currentPlayer.label() + " won");
      gameStatus = currentPlayer.getSymbol() == Symbol.X ? GameStatus.X_WON : GameStatus.O_WON;
      board.print();
      return;
    }
    if (board.isFull()) {
      System.out.println("Game is a draw");
      gameStatus = GameStatus.DRAW;
      board.print();
      return;
    }
    currentPlayer = (currentPlayer == player1) ? player2 : player1;
    System.out.println(currentPlayer.label() + "'s turn");
  }
}
