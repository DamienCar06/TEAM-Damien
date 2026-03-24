# TEAM-Damien
Damien Cardenas iey18

## Chess Game Project

A console-based chess game implemented in Java.

### Environment & Dependencies
- **Java Version**: 25.0.1 (LTS)
- **Build Tool**: None (plain Java compilation with `javac`)

### How to Run
1. Compile the project:
   ```
   javac -sourcepath main/java -d classes main/java/board/Board.java main/java/pieces/*.java main/java/utils/*.java
   ```
2. Run the game:
   ```
   java -cp classes utils.Run
   ```
### How to Play
- The game displays the chess board in the console.
- Enter moves in algebraic notation:
  - Format: `e2e4` or `e2 e4` (from square to square)
  - Example: `e2 e4` moves the pawn from e2 to e4
- Type `quit` or `exit` to end the game.
- The board updates after each valid move.

### Features
#### Implemented
- Chess board representation (8x8 grid)
- All standard chess pieces: King, Queen, Rook, Bishop, Knight, Pawn
- Basic movement rules for all pieces:
  - Pawns: Forward movement (1 or 2 squares from start), diagonal captures
  - Bishops: Diagonal movement any number of squares
  - Rooks: Horizontal/vertical movement
  - Queens: Combination of rook and bishop moves
  - Knights: L-shaped jumps
  - Kings: One square in any direction
- Console-based user interface
- Piece capture mechanics
- Board display with piece symbols

#### Not Implemented
- Turn-based gameplay (no alternating white/black moves)
- Check and checkmate detection
- Castling
- En passant
- Pawn promotion
- Game end conditions (resignation, stalemate)
- Move validation for check (pieces can move into check)
- Undo moves
- Save/load game state

#### Limitations
- No graphical user interface (console-only)
- Basic error handling for invalid moves
- Limited to standard chess rules without advanced features
