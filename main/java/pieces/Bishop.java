package pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import utils.Position;

/**
 * Represents a Bishop chess piece.
 * Bishops move diagonally any number of squares
 */
public class Bishop extends Piece {
    /**
     * Constructs a new Bishop with the specified color.
     * @param color the color of the bishop
     */
    public Bishop(Color color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public List<Position> possibleMoves(Board board, Position pos) {
        List<Position> moves = new ArrayList<>();
        int[] dr = {-1, -1, 1, 1};
        int[] dc = {-1, 1, -1, 1};

        for (int d = 0; d < 4; d++) {
            for (int i = 1; i < 8; i++) {
                int nr = pos.row + i * dr[d];
                int nc = pos.col + i * dc[d];
                if (!Position.isValid(nr, nc)) break;
                Position newPos = new Position(nr, nc);
                Piece p = board.getPiece(newPos);
                if (p == null) {
                    moves.add(newPos);
                } else {
                    if (p.getColor() != getColor()) {
                        moves.add(newPos);
                    }
                    break;
                }
            }
        }

        return moves;
    }
}