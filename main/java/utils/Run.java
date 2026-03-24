package utils;

import board.Board;
import utils.ConsoleInterface;

/**
 * Main class for the chess game application.
 * Initializes the board and starts the console-based game interface.
 */
public class Run {

    /**
     * The main entry point for the chess game.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Board board = new Board();
        ConsoleInterface console = new ConsoleInterface();
        console.runGame(board);
    }
}
