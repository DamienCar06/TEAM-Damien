package pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import utils.Position;

/**
 * Represents a Knight chess piece.
 * Knights move in an L-shape: two squares in one direction and one perpendicularly.
 */
public class Knight extends Piece {
    /**
     * Constructs a new Knight with the specified color.
     * @param color the color of the knight
     */
    public Knight(Color color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public List<Position> possibleMoves(Board board, Position pos) {
        List<Position> moves = new ArrayList<>();
        int[] dr = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dc = {-1, 1, -2, 2, -2, 2, -1, 1};

        for (int i = 0; i < 8; i++) {
            int nr = pos.row + dr[i];
            int nc = pos.col + dc[i];
            if (Position.isValid(nr, nc)) {
                Position newPos = new Position(nr, nc);
                Piece p = board.getPiece(newPos);
                if (p == null || p.getColor() != getColor()) {
                    moves.add(newPos);
                }
            }
        }

        return moves;
    }
}
