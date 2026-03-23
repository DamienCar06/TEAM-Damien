package pieces;

import java.util.List;



public abstract class Piece {
    private final PieceType type;
    private final Color color;

    /**
     * Constructs a new Piece with the specified type and color.
     * @param type the type of the piece
     * @param color the color of the piece
     */
    public Piece(PieceType type, Color color) {
        this.type = type;
        this.color = color;
    }

    /**
     * Returns the type of this piece.
     * @return the piece type
     */
    public PieceType getType() { return type; }

    /**
     * Returns the color of this piece.
     * @return the piece color
     */
    public Color getColor() { return color; }

    /**
     * Calculates the list of possible moves for this piece from the given position on the board.
     * @param board the current board state
     * @param pos the current position of this piece
     * @return a list of possible positions this piece can move to
     */
    public abstract List<Position> possibleMoves(Board board, Position pos);

    /**
     * Returns the symbol representing this piece for display purposes.
     * @return the character symbol for this piece type
     */
    private char symbol() {
        switch (type) {
            case KING: return 'K';
            case QUEEN: return 'Q';
            case ROOK: return 'R';
            case BISHOP: return 'B';
            case KNIGHT: return 'N';
            case PAWN: return 'P';
            default: return '?';
        }
    }

    @Override
    public String toString() {
        char s = symbol();
        char c = color == Color.WHITE ? 'w' : 'b';
        return new String(new char[] {c, s});
    }
}