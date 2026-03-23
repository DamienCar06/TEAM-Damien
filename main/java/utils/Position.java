package utils;

/**
 * Represents a position on the chess board.
 * Positions are defined by row and column indices (0-7).
 */
public class Position {
    public final int row;
    public final int col;

    /**
     * Constructs a new Position with the specified row and column.
     * @param row the row index
     * @param col the column index
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Checks if the given row and column are within the board bounds.
     * @param r the row index
     * @param c the column index
     * @return true if the position is valid, false otherwise
     */
    public static boolean isValid(int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
