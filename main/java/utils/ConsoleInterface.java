package utils;

import java.util.Scanner;

import board.Board;

/**
 * Handles the console-based user interface for the chess game.
 * Manages input parsing, move validation, and game loop.
 */
public class ConsoleInterface {
    /** Scanner for reading user input. */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Runs the main game loop, displaying the board and processing user moves.
     * @param board the chess board to play on
     */
    public void runGame(Board board) {
        board.initStandardSetup();
        System.out.println("Initial position:");
        System.out.println(board);

        while (true) {
            System.out.print("Enter move (e2 e4 or e2e4), or 'quit': ");
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) break;
            if (line.isEmpty()) continue;

            try {
                Move move = parseMove(line);
                if (move != null) {
                    boolean ok = board.movePiece(move.from, move.to);
                    System.out.println("Move " + move.fromString + "->" + move.toString + ": " + ok);
                    if (ok) {
                        System.out.println(board);
                    }
                } else {
                    System.out.println("Invalid move format. Example: e2 e4 or e2e4");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid square: " + ex.getMessage());
            }
        }

        System.out.println("Goodbye.");
    }

    /**
     * Parses a move string into from and to positions.
     * @param input the input string
     * @return a Move object if parsing succeeds, null otherwise
     */
    private Move parseMove(String input) {
        String fromS = null, toS = null;
        String[] parts = input.split("\\s+");
        if (parts.length == 1 && parts[0].length() == 4) {
            fromS = parts[0].substring(0, 2);
            toS = parts[0].substring(2, 4);
        } else if (parts.length >= 2) {
            fromS = parts[0];
            toS = parts[1];
        } else {
            return null;
        }

        if (fromS == null || toS == null || fromS.length() != 2 || toS.length() != 2) {
            return null;
        }

        Position from = algebraicToPosition(fromS);
        Position to = algebraicToPosition(toS);
        return new Move(from, to, fromS, toS);
    }

    /**
     * Converts algebraic notation to a Position object.
     * @param sq the square in algebraic notation (e.g., "e2")
     * @return the corresponding Position
     * @throws IllegalArgumentException if the square is invalid
     */
    private Position algebraicToPosition(String sq) {
        if (sq == null || sq.length() != 2) throw new IllegalArgumentException(sq);
        int col = sq.charAt(0) - 'a';
        int rank = sq.charAt(1) - '0';
        if (col < 0 || col > 7 || rank < 1 || rank > 8) throw new IllegalArgumentException(sq);
        int row = 8 - rank;
        return new Position(row, col);
    }

    private static class Move {
        /** The starting position. */
        Position from;
        /** The ending position. */
        Position to;
        /** The starting square in string form. */
        String fromString;
        /** The ending square in string form. */
        String toString;

        /**
         * Constructs a new Move.
         * @param from the from position
         * @param to the to position
         * @param fromString the from string
         * @param toString the to string
         */
        Move(Position from, Position to, String fromString, String toString) {
            this.from = from;
            this.to = to;
            this.fromString = fromString;
            this.toString = toString;
        }
    }
}
