package pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import utils.Position;


public class Pawn extends Piece {
    /**
     * Constructs a new Pawn with the specified color.
     * @param color the color of the pawn
     */
    public Pawn(Color color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public List<Position> possibleMoves(Board board, Position pos) {
        List<Position> moves = new ArrayList<>();
        int dir = getColor() == Color.WHITE ? -1 : 1;
        int startRow = getColor() == Color.WHITE ? 6 : 1;

        // Forward moves
        Position oneForward = new Position(pos.row + dir, pos.col);
        if (Position.isValid(oneForward.row, oneForward.col) && board.getPiece(oneForward) == null) {
            moves.add(oneForward);
            // Two forward from start
            if (pos.row == startRow) {
                Position twoForward = new Position(pos.row + 2 * dir, pos.col);
                if (Position.isValid(twoForward.row, twoForward.col) && board.getPiece(twoForward) == null) {
                    Position mid = new Position(pos.row + dir, pos.col);
                    if (board.getPiece(mid) == null) {
                        moves.add(twoForward);
                    }
                }
            }
        }

        // Capture pieces
        for (int dc = -1; dc <= 1; dc += 2) {
            Position capture = new Position(pos.row + dir, pos.col + dc);
            if (Position.isValid(capture.row, capture.col)) {
                Piece dest = board.getPiece(capture);
                if (dest != null && dest.getColor() != getColor()) {
                    moves.add(capture);
                }
            }
        }

        return moves;
    }
}
