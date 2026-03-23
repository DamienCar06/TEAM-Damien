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
     * Initializes the board with pieces in their standard starting positions.
     */
    public void initStandardSetup() {
        for (int c = 0; c < 8; c++) {
            grid[0][c] = createPiece(c, Color.BLACK);
            grid[1][c] = new Pawn(Color.BLACK);
        }

        for (int c = 0; c < 8; c++) {
            grid[6][c] = new Pawn(Color.WHITE);
            grid[7][c] = createPiece(c, Color.WHITE);
        }
    }

    /**
     * Creates a piece based on the column index for the back rank.
     * @param c the column index (0-7)
     * @param color the color of the piece
     * @return the created piece
     */
    private Piece createPiece(int c, Color color) {
        switch (c) {
            case 0: case 7: return new Rook(color);
            case 1: case 6: return new Knight(color);
            case 2: case 5: return new Bishop(color);
            case 3: return new Queen(color);
            case 4: return new King(color);
            default: return null;
        }
    }

    /**
     * Returns the piece at the specified position.
     * @param pos the position to check
     * @return the piece at the position, or null if empty
     */
    public Piece getPiece(Position pos) {
        if (!Position.isValid(pos.row, pos.col)) return null;
        return grid[pos.row][pos.col];
    }

    /**
     * Returns the piece at the specified row and column.
     * @param row the row index
     * @param col the column index
     * @return the piece at the position, or null if empty
     */
    public Piece get(int row, int col) { return getPiece(new Position(row,col)); }

    /**
     * Returns the list of captured pieces.
     * @return the list of captured pieces
     */
    public List<Piece> getCaptured() { return captured; }
    
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
