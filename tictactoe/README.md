# Tic Tac Toe

### Features
- Two players
- Configurable board size (N × N)
- Win detection on row, column, and both diagonals
- Draw detection when the board is full

### Not supported
- Interactive input — moves are hardcoded in `Main`
- N players
- AI / computer opponent
- Undo / replay of moves
- Persistence (state is in-memory only)

### Core classes
| Class | Responsibility                                                               |
|-------|------------------------------------------------------------------------------|
| `Game` | Orchestrates turns, applies moves, tracks `GameStatus`, switches players.    |
| `Board` | Holds the `Cell` grid; places symbols, validates positions, checks win/full. |
| `Cell` | A single square holding a `Symbol`.                                          |
| `Player` | Name + assigned `Symbol`.                                                    |
| `Symbol` | Enum: `EMPTY`, `X`, `O`.                                                     |
| `GameStatus` | Enum: `IN_PROGRESS`, `DRAW`, `X_WON`, `O_WON`.                               |
| `InvalidMoveException` | Thrown on invalid moves - out of bounds / already occupied / post game over. |



### Run
From the repo root:
```bash
javac tictactoe/*.java tictactoe/model/*.java tictactoe/enums/*.java tictactoe/exception/*.java
java tictactoe.Main
```
