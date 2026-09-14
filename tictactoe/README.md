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

---

## AI Review

**Verdict:** Solid LLD base — clear responsibilities, immutable `final` fields, constructor
validation, a custom exception, and smart win checks (diagonal only when `row == col`). The gaps
are all about **design principles + extensibility**, which is exactly where LLD rounds score.

### Most impactful

1. **I/O is tangled into the domain (biggest).**
   `System.out.println` is scattered through `Game`, and `Board.checkWin` even prints `"Row match"`
   — a boolean method with side effects. This breaks **SRP** and makes everything untestable (you'd
   assert on stdout).
   **Fix:** add an output boundary via an **Observer** — `Game` notifies a
   `GameObserver` / `ConsoleRenderer` on move / win / draw / turn events, so the domain stays pure
   and rendering is swappable. `checkWin` returns `boolean` only.

2. **`Game` exposes no state — can't be driven or tested.**
   `makeMove` is `void`, so stdout is the only signal.
   **Fix:** add `getStatus()` and `getCurrentPlayer()`, and have `makeMove` return a result. Then a
   controller or test can loop until a terminal state instead of hardcoding 9 moves in `Main`.

3. **Win detection is hardcoded in `Board` — no extension point.**
   Classic follow-up: *"support K-in-a-row / a bigger board / a new rule."*
   **Fix:** extract a `WinningStrategy` interface (`RowStrategy`, `ColumnStrategy`,
   `DiagonalStrategy`) and iterate over strategies — demonstrates **Open/Closed + Strategy**.

4. **No Human-vs-AI `Player` abstraction.**
   README lists AI as unsupported and the interviewer will push here.
   **Fix:** give `Player` a `Move nextMove(Board)` (or a `MoveStrategy`) so `HumanPlayer` (reads
   input) and `BotPlayer` (computes) coexist without touching `Game`. Even naming this scores.

### Worth mentioning (lower effort / impact)

- **`EMPTY` in the `Symbol` enum** conflates "player mark" with "empty square." Cleaner:
  `Symbol { X, O }` with emptiness via `null` / `Optional` on `Cell` (or keep it, since `Player`
  already guards against it).
- **O(1) incremental win check** — currently rescans a full row / col / diagonal each move (O(n));
  per-row/col/diagonal counters make it O(1). Not needed for correctness, but a good "if asked to
  optimize" talking point.
- **No `Move` / `Position` value object** — fine at 3×3, but a small type reads better and pairs
  naturally with the player-strategy idea.
- **`Main` doubles as a hardcoded test harness** — a tiny driver loop over `getStatus()` would show
  the intended runtime shape.

### Already good (keep)

Immutability, fail-fast validation (duplicate-symbol, bounds / occupied), custom unchecked
exception, `filledCells` for O(1) `isFull`, clean `model` / `enums` / `exception` packages, and the
honest README scope section.
