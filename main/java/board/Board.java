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
     * Attempts to move a piece from one position to another.
     * Checks if the move is valid according to the piece's movement rules.
     * If a piece is captured, it is added to the captured list.
     * @param from the starting position
     * @param to the destination position
     * @return true if the move was successful, false otherwise
     */
    public boolean movePiece(Position from, Position to) {
        if (!Position.isValid(from.row, from.col) || !Position.isValid(to.row, to.col)) return false;
        Piece p = getPiece(from);
        if (p == null) return false;
        if (!p.possibleMoves(this, from).contains(to)) return false;
        Piece dest = getPiece(to);
        if (dest != null) captured.add(dest);
        set(to.row, to.col, p);
        set(from.row, from.col, null);
        return true;
    }

    /**
     * Sets a piece at the specified row and column.
     * @param row the row index
     * @param col the column index
     * @param p the piece to place, or null to clear the square
     */
    public void set(int row, int col, Piece p) {
        if (!Position.isValid(row, col)) return;
        grid[row][col] = p;
    }

    /**
     * Checks if the path between two positions is clear of pieces.
     * Used for sliding pieces like rooks, bishops, and queens.
     * @param from the starting position
     * @param to the ending position
     * @return true if the path is clear, false if blocked
     */
    public boolean isPathClear(Position from, Position to) {
        int dr = Integer.compare(to.row, from.row);
        int dc = Integer.compare(to.col, from.col);
        if (dr == 0 && dc == 0) return true;
        int r = from.row + dr;
        int c = from.col + dc;
        while (r != to.row || c != to.col) {
            if (grid[r][c] != null) return false;
            r += dr;
            c += dc;
        }
        return true;
    }

    /**
     * Checks if the king of the specified color is in check.
     * @param color the color of the king to check
     * @return true if the king is in check, false otherwise
     */
    public boolean isCheck(Color color) {
        Position kingPos = null;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p != null && p.getType() == PieceType.KING && p.getColor() == color) {
                    kingPos = new Position(r, c);
                    break;
                }
            }
            if (kingPos != null) break;
        }
        if (kingPos == null) return false;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p != null && p.getColor() != color) {
                    if (p.possibleMoves(this, new Position(r,c)).contains(kingPos)) return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the king of the specified color is in checkmate.
     * This is a brute-force implementation that checks if any legal move can remove the check.
     * @param color the color of the king to check
     * @return true if the king is in checkmate, false otherwise
     */
    public boolean isCheckmate(Color color) {
        if (!isCheck(color)) return false;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p == null || p.getColor() != color) continue;
                for (int tr = 0; tr < 8; tr++) {
                    for (int tc = 0; tc < 8; tc++) {
                        Position from = new Position(r,c);
                        Position to = new Position(tr,tc);
                        Piece dest = grid[tr][tc];
                        if (!p.possibleMoves(this, from).contains(to)) continue;
                        // make move
                        grid[tr][tc] = p;
                        grid[r][c] = null;
                        boolean stillInCheck = isCheck(color);
                        // undo
                        grid[r][c] = p;
                        grid[tr][tc] = dest;
                        if (!stillInCheck) return false;
                    }
                }
            }
        }
        return true;
    }

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
