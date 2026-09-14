package tictactoe;

import tictactoe.enums.Symbol;
import tictactoe.model.Player;

public class Main {
  public static void main(String[] args) {
    Player player1 = new Player("Joe", Symbol.X);
    Player player2 = new Player("Smith", Symbol.O);
    tictactoe.Game game = new tictactoe.Game(player1, player2, 3);
    game.makeMove(0, 0);
    game.makeMove(0, 1);
    game.makeMove(0, 2);
    game.makeMove(1, 1);
    game.makeMove(2, 1);
    game.makeMove(1, 0);
    game.makeMove(1, 2);
    game.makeMove(2, 2);
    game.makeMove(2, 0);
  }
}
