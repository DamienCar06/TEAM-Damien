package pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import utils.Position;

/**
 * Represents a King chess piece.
 * Kings move one square in any direction (horizontally, vertically, or diagonally).
 */
public class King extends Piece {
    /**
     * Constructs a new King with the specified color.
     * @param color the color of the king
     */
    public King(Color color) {
        super(PieceType.KING, color);
    }

    @Override
    public List<Position> possibleMoves(Board board, Position pos) {
        List<Position> moves = new ArrayList<>();
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int nr = pos.row + dr;
                int nc = pos.col + dc;
                if (Position.isValid(nr, nc)) {
                    Position newPos = new Position(nr, nc);
                    Piece p = board.getPiece(newPos);
                    if (p == null || p.getColor() != getColor()) {
                        moves.add(newPos);
                    }
                }
            }
        }
        return moves;
    }
}
