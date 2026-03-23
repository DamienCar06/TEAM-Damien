package pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import utils.Position;

/**
 * Represents a Queen chess piece.
 * Queens move horizontally, vertically, or diagonally any number of squares
 */
public class Queen extends Piece {
    /**
     * Constructs a new Queen with the specified color.
     * @param color the color of the queen
     */
    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public List<Position> possibleMoves(Board board, Position pos) {
        List<Position> moves = new ArrayList<>();
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
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