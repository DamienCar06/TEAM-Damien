package board;

import java.util.ArrayList;
import java.util.List;

import pieces.Bishop;
import pieces.Color;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.PieceType;
import pieces.Queen;
import pieces.Rook;
import utils.Position;

/**
 * Represents the chess board and manages piece positions and movements.
 * The board is an 8x8 grid where each cell can hold a Piece or be null.
 */
public class Board {
    private final Piece[][] grid = new Piece[8][8];
    private final List<Piece> captured = new ArrayList<>();

    /**
     * Constructs a new empty Board.
     */
    public Board() {}


    /**
     * Prints the current board state to the console.
     */
    public void display() {
        System.out.println(this.toString());
    }

    /**
     * Returns a string representation of the board.
     * Shows ranks, files, and piece positions.
     * @return the board as a formatted string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (char f = 'A'; f <= 'H'; f++) {
            sb.append(f).append("  ");
        }
        sb.append('\n');

        for (int r = 0; r < 8; r++) {
            sb.append(8 - r).append(' ');
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                sb.append(p == null ? ".." : p.toString()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
